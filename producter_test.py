import datetime
import time

import pika

connect_mq = pika.BlockingConnection(pika.ConnectionParameters(host="localhost", port=5672))
channel = connect_mq.channel()
# channel.queue_declare("hello")

message = {
    "groupId": "369746384",
    "msg": "",
    "delete": "true",
    "port": 5700
}

for i in range(10):
    message["msg"] = str(datetime.datetime.now())
    channel.basic_publish(exchange="", routing_key='hello', body=str(message).encode())
    print(message)
    time.sleep(2)

channel.close()
connect_mq.close()
