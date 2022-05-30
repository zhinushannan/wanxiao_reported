#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import cv2
import numpy as np
from cnocr import CnOcr
import sys
import datetime
import os

src = cv2.imread(sys.argv[1])
# cv2.namedWindow("input", cv2.WINDOW_AUTOSIZE)
cv2.imshow("input", src)
"""
提取图中的红色部分
"""
hsv = cv2.cvtColor(src, cv2.COLOR_BGR2HSV)
# 紫色
low_hsv = np.array([115, 115, 110])
high_hsv = np.array([125, 255, 255])
mask = cv2.inRange(hsv, lowerb=low_hsv, upperb=high_hsv)
cv2.imwrite("test.png", mask)

ocr = CnOcr()
res = ocr.ocr("./test.png")
name = "".join(res[0][0])

# 红色
low_hsv = np.array([0, 43, 46])
high_hsv = np.array([10, 255, 255])
mask = cv2.inRange(hsv, lowerb=low_hsv, upperb=high_hsv)
cv2.imwrite("test.png", mask)

ocr = CnOcr()
res = ocr.ocr("./test.png")
for i in res:
    if "当日积分" in "".join(i[0]):
        score = "".join(i[0]).split(":")[-1].split("：")[-1]

now = datetime.datetime.today()
today = f"{str(now.month).zfill(2)}{str(now.day).zfill(2)}"
path = f"/home/zhinushannan/Desktop/xxqg/{today}/{today}.csv"
with open(path, mode="a") as f:
    f.write(f"{name},{score}\n")

os.rename(sys.argv[1], f"/home/zhinushannan/Desktop/xxqg/{today}/{name}.png")
print(name, score)
print("已成功写入", path)

sys.exit(0)