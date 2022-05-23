import re

from flask import Flask, request, jsonify
import utils
from handle_log import HandleLog

import engine
import clazz_engine

log = HandleLog()

app = Flask(__name__)


@app.route('/get_unreported', methods=["POST"])
def get_unreported():
    connect_mysql, cursor, connect_redis, channel, connect_mq = utils.get_connect()
    class_list = request.get_json()
    classes = str(class_list).replace("[", "(").replace("]", ")")
    engine.run(cursor, connect_redis, channel, classes)
    utils.close_connect(connect_mysql, cursor, connect_redis, channel, connect_mq)
    return "no reply"


@app.route('/appoint_clazz')
def get_appoint_clazz():
    connect_mysql, cursor, connect_redis, channel, connect_mq = utils.get_connect()

    clazz_name, report_type, bot_port, group_id = request.args.get("clazz_name"), request.args.get("report_type"), request.args.get(
        "bot_port"), request.args.get("group_id")

    msg = clazz_engine.run(cursor, connect_redis, channel, clazz_name, report_type, bot_port, group_id)

    utils.close_connect(connect_mysql, cursor, connect_redis, channel, connect_mq)

    msg = msg.replace("\n", "<br/>").replace("\t", "&nbsp;&nbsp;")

    pattern = re.compile('\[CQ:at,qq=\d+,name=[\u4e00-\u9fa5]+]')
    str = msg
    name_list = pattern.findall(str)
    for i in name_list:
        qq = i.split(",")[1].split("=")[1]
        name = "@" + i.split(",")[2].split("=")[1].replace("]", "")
        replace_str = f"<span title='QQ:{qq}'>{name}</span>"
        str = str.replace(i, replace_str)

    return {"raw": msg, "show": str}


if __name__ == '__main__':
    app.run()
