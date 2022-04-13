import datetime

import pymysql
from nonebot import on_request
from nonebot.adapters.cqhttp import Bot, Event

from report_nb2.src.plugins import _settings

friend_match = on_request()


@friend_match.handle()
def get_message(bot: Bot, event: Event):
    flag = str(event.flag)
    bot_id = str(bot.self_id)
    type = 1 if event.request_type == "friend" else 0
    target_id = str(event.user_id)
    comment = str(event.comment)
    creat_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    sql = f"insert into bot_request (flag, bot_id, type, target_id, comment, create_time) value ({flag}, {bot_id}, {type}, {target_id}, '{comment}', '{creat_time}') "

    db = pymysql.connect(host=_settings.DB_HOST, port=_settings.DB_PORT, user=_settings.DB_USER, password=_settings.DB_PASSWD, database=_settings.DB_NAME)
    cursor = db.cursor()
    cursor.execute(sql)
    db.commit()
    cursor.close()
    db.close()
