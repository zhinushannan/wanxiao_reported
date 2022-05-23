import sys

import pymysql
import redis

from pymysql import OperationalError
from redis import ResponseError, AuthenticationError

import pika

from handle_log import HandleLog

log = HandleLog()


def get_connect():
    connect_mysql, cursor, connect_redis, channel, connect_mq = None, None, None, None, None
    # get connection to mysql
    try:
        connect_mysql = pymysql.connect(host='localhost', port=3306,
                                        user='root', passwd='09140727', db='report')
    except OperationalError as e:
        close_connect(connect_mysql, cursor, connect_redis, connect_mq, channel)
        log.error(e)
        sys.exit(-1)
    else:
        log.info("获取 Mysql 数据库链接成功！")
    cursor = connect_mysql.cursor()

    try:
        connect_redis = redis.Redis(host="127.0.0.1", port=6379, decode_responses=True)
        connect_redis.info()
    except (ResponseError, AuthenticationError) as e:
        close_connect(connect_mysql, cursor, connect_redis, connect_mq, channel)
        log.error(e)
        sys.exit(-1)
    else:
        log.info("获取 Redis 数据库链接成功！")

    try:
        auth =pika.PlainCredentials("test", "test")
        connect_mq = pika.BlockingConnection(pika.ConnectionParameters(host="localhost", port=5672, credentials=auth))
        channel = connect_mq.channel()
        channel.queue_declare("wanxiao_report", durable=True)
    except:
        close_connect(connect_mysql, cursor, connect_redis, connect_mq, channel)
        log.error("消息队列链接失败！")
        sys.exit(-1)
    else:
        log.info("获取 MQ 成功！")

    return connect_mysql, cursor, connect_redis, channel, connect_mq


def close_connect(connect_mysql, cursor, connect_redis, channel, connect_mq):
    if connect_mysql is not None:
        connect_mysql.close()
    if cursor is not None:
        cursor.close()
    if connect_redis is not None:
        connect_redis.close()
    if channel is not None:
        channel.close()
    if connect_mq is not None:
        connect_mq.close()

