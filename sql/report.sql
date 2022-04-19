-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: report
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `report`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `report` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `report`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `teacher_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`teacher_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('赵思文','2200000160','Kwxy2000');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bot`
--

DROP TABLE IF EXISTS `bot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bot` (
  `bot_id` varchar(255) NOT NULL,
  `port` int NOT NULL,
  `status` int DEFAULT NULL COMMENT '1正常，0停止\n',
  PRIMARY KEY (`bot_id`),
  UNIQUE KEY `bot_port_uindex` (`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器人列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bot`
--

LOCK TABLES `bot` WRITE;
/*!40000 ALTER TABLE `bot` DISABLE KEYS */;
INSERT INTO `bot` VALUES ('2793287265',5700,1);
/*!40000 ALTER TABLE `bot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bot_request`
--

DROP TABLE IF EXISTS `bot_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bot_request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `flag` varchar(255) NOT NULL,
  `bot_id` varchar(255) NOT NULL,
  `type` int NOT NULL COMMENT '1-friend\n0-group',
  `target_id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bot_request`
--

LOCK TABLES `bot_request` WRITE;
/*!40000 ALTER TABLE `bot_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `bot_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz` (
  `clazz_name` varchar(255) NOT NULL,
  `teacher_name` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `dept_id` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  `bot_id` varchar(255) NOT NULL,
  `delete` int NOT NULL,
  PRIMARY KEY (`clazz_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz`
--

LOCK TABLES `clazz` WRITE;
/*!40000 ALTER TABLE `clazz` DISABLE KEYS */;
INSERT INTO `clazz` VALUES ('18数据','赵思文','2022.4.10','147331','825909952','5701',1),('19软件2','赵思文','2022.4.10','147372','1087894326','2700',0),('20数据','赵思文','2022.4.10','236630','945219184','5700',1),('20软件2','赵思文','2022.4.10','236628','953152455','5700',0);
/*!40000 ALTER TABLE `clazz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_list`
--

DROP TABLE IF EXISTS `friend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_list` (
  `bot_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bot_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_list`
--

LOCK TABLES `friend_list` WRITE;
/*!40000 ALTER TABLE `friend_list` DISABLE KEYS */;
INSERT INTO `friend_list` VALUES ('2793287265','1377875184','1377875184','治怒善男',NULL),('2793287265','1427774041','1427774041','ice_water',NULL),('2793287265','2793287265','2793287265','治怒善男',NULL),('2793287265','66600000','66600000','babyQ',NULL);
/*!40000 ALTER TABLE `friend_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_list`
--

DROP TABLE IF EXISTS `group_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_list` (
  `bot_id` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `max_member_count` int NOT NULL,
  `member_count` int NOT NULL,
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bot_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_list`
--

LOCK TABLES `group_list` WRITE;
/*!40000 ALTER TABLE `group_list` DISABLE KEYS */;
INSERT INTO `group_list` VALUES ('2793287265','1003209156','小窝',200,6,NULL),('2793287265','1030838056','*',200,4,NULL),('2793287265','369746384','实干青年 - 老年人活动中心',200,7,NULL);
/*!40000 ALTER TABLE `group_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bot_id` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `target_id` varchar(255) NOT NULL COMMENT '发送目标，可能是群组也可能是私信，为群号或QQ账号\n',
  `type` int NOT NULL COMMENT '0是群组，1是私信',
  `send_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='使用机器人发送的消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remove`
--

DROP TABLE IF EXISTS `remove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remove` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dept_id` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remove`
--

LOCK TABLES `remove` WRITE;
/*!40000 ALTER TABLE `remove` DISABLE KEYS */;
INSERT INTO `remove` VALUES (1,'236628','徐志龙'),(2,'236628','陈烨'),(3,'236628','徐冰清'),(5,'236628','黄潇'),(6,'236628','崔波'),(7,'236628','李锦松'),(8,'236628','严雯'),(9,'236628','刘嗣帅'),(10,'236628','方浩扬'),(11,'236628','孙创业'),(12,'236628','张茂'),(13,'236628','郑梦可'),(14,'236628','王玮瑶'),(15,'236628','赵林林'),(16,'236628','郭雨钿'),(17,'236628','张凤飞'),(18,'236628','范金生'),(19,'236628','王诗琪'),(20,'236628','赵惠杰'),(21,'236628','徐程'),(22,'236628','张曼'),(23,'236628','戴衍'),(24,'236628','马晨曦'),(25,'236628','何伟'),(26,'236628','包智予'),(27,'236628','陆小茜'),(28,'236628','蔡紫旋'),(29,'236628','蒋益'),(30,'236628','关星月'),(31,'236628','张阳'),(32,'236628','朱茜茜'),(33,'236628','李雅婧'),(34,'236628','吴京'),(35,'236628','孙爱琳'),(36,'236628','陈华俊'),(37,'236628','吴紫茜'),(38,'236628','王迪'),(39,'236628','行孟杰'),(40,'236628','李晓婧'),(41,'236628','陈蝶'),(42,'236628','王嘉明'),(43,'236628','李苏杰'),(44,'236628','陈琦'),(45,'236628','张洪婉'),(46,'236628','谢逸凡'),(47,'236628','陆雅婷'),(48,'236628','马新萍'),(49,'236628','陈爽'),(50,'236628','彭海洋'),(51,'236628','张磊'),(52,'236628','沈依青'),(53,'236628','吴征昊'),(54,'236628','李雨欣'),(55,'236628','黄艳阳'),(56,'236628','孙重祎'),(57,'236628','房晨宇'),(58,'236628','江舟'),(59,'236628','席浩迅'),(60,'236628','李晓同');
/*!40000 ALTER TABLE `remove` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_qq` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_clazz` varchar(255) NOT NULL,
  PRIMARY KEY (`student_qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1015611693','李敏','19软件2'),('1018472132','杨玉堃','18数据'),('1019943758','夏雨','18数据'),('1021245536','杜晓轩','18数据'),('1025951846','罗劭彦','18数据'),('1025976605','程莉莉','19软件2'),('1029692927','黄楠','18数据'),('1029882083','黄俊华','18数据'),('1031130284','于雪莲','18数据'),('1046500875','封一鸣','18数据'),('105130110','朱晨曦','18数据'),('1056644093','刘曜慧','18数据'),('1060864911','李炎玺','18数据'),('1069395062','申刘宝','19软件2'),('1091120294','陆怡静','20数据'),('1093394030','杨婷','19软件2'),('1095365233','吕晗丽','18数据'),('1102969940','周航','18数据'),('1115019327','毛小伟','19软件2'),('1115448506','陈强龙','19软件2'),('1136971913','林欣雨','20数据'),('1149723425','施承志','20数据'),('1161098105','赵锐敏','18数据'),('1178127896','杜宛蓉','20数据'),('1186433079','周佳敏','18数据'),('1223141458','唐夏懿','20数据'),('1226205679','李若冰','19软件2'),('1226559461','王晨宇','19软件2'),('1250651074','黄亦婷','19软件2'),('1252594553','颜千慧','20数据'),('1256066356','朱宇','20数据'),('1262296187','徐志龙','20软件2'),('1276674734','王彩霞','18数据'),('1282261438','汤欣怡','19软件2'),('1282676341','陈烨','20软件2'),('1295154071','李沛瑾','18数据'),('1303297380','王雅琪','20数据'),('1327136691','费宇豪','19软件2'),('1345666253','王诗雨','19软件2'),('1348964821','何雅珣','20数据'),('1356353729','陈沛','18数据'),('1397245269','张依婷','19软件2'),('1400305533','徐卓宇','20数据'),('1401545517','向肖晗','19软件2'),('1415123546','杨璟燊','19软件2'),('1415674799','杨淳惠','18数据'),('1424383589','刘雅平','19软件2'),('1426018272','刘盈','18数据'),('1443668388','张涵','19软件2'),('1446982638','王正','19软件2'),('1452046928','徐冰清','20软件2'),('1458335158','张彦伟','20软件2'),('1466002658','陈佳裕','20数据'),('1474251744','韩溢铢','18数据'),('1482193676','黄潇','20软件2'),('1499593995','崔波','20软件2'),('1500387706','丁诺','19软件2'),('1501571050','肖一','18数据'),('1505617741','阮珠霖','20数据'),('1514443449','陈世伦','18数据'),('1520379867','李锦松','20软件2'),('1529212762','贾晟毅','19软件2'),('1538573929','童家定','19软件2'),('1546305908','蒋亢杰','18数据'),('1546749868','胡传钢','18数据'),('1563361033','周艺博','19软件2'),('1569036587','李佳慧','20数据'),('1569245448','严雯','20软件2'),('1579217496','江婉琪','18数据'),('1605051832','史程伟','18数据'),('1606492982','占帅帅','19软件2'),('1607720953','陈炳寰','18数据'),('1617268588','黄明娟','19软件2'),('1650293672','赵茂','20数据'),('1654826315','李希伟','18数据'),('1667459851','周婷','19软件2'),('1695893380','姚慧静','19软件2'),('1721488387','彭曙光','19软件2'),('1729965457','王晓晓','19软件2'),('1738675921','刘建宏','19软件2'),('1741459386','吴梦莹','19软件2'),('1755484895','王丽萍','18数据'),('1757724386','刘嗣帅','20软件2'),('1758003138','王晓莉','19软件2'),('1776565000','方浩扬','20软件2'),('1779849981','刘雅婷','19软件2'),('1791260858','蔡伟毅','19软件2'),('1799239677','强璇','19软件2'),('1806881429','樊科','18数据'),('1814783395','高丽','20数据'),('183268352','孙创业','20软件2'),('183505678','乔靖杰','18数据'),('1848685904','王建国','18数据'),('1908045405','张茂','20软件2'),('1910822079','韦松','20数据'),('1913800785','周政伟','19软件2'),('1916757301','宋子璇','19软件2'),('1924507651','郑梦可','20软件2'),('1932586701','严凯敏','19软件2'),('1959209891','杜芷若','20数据'),('1969717812','徐小琳','19软件2'),('2017630558','高标','18数据'),('203516291','朱林','20数据'),('2044435806','王玮瑶','20软件2'),('2064363526','苏颖','19软件2'),('206460950','李渊睿','19软件2'),('209617832','吕晨搏','20数据'),('2105339715','赵林林','20软件2'),('2114940672','方明暄','20数据'),('2145039799','郭雨钿','20软件2'),('2194159100','饶克奎','20数据'),('2196155975','高萌','20数据'),('2210492129','王晨雨','18数据'),('2213535079','王辛薇','20数据'),('2215291943','张丹','20数据'),('2234421866','张凤飞','20软件2'),('2235357497','范金生','20软件2'),('2251145976','王诗琪','20软件2'),('2276872110','高武','19软件2'),('2282161961','赵惠杰','20软件2'),('2293923761','徐程','20软件2'),('2303812413','弓艾丽','20数据'),('2310701779','翟林炜','20数据'),('2316501367','张曼','20软件2'),('2320869780','余烨滨','20数据'),('2371453667','李佳奇','20数据'),('2376441554','梁源','18数据'),('2380484650','钱轶瑶','20数据'),('2396978763','王瑀霖','19软件2'),('2410323243','戴衍','20软件2'),('2411954749','陈可昕','20数据'),('2418251700','陈龙','19软件2'),('2419363229','马慧奇','18数据'),('2428286276','马晨曦','20软件2'),('2429711889','左蓉蓉','19软件2'),('2430485450','何伟','20软件2'),('2435257655','顾佳君','20数据'),('2439194671','周静雯','20数据'),('2440913157','甄子函','19软件2'),('2453258835','王娜','19软件2'),('2470684840','包智予','20软件2'),('2473330993','程轲','18数据'),('2481668585','李云梦','20数据'),('2494344138','陆小茜','20软件2'),('2499869608','蔡紫旋','20软件2'),('2501116423','蒋益','20软件2'),('2502439129','李川','18数据'),('2508838514','刘振林','19软件2'),('2509141720','张妙妍','19软件2'),('2529315991','关星月','20软件2'),('253918670','徐卫中','18数据'),('2549590734','杨畅','19软件2'),('2558464414','张青鹏','20数据'),('2563787865','张阳','20软件2'),('2571940933','朱茜茜','20软件2'),('2585443920','李雅婧','20软件2'),('2593658505','吴京','20软件2'),('2608454567','郑文婷','19软件2'),('2623279137','张淘','18数据'),('2624509092','吴可秦','18数据'),('2631509949','徐傲','19软件2'),('2643109172','孙爱琳','20软件2'),('2653090107','陈华俊','20软件2'),('2667096287','黄大俊','20数据'),('2673380881','徐佳佳','20数据'),('2680260895','吴紫茜','20软件2'),('2681248139','江双双','19软件2'),('2734076433','王迪','20软件2'),('2745716636','石新伟','18数据'),('2753757325','行孟杰','20软件2'),('2759638913','李晓婧','20软件2'),('2781468167','陈蝶','20软件2'),('2798976142','孙茹茹','20数据'),('2826877129','郝一鸣','19软件2'),('2834024743','王嘉明','20软件2'),('2861321865','梁梦华','20数据'),('2890514017','师伟','19软件2'),('2895799065','李海燕','19软件2'),('2900208198','尉鑫鑫','19软件2'),('2904641709','李苏杰','20软件2'),('2909235457','陈琦','20软件2'),('2914543970','杨程','18数据'),('291774830','朱新茹','19软件2'),('2939934570','张洪婉','20软件2'),('2943882089','郭楠楠','20数据'),('2959674812','谢逸凡','20软件2'),('2969787424','陆雅婷','20软件2'),('2970237687','宋盈盈','20数据'),('2981712004','陈森','20数据'),('3029917557','张娴','19软件2'),('3031531805','马新萍','20软件2'),('3050870263','王语','18数据'),('3062393700','孔雪梅','20数据'),('309619403','李康','19软件2'),('3120489147','倪颖','20数据'),('3137791187','褚奉民','18数据'),('3141158350','陈爽','20软件2'),('3197367918','张妍琳','20数据'),('320976399','张泰铭','18数据'),('3253382667','孟蕊','20数据'),('3270095598','王必滨','19软件2'),('3285485668','张丽华','18数据'),('3357457720','贾燕霞','20数据'),('3383405990','许霞','19软件2'),('3394532894','段丽新','20数据'),('3407202995','季泽','20数据'),('3431844035','李紫微','20数据'),('3506676171','卓庆','19软件2'),('3529411304','朱帅波','18数据'),('3530637699','陆雪艳','19软件2'),('3536987271','范彩云','20数据'),('3600013695','盛洁','18数据'),('421712537','彭海洋','20软件2'),('441977824','侯超','19软件2'),('446078839','夏玮','18数据'),('465386317','盛思浩','20数据'),('536327120','王宇欣','19软件2'),('571807394','张磊','20软件2'),('572713539','付翔宇','19软件2'),('573507487','丁雯','18数据'),('603226819','陈力列','19软件2'),('630017682','章宏霄','19软件2'),('673576534','沈依青','20软件2'),('705192968','徐雕','20数据'),('709887371','王永辉','18数据'),('732788562','吴征昊','20软件2'),('741349469','李雨欣','20软件2'),('758766041','黄艳阳','20软件2'),('763039204','孙重祎','20软件2'),('769591414','房晨宇','20软件2'),('809294927','武小涵','18数据'),('809976462','林海燕','18数据'),('819508292','李鹏龙','19软件2'),('826694415','周贞雄','18数据'),('840066450','江舟','20软件2'),('848782369','刘奔','19软件2'),('853579314','席浩迅','20软件2'),('859437529','何洋','20数据'),('876985645','关心怡','18数据'),('907949114','江培松','19软件2'),('934034168','柯楠','20数据'),('960919037','李晓同','20软件2'),('981961894','何香泳','19软件2'),('985937844','朱霏','19软件2'),('996344589','单香怡','19软件2');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-19  8:14:30
