# 健康打卡机器人停止运营
2022年12月16日，接学校通知，即日起健康打卡不需上报。健康打卡自动提醒机器人停止运营。

健康打卡不在继续，但大家要继续保护自己的健康。

感谢三年来党和政府为保护我们所做的一切，感谢学校为我们提供一个安全、健康的学习环境，再也不见，健康打卡小助手👋🏻👋🏻

<div align="center"><h1 align="center">🦠🤺轻量化健康打卡自动提醒机器人（完美校园版）</h1>


[![](https://img.shields.io/badge/Author-zhinushannan-red)](https://github.com/zhinushannan/)
[![](https://img.shields.io/github/license/zhinushannan/wanxiao_reported)](https://github.com/zhinushannan/wanxiao_reported/blob/main/LICENSE)
[![](https://www.oscs1024.com/platform/badge/zhinushannan/wanxiao_reported.svg)](https://www.oscs1024.com/cd/1528036731427835904)
</div>


## ⚽️项目介绍

&emsp;&emsp;根据世界卫生组织，2019年12月31日，中国武汉市卫健委报告了湖北省武汉市的一组肺炎病例，最终确认了一种新型冠状病毒。

&emsp;&emsp;2020年2月7日，杭州市委书记周江勇在防疫例会上提出要推出市民健康码。9日，腾讯公司在深圳推出“防疫健康码”，深圳是全国首个推行“防疫健康码”的城市。“防疫健康码”的出现，对疫情的防控起到了至关重要的作用。

&emsp;&emsp;2020年3月4日，江苏师范大学科文学院使用完美校园平台实行每日健康上报，实现了全面了解学生防疫状况。通过完美校园平台每日的健康上报，可以及时了解在校人员的健康状况，对于校园疫情防控具有重要意义。

&emsp;&emsp;实行每日健康上报制度是学院出于全面了解学生和教职工的防疫状况、保证学生健康安全的一项重要举措，然而，在实际的执行过程中，有些同学或教职工会受到一些不可避免的情况的干扰而忘记打卡，需要辅导员老师和相关班委的提醒。此项工作比较繁琐，且辅导员老师或班委也存在忘记提醒的情况，导致有些同学忘记打卡，需要系级老师去通报提醒，浪费了大量的人力。**本项目旨在帮助使用完美校园打卡的在校师生提供帮助，每天定时进行自动提醒，从每天提醒打卡的压力中解放出来，全身心地投入到社会主义建设之中去。**

&emsp;&emsp;该项目于2021年2月开始设计并研发，项目名为基于QQ群的健康打卡自动提醒机器人，使用近1个月后，因技术原因暂停维护。 2021年6月23日，我光荣的加入了党组织。出于为人民服务的信念，我重启了该项目的研发，最终于8月形成稳定的、基于QQ群的轻量化健康打卡自动提醒机器人。 11月推广使用，目前仅在科文学院内，已经服务于近60个班级、3500人。

&emsp;&emsp;本项目基于nonebot2和go-cqhttp（实际线上版本是基于nonebot2修改的，比原版更稳定），使用了 `requests` 第三方库。

&emsp;&emsp;项目设计不容忍数据不完整，即如果班级信息与线上后台信息不符，将会直接报错终止运行。该项目架构如下：


<div align="center"><img src="https://pic-go-zhinushannan-1304360121.cos.ap-nanjing.myqcloud.com/202208/202207251632440.png" alt="image-20220725163238483" style="width:50%;" /></div>



## 💫项目功能

* [x] 完美校园管理后台模拟登录获取 token
* [x] 自动获取当前班级未打卡的名单
* [x] 在班级QQ群内@指定同学进行提醒
* [x] 支持多班级打卡
* [x] 支持Bark推送程序异常

### YouTube或Bilibili
YouTube地址：https://www.youtube.com/watch?v=rkoMG7Smw6o   
Bilibili地址：https://www.bilibili.com/video/BV1Mg411r7EK   

[![](https://res.cloudinary.com/marcomontalbano/image/upload/v1661094833/video_to_markdown/images/youtube--rkoMG7Smw6o-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://www.youtube.com/watch?v=rkoMG7Smw6o "")



## 🛠项目维护

目前此项目由江苏师范大学科文学院实干青年众创空间工作室维护。

## 🏆项目成果

[入围江苏师范大学科文学院人工智能与软件学院开展“我的青春故事”系列活动](http://kwxy.jsnu.edu.cn/39/82/c7398a342402/page.htm)

> **立足专业，我为抗疫献力量。**为了解决疫情常态化防控下师生每日健康打卡容易遗忘的问题，学院动员、鼓励学生发挥聪明才智，出谋划策，建言献策。2019级数据科学与大数据技术专业班长、中共预备党员王建文同学积极响应，充分发挥专业优势，将所学知识和实践应用相结合，自主研发、设计了用于提醒师生每日健康打卡的实用软件。软件实现了全程自动化操作，每天定时精准提醒未打卡师生。该软件在学院广泛应用，是我院学子贡献专业智慧助力抗疫的典型，为利用技术化手段帮助师生养成打卡习惯，助力校园疫情防控常态化管理贡献了自己的青春智慧。

科文学院第七届东软睿道杯二等奖

科文学院第七届东软睿道杯最佳创意奖

## 🧘只可意会不可言传的项目

我称之为轻量化健康打卡自动机器人：[🌈17wanxiaoCheckin](https://github.com/ReaJason/17wanxiaoCheckin)

