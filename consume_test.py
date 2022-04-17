
import pika

connect_mq = pika.BlockingConnection(pika.ConnectionParameters(host="localhost", port=5672))
channel = connect_mq.channel()
# channel.queue_declare("hello")


channel.consume("hello", auto_ack=True)

channel.close()
connect_mq.close()
