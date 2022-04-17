from nonebot import on_message
from nonebot.adapters.onebot.v11 import Bot, GroupMessageEvent
import requests
import os
import datetime

message_match = on_message()


@message_match.handle()
def get_message(bot: Bot, event: GroupMessageEvent):
    if str(event.group_id) != "1030838056":
        return
    url = str(event.message).split(",")[2].split("=")[1]
    img = requests.get(url).content

    now = datetime.datetime.today()
    today = f"{str(now.month).zfill(2)}{str(now.day).zfill(2)}"
    path = f"/home/zhinushannan/Desktop/xxqg/{today}/"

    print(path)

    if not os.path.exists(path):
        os.mkdir(path)

    path += url.split("/")[-2] + ".png"
    print(url)
    with open(path, "wb") as f:
        f.write(img)

    os.system("python ocr-xxqg/main.py " + path)

    pass

