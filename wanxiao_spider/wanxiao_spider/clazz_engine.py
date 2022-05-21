# coding=utf-8

from handle_log import HandleLog
from auto_reported import AutoReport

log = HandleLog()


def get_info(cursor, clazz_name):
    clazz_sql = f"select teacher_name, dept_id from clazz where clazz_name = '{clazz_name}'"
    cursor.execute(clazz_sql)
    teacher_name, dept_id = cursor.fetchone()

    account_sql = f"select username, password from account where teacher_name = '{teacher_name}'"
    cursor.execute(account_sql)
    username, password = cursor.fetchone()

    student_sql = f'select student_name, student_qq from student where student_clazz="{clazz_name}" and remove = 0'
    cursor.execute(student_sql)
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

    teacher = teacher_name
    account_info = {'username': username, 'securitycode': password, 'captcha': ''}
    clazz = {
        clazz_name: {
            'dept_id': dept_id,
            'group_id': '',
            'bot_id': '',
            'student_info': student_info,
            'remove_list': remove_list
        }
    }

    return teacher, account_info, clazz


def run(cursor, connect_redis, channel, clazz_name, report_type, bot_id):
    teacher, account_info, clazz = get_info(cursor, clazz_name)
    clazz[clazz_name]["bot_id"] = bot_id
    clazz[clazz_name]["delete"] = 0
    log.info(f"开始推送 [{teacher}]，{clazz}")
    msg = AutoReport(teacher, account_info, clazz, cursor, connect_redis, channel, report_type).run()
    return msg






