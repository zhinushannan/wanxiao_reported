import datetime
import redis

from handle_log import HandleLog

from auto_reported import AutoReport

log = HandleLog()


def get_unfinished_clazz(cursor):
    """
    get unfinished reported class
    :return: list of (class_name, teacher_name, dept_id, qq_group_id)
    """
    # get tomorrow date str
    now = datetime.datetime.now()
    tomorrow = now + datetime.timedelta(days=1)
    date = f'{tomorrow.year}.{tomorrow.month}.{tomorrow.day + 1}'
    # select clazz which report date not equal tomorrow str
    unfinished_clazz_sql = f'select clazz_name, teacher_name, dept_id, group_id, bot_port, `delete` from clazz where `date`<>"{date}" and teacher_name is not null '
    cursor.execute(unfinished_clazz_sql)
    unfinished_clazz = list(cursor.fetchall())
    return unfinished_clazz


def get_clazz_info(cursor, unfinished_clazz):
    """
    :param unfinished_clazz:
    :return:
    {
        'teacher': {
            'account_data': {
                'username': username
                'securitycode': password
                'captcha': ""
            },
            'clazz': {
                'clazz_name': {
                    'dept_id': dept_id,
                    'group_id': qq_group_id,
                    'bot_id': bot_id,
                    'group_id': group_id,
                    'student_info': [{name: qq}, {name, qq}, ...]
                }
            }
        }
    }
    """
    clazz_info = {}
    for i in unfinished_clazz:
        clazz = i[0]
        teacher = i[1]
        # select teacher's account info who not in clazz_info, get his/her account_data and init empty clazz
        if teacher not in list(clazz_info.keys()):
            clazz_info[teacher] = {}
            select_account = f'select username, password from account where teacher_name="{teacher}"'
            cursor.execute(select_account)
            username, securitycode = cursor.fetchone()
            clazz_info[teacher]['account_data'] = {
                'username': username,
                'securitycode': securitycode,
                'captcha': ''
            }
            clazz_info[teacher]['clazz'] = {}
        # select student of this class
        select_student = f'select student_name, student_qq from student where student_clazz="{clazz}" and remove = 0'
        cursor.execute(select_student)
        student_list = list(cursor.fetchall())
        # student_info is list fill with {name: qq}
        student_info = {}
        for j in student_list:
            student_info[j[0]] = j[1]

        select_remove_list = f'select student_name from student where remove = 1'
        cursor.execute(select_remove_list)
        remove_tuple_list = list(cursor.fetchall())
        remove_list = []
        for j in remove_tuple_list:
            remove_list.append(j[0])

        clazz_info[teacher]['clazz'][clazz] = {
            'dept_id': i[2],
            'group_id': i[3],
            'bot_id': i[4],
            'delete': i[5] == 1,
            'student_info': student_info,
            'remove_list': remove_list
        }
    return clazz_info


def run(cursor, connect_redis, channel):
    unfinished_clazz = get_unfinished_clazz(cursor)
    log.info(f"获取到未完成打卡的班级：{str(list(map(lambda x: x[0], unfinished_clazz)))}")
    clazz_info = get_clazz_info(cursor, unfinished_clazz)

    teacher_list = list(clazz_info.keys())

    for i in teacher_list:
        try:
            log.info(f"开始推送 [{i}]，{list(clazz_info[i]['clazz'].keys())}")
            AutoReport(i, clazz_info[i]['account_data'], clazz_info[i]['clazz'], cursor, connect_redis, channel).run()
        except Exception as e:
            log.error(e)

