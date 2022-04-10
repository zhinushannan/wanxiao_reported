import base64
import datetime
import json
import sys
import time
from io import BytesIO
import requests

from handle_log import HandleLog

log = HandleLog()


class AutoReport:
    def __init__(self, teacher, account_info, clazz, cursor, redis, remove_dict):
        """
        :param teacher: teacher name
        :param account_info: {'username': username, 'securitycode': password, 'captcha': ''}
        :param clazz:
            {
                'clazz_name': {
                    'dept_id': dept_id,
                    'group_id': group_id,
                    'bot_id': bot_id,
                    'group_id': group_id,
                    'student_info': {name1: qq1, name2: qq2, ...}
                }
            }
        :param cursor: mysql cursor
        :param redis: redis
        """
        self.teacher, self.account_info, self.clazz, self.cursor, self.redis, self.remove_dict = teacher, account_info, clazz, cursor, redis, remove_dict
        self.login_url = 'https://reported.17wanxiao.com/admin/login'
        self.headers = {
            'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36',
        }
        self.captcha_url = f'https://reported.17wanxiao.com/captcha.jpg?t={int(time.time() * 1000)}'
        self.now = datetime.datetime.now()

    def base64_api(self, img):
        post_data = {"username": 'zhinushannan', "password": '5k7B8SCe7mHe', "typeid": 3, "image": img}
        result = json.loads(requests.post("http://api.ttshitu.com/predict", data=post_data).text)
        if result['success']:
            return result["data"]["result"]
        else:
            return result["message"]

    def requests_get(self, url):
        return requests.get(url=url, headers=self.headers)

    def login(self):
        # get captcha
        captcha_response = self.requests_get(self.captcha_url)
        # parse captcha
        captcha_base64 = base64.b64encode(BytesIO(captcha_response.content).read())
        captcha_content = self.base64_api(captcha_base64)
        self.account_info["captcha"] = captcha_content

        # get SERVERID and shiro.session
        SERVERID = captcha_response.cookies.get('SERVERID')
        shiro_session = captcha_response.cookies.get('shiro.session')
        # get cookie and set in redis, put cookie in headers SERVERID and shiro.session
        cookie = f'SERVERID={SERVERID};shiro.session={shiro_session}'
        self.redis.set(f"cookie:{str(self.account_info['username'])}", cookie)
        self.headers['cookie'] = cookie
        # do login
        login_response = requests.post(url=self.login_url, data=self.account_info, headers=self.headers)
        # login requests success info : {'result': True, 'msg': 'success', 'code': 0}
        # login requests fail
        return login_response.json()

    def check_cookie(self):
        index_url = "https://reported.17wanxiao.com/index.html"
        cookie = self.redis.get(f"cookie:{str(self.account_info['username'])}")
        self.headers["cookie"] = cookie
        response = self.requests_get(index_url)
        return response.url == index_url

    def get_unreported_list(self, dept_id):
        unreported_url = f'https://reported.17wanxiao.com/student/list2?undo=0&reportTime={self.now.year + 1}-{str(self.now.month).zfill(2)}-{str(self.now.day).zfill(2)}&_search=false&limit=60&page=1&order=asc&deptId=' + dept_id
        return self.requests_get(unreported_url).json()['page']['records']

    def get_msg(self, unreported_list, student_info, dept_id):
        removes = None
        if dept_id in list(self.remove_dict.keys()):
            removes = list(self.remove_dict.values())[0]

        msg = ""
        for i in unreported_list:
            name = i['name']
            if removes is not None and name in removes:
                continue
            msg = msg + f'[CQ:at,qq={student_info[name]},name={name}]\t'

        if len(msg) != 0:
            msg = '健康打卡自动提醒\n尚未打卡的同学如下：\n' + msg + '\n已打卡的同学请忽略此条消息'
        else:
            msg = "今日健康打卡已完成！"
            tomorrow = self.now + datetime.timedelta(days=1)
            sql = f'update clazz set `date`="{tomorrow.year}.{tomorrow.month}.{tomorrow.day}" where dept_id="{dept_id}"'
            self.cursor.execute(sql)
        now = datetime.datetime.now()
        return msg + f'\n{now.hour}:{now.minute}:{now.second}'

    def send_msg(self, group_id, msg, delete, bot_id):
        if delete:
            message_id = str(self.redis.get(f"message_id:{group_id}"))
            # log.debug(f"发送消息 : http://127.0.0.1:{bot_id}/delete_msg?message_id={message_id}")
            # requests.get(f"http://127.0.0.1:{bot_id}/delete_msg?message_id=" + message_id)
            time.sleep(5)
        # log.debug(f"http://127.0.0.1:{bot_id}/send_group_msg?group_id={group_id}&message={msg}")
        # requests.get(f"http://127.0.0.1:{bot_id}/send_group_msg?group_id={group_id}&message={msg}")

    def run(self):
        log.info("尝试使用缓存cookie")
        isAble = self.check_cookie()
        if not isAble:
            log.info("缓存cookie已失效，重新登录获取")
            message = self.login()
            if not message["result"]:
                log.warning(message)
                sys.exit(-1)
            log.info("登录成功")
        else:
            log.info("cookie未失效，使用cookie进行登录")
        for i in list(self.clazz.keys()):
            log.debug(f"开始推送{i}")
            try:
                unreported_url = self.get_unreported_list(self.clazz[i]["dept_id"])
                msg = self.get_msg(unreported_url, self.clazz[i]["student_info"], self.clazz[i]["dept_id"])
            except Exception as e:
                log.error(f"获取名单失败，请检查网络是否通常或是否更换班主任！错误信息：{str(e)}")
                sys.exit(-1)
            log.debug(f"{self.clazz[i]['group_id']} \n {msg} \n {self.clazz[i]['delete']} \n {str(self.clazz[i]['bot_id'])}")
            try:
                self.send_msg(str(self.clazz[i]["group_id"]), msg, self.clazz[i]["delete"],
                              str(self.clazz[i]["bot_id"]))
            except Exception as e:
                log.debug(f"发送失败，请检查账号是否正常登录或是否被风控！错误信息：{str(e)}")
                log.error(f"发送失败，请检查账号是否正常登录或是否被风控！错误信息：{str(e)}")
