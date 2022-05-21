from flask import Flask, request, jsonify
import utils
from handle_log import HandleLog

import engine
import clazz_engine

log = HandleLog()

app = Flask(__name__)


@app.route('/get_unreported')
def get_unreported():
    connect_mysql, cursor, connect_redis, channel, connect_mq = utils.get_connect()
    engine.run(cursor, connect_redis, channel)
    utils.close_connect(connect_mysql, cursor, connect_redis, channel, connect_mq)
    return "no reply"


@app.route('/appoint_clazz')
def get_appoint_clazz():
    connect_mysql, cursor, connect_redis, channel, connect_mq = utils.get_connect()

    clazz_name, report_type, bot_id = request.args.get("clazz_name"), request.args.get("report_type"), request.args.get("bot_id"),

    print(clazz_name, report_type, bot_id)

    clazz_engine.run(cursor, connect_redis, channel, clazz_name, report_type, bot_id)

    utils.close_connect(connect_mysql, cursor, connect_redis, channel, connect_mq)
    return "no reply"


if __name__ == '__main__':
    app.run()
