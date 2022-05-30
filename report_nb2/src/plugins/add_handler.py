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

    sql = "select port from bot where bot_id = " + bot_id
    db = pymysql.connect(host=DB_HOST, port=DB_PORT, user=DB_USER,
                         password=DB_PASSWD, database=DB_NAME)
    cursor = db.cursor()
    cursor.execute(sql)
    port = cursor.fetchone()[0]
    cursor.close()
    db.close()

    url = "http://127.0.0.1:" + str(port)

    if event.request_type == "friend":
        url += "/set_friend_add_request?approve=true&flag=" + flag
    else:
        url += "/set_group_add_request?approve=true&sub_type=invite&flag=" + flag

    requests.get(url)
