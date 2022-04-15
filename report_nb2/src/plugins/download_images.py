from nonebot import on_message
from nonebot.adapters.cqhttp import Bot, GroupMessageEvent
import requests
import os

message_match = on_message()


@message_match.handle()
def get_message(bot: Bot, event: GroupMessageEvent):
    print(bot.self_id)
    url = str(event.message).split(",")[2].split("=")[1]
    img = requests.get(url).content
    path = "/home/zhinushannan/Desktop/xxqg/0413/"

    if not os.path.exists(path):
        os.mkdir(path)

    path += url.split("/")[-2] + ".png"
    print(url)
    with open(path, "wb") as f:
        f.write(img)

    os.system("python ../main.py " + path)

    pass
