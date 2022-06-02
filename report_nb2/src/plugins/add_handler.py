import time

import pymysql
import requests
from nonebot import on_request
from nonebot.adapters.cqhttp import Bot, Event

DB_HOST = "localhost"
DB_PORT = 3306
DB_USER = "root"
DB_PASSWD = "09140727"
DB_NAME = "report"

friend_match = on_request()


@friend_match.handle()
def get_message(bot: Bot, event: Event):
    flag = str(event.flag)
    bot_id = str(bot.self_id)

    # sql = "select port from bot where bot_id = " + bot_id
    # db = pymysql.connect(host=DB_HOST, port=DB_PORT, user=DB_USER,
    #                      password=DB_PASSWD, database=DB_NAME)
    # cursor = db.cursor()
    # cursor.execute(sql)
    # port = cursor.fetchone()[0]
    # cursor.close()
    # db.close()

    port = 5700

    url = "http://127.0.0.1:" + str(port)

    if event.request_type == "friend":
        approve_url = url + "/set_friend_add_request?approve=true&flag=" + flag
    else:
        approve_url = url + "/set_group_add_request?approve=true&sub_type=invite&flag=" + flag

    requests.get(approve_url)

    time.sleep(1)

    send_url = url + "/send_group_msg?message=大家好，我是健康打卡自动提醒机器人，接下来的一段时间将由我给大家提供健康打卡提醒的服务" \
                     "。\n\n该项目的研发由19数据王建文独立发起并完成，目前由实干青年众创空间工作室维护。\n\n期待尽快迎来本项目完成历史" \
                     "使命的一天，让我们的青春得到应有的奔放！&group_id=" + str(event.group_id)
    requests.get(send_url)
