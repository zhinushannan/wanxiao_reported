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
INSERT INTO `account` VALUES ('张银钏','2180000011','Kwxy2000');
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
-- Table structure for table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz` (
  `clazz_name` varchar(255) NOT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `dept_id` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  `bot_port` varchar(255) NOT NULL,
  `delete` int NOT NULL COMMENT '开启撤回 1 \n关闭撤回 0',
  PRIMARY KEY (`clazz_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz`
--

LOCK TABLES `clazz` WRITE;
/*!40000 ALTER TABLE `clazz` DISABLE KEYS */;
INSERT INTO `clazz` VALUES ('19数据','张银钏','2022.5.31','147373','123456789','5700',1);
/*!40000 ALTER TABLE `clazz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_time`
--

DROP TABLE IF EXISTS `report_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_time` (
  `clazz_name` varchar(255) NOT NULL,
  `time` int NOT NULL COMMENT '提醒时间，从00:00开始为1，每半小时+1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_time`
--

LOCK TABLES `report_time` WRITE;
/*!40000 ALTER TABLE `report_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `student_qq` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_clazz` varchar(255) NOT NULL,
  `remove` int NOT NULL DEFAULT '0' COMMENT '是否不在班级内，0在，1不在\n（参军或休学或其他情况）',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('11111','11111','蔡家祈','19数据',0),('11112','11112','陈春婷','19数据',0),('11113','11113','陈俊仁','19数据',0),('11114','11114','陈欣','19数据',0),('11115','11115','戴继慧','19数据',0),('11116','11116','董禹','19数据',0),('11117','11117','段晓睿','19数据',0),('11118','11118','段瑜航','19数据',0),('11119','11119','高丹阳','19数据',0),('11120','11120','郭佳慧','19数据',0),('11121','11121','郝娜','19数据',0),('11122','11122','洪希','19数据',0),('11123','11123','黄靖雯','19数据',0),('11124','11124','蒋文艳','19数据',0),('11125','11125','蒋雨迪','19数据',0),('11126','11126','勒嘉琪','19数据',0),('11127','11127','刘华慧','19数据',0),('11128','11128','刘思源','19数据',0),('11129','11129','刘婷','19数据',0),('11130','11130','刘祎萍','19数据',0),('11131','11131','罗克素','19数据',0),('11132','11132','罗茂财','19数据',0),('11133','11133','马越','19数据',0),('11134','11134','毛欣欣','19数据',0),('11135','11135','普耀龙','19数据',0),('11136','11136','秦沙沙','19数据',0),('11137','11137','饶书菡','19数据',0),('11138','11138','佘亚姐','19数据',0),('11139','11139','申茂','19数据',0),('11140','11140','王贺芳','19数据',0),('11142','11142','王婧宜','19数据',0),('11143','11143','王梅','19数据',0),('11144','11144','王晓云','19数据',0),('11145','11145','王妍','19数据',0),('11146','11146','王逸鹤','19数据',0),('11147','11147','韦炫安','19数据',0),('11148','11148','武通通','19数据',0),('11149','11149','谢雨婷','19数据',0),('11150','11150','徐辅明','19数据',0),('11151','11151','徐陆孝','19数据',0),('11152','11152','徐心悦','19数据',0),('11153','11153','姚方','19数据',0),('11154','11154','易彤浍','19数据',0),('11155','11155','游莹莹','19数据',0),('11156','11156','张新悦','19数据',0),('11157','11157','赵海源','19数据',0),('11158','11158','赵哲','19数据',0),('11159','11159','周煦','19数据',0),('11160','11160','周舟','19数据',0),('11161','11161','庄楠楠','19数据',0),('1377875184','1377875184','王建文','19数据',0);
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

-- Dump completed on 2022-06-02  1:18:23
