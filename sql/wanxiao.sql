/*
Navicat MySQL Data Transfer

Source Server         : mysql8.0
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : wanxiao

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-04-10 18:02:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `teacher_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`teacher_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('赵思文', '2200000160', 'Kwxy2000');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
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

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('18数据', '赵思文', '2022.4.10', '147331', '825909952', '5701', '1');
INSERT INTO `clazz` VALUES ('19软件2', '赵思文', '2022.4.10', '147372', '1087894326', '2700', '0');
INSERT INTO `clazz` VALUES ('20数据', '赵思文', '2022.4.10', '236630', '945219184', '5700', '1');
INSERT INTO `clazz` VALUES ('20软件2', '赵思文', '2022.4.10', '236628', '953152455', '5700', '0');

-- ----------------------------
-- Table structure for remove
-- ----------------------------
DROP TABLE IF EXISTS `remove`;
CREATE TABLE `remove` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dept_id` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of remove
-- ----------------------------
INSERT INTO `remove` VALUES ('1', '236628', '徐志龙');
INSERT INTO `remove` VALUES ('2', '236628', '陈烨');
INSERT INTO `remove` VALUES ('3', '236628', '徐冰清');
INSERT INTO `remove` VALUES ('5', '236628', '黄潇');
INSERT INTO `remove` VALUES ('6', '236628', '崔波');
INSERT INTO `remove` VALUES ('7', '236628', '李锦松');
INSERT INTO `remove` VALUES ('8', '236628', '严雯');
INSERT INTO `remove` VALUES ('9', '236628', '刘嗣帅');
INSERT INTO `remove` VALUES ('10', '236628', '方浩扬');
INSERT INTO `remove` VALUES ('11', '236628', '孙创业');
INSERT INTO `remove` VALUES ('12', '236628', '张茂');
INSERT INTO `remove` VALUES ('13', '236628', '郑梦可');
INSERT INTO `remove` VALUES ('14', '236628', '王玮瑶');
INSERT INTO `remove` VALUES ('15', '236628', '赵林林');
INSERT INTO `remove` VALUES ('16', '236628', '郭雨钿');
INSERT INTO `remove` VALUES ('17', '236628', '张凤飞');
INSERT INTO `remove` VALUES ('18', '236628', '范金生');
INSERT INTO `remove` VALUES ('19', '236628', '王诗琪');
INSERT INTO `remove` VALUES ('20', '236628', '赵惠杰');
INSERT INTO `remove` VALUES ('21', '236628', '徐程');
INSERT INTO `remove` VALUES ('22', '236628', '张曼');
INSERT INTO `remove` VALUES ('23', '236628', '戴衍');
INSERT INTO `remove` VALUES ('24', '236628', '马晨曦');
INSERT INTO `remove` VALUES ('25', '236628', '何伟');
INSERT INTO `remove` VALUES ('26', '236628', '包智予');
INSERT INTO `remove` VALUES ('27', '236628', '陆小茜');
INSERT INTO `remove` VALUES ('28', '236628', '蔡紫旋');
INSERT INTO `remove` VALUES ('29', '236628', '蒋益');
INSERT INTO `remove` VALUES ('30', '236628', '关星月');
INSERT INTO `remove` VALUES ('31', '236628', '张阳');
INSERT INTO `remove` VALUES ('32', '236628', '朱茜茜');
INSERT INTO `remove` VALUES ('33', '236628', '李雅婧');
INSERT INTO `remove` VALUES ('34', '236628', '吴京');
INSERT INTO `remove` VALUES ('35', '236628', '孙爱琳');
INSERT INTO `remove` VALUES ('36', '236628', '陈华俊');
INSERT INTO `remove` VALUES ('37', '236628', '吴紫茜');
INSERT INTO `remove` VALUES ('38', '236628', '王迪');
INSERT INTO `remove` VALUES ('39', '236628', '行孟杰');
INSERT INTO `remove` VALUES ('40', '236628', '李晓婧');
INSERT INTO `remove` VALUES ('41', '236628', '陈蝶');
INSERT INTO `remove` VALUES ('42', '236628', '王嘉明');
INSERT INTO `remove` VALUES ('43', '236628', '李苏杰');
INSERT INTO `remove` VALUES ('44', '236628', '陈琦');
INSERT INTO `remove` VALUES ('45', '236628', '张洪婉');
INSERT INTO `remove` VALUES ('46', '236628', '谢逸凡');
INSERT INTO `remove` VALUES ('47', '236628', '陆雅婷');
INSERT INTO `remove` VALUES ('48', '236628', '马新萍');
INSERT INTO `remove` VALUES ('49', '236628', '陈爽');
INSERT INTO `remove` VALUES ('50', '236628', '彭海洋');
INSERT INTO `remove` VALUES ('51', '236628', '张磊');
INSERT INTO `remove` VALUES ('52', '236628', '沈依青');
INSERT INTO `remove` VALUES ('53', '236628', '吴征昊');
INSERT INTO `remove` VALUES ('54', '236628', '李雨欣');
INSERT INTO `remove` VALUES ('55', '236628', '黄艳阳');
INSERT INTO `remove` VALUES ('56', '236628', '孙重祎');
INSERT INTO `remove` VALUES ('57', '236628', '房晨宇');
INSERT INTO `remove` VALUES ('58', '236628', '江舟');
INSERT INTO `remove` VALUES ('59', '236628', '席浩迅');
INSERT INTO `remove` VALUES ('60', '236628', '李晓同');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_qq` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_clazz` varchar(255) NOT NULL,
  PRIMARY KEY (`student_qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1015611693', '李敏', '19软件2');
INSERT INTO `student` VALUES ('1018472132', '杨玉堃', '18数据');
INSERT INTO `student` VALUES ('1019943758', '夏雨', '18数据');
INSERT INTO `student` VALUES ('1021245536', '杜晓轩', '18数据');
INSERT INTO `student` VALUES ('1025951846', '罗劭彦', '18数据');
INSERT INTO `student` VALUES ('1025976605', '程莉莉', '19软件2');
INSERT INTO `student` VALUES ('1029692927', '黄楠', '18数据');
INSERT INTO `student` VALUES ('1029882083', '黄俊华', '18数据');
INSERT INTO `student` VALUES ('1031130284', '于雪莲', '18数据');
INSERT INTO `student` VALUES ('1046500875', '封一鸣', '18数据');
INSERT INTO `student` VALUES ('105130110', '朱晨曦', '18数据');
INSERT INTO `student` VALUES ('1056644093', '刘曜慧', '18数据');
INSERT INTO `student` VALUES ('1060864911', '李炎玺', '18数据');
INSERT INTO `student` VALUES ('1069395062', '申刘宝', '19软件2');
INSERT INTO `student` VALUES ('1091120294', '陆怡静', '20数据');
INSERT INTO `student` VALUES ('1093394030', '杨婷', '19软件2');
INSERT INTO `student` VALUES ('1095365233', '吕晗丽', '18数据');
INSERT INTO `student` VALUES ('1102969940', '周航', '18数据');
INSERT INTO `student` VALUES ('1115019327', '毛小伟', '19软件2');
INSERT INTO `student` VALUES ('1115448506', '陈强龙', '19软件2');
INSERT INTO `student` VALUES ('1136971913', '林欣雨', '20数据');
INSERT INTO `student` VALUES ('1149723425', '施承志', '20数据');
INSERT INTO `student` VALUES ('1161098105', '赵锐敏', '18数据');
INSERT INTO `student` VALUES ('1178127896', '杜宛蓉', '20数据');
INSERT INTO `student` VALUES ('1186433079', '周佳敏', '18数据');
INSERT INTO `student` VALUES ('1223141458', '唐夏懿', '20数据');
INSERT INTO `student` VALUES ('1226205679', '李若冰', '19软件2');
INSERT INTO `student` VALUES ('1226559461', '王晨宇', '19软件2');
INSERT INTO `student` VALUES ('1250651074', '黄亦婷', '19软件2');
INSERT INTO `student` VALUES ('1252594553', '颜千慧', '20数据');
INSERT INTO `student` VALUES ('1256066356', '朱宇', '20数据');
INSERT INTO `student` VALUES ('1262296187', '徐志龙', '20软件2');
INSERT INTO `student` VALUES ('1276674734', '王彩霞', '18数据');
INSERT INTO `student` VALUES ('1282261438', '汤欣怡', '19软件2');
INSERT INTO `student` VALUES ('1282676341', '陈烨', '20软件2');
INSERT INTO `student` VALUES ('1295154071', '李沛瑾', '18数据');
INSERT INTO `student` VALUES ('1303297380', '王雅琪', '20数据');
INSERT INTO `student` VALUES ('1327136691', '费宇豪', '19软件2');
INSERT INTO `student` VALUES ('1345666253', '王诗雨', '19软件2');
INSERT INTO `student` VALUES ('1348964821', '何雅珣', '20数据');
INSERT INTO `student` VALUES ('1356353729', '陈沛', '18数据');
INSERT INTO `student` VALUES ('1397245269', '张依婷', '19软件2');
INSERT INTO `student` VALUES ('1400305533', '徐卓宇', '20数据');
INSERT INTO `student` VALUES ('1401545517', '向肖晗', '19软件2');
INSERT INTO `student` VALUES ('1415123546', '杨璟燊', '19软件2');
INSERT INTO `student` VALUES ('1415674799', '杨淳惠', '18数据');
INSERT INTO `student` VALUES ('1424383589', '刘雅平', '19软件2');
INSERT INTO `student` VALUES ('1426018272', '刘盈', '18数据');
INSERT INTO `student` VALUES ('1443668388', '张涵', '19软件2');
INSERT INTO `student` VALUES ('1446982638', '王正', '19软件2');
INSERT INTO `student` VALUES ('1452046928', '徐冰清', '20软件2');
INSERT INTO `student` VALUES ('1458335158', '张彦伟', '20软件2');
INSERT INTO `student` VALUES ('1466002658', '陈佳裕', '20数据');
INSERT INTO `student` VALUES ('1474251744', '韩溢铢', '18数据');
INSERT INTO `student` VALUES ('1482193676', '黄潇', '20软件2');
INSERT INTO `student` VALUES ('1499593995', '崔波', '20软件2');
INSERT INTO `student` VALUES ('1500387706', '丁诺', '19软件2');
INSERT INTO `student` VALUES ('1501571050', '肖一', '18数据');
INSERT INTO `student` VALUES ('1505617741', '阮珠霖', '20数据');
INSERT INTO `student` VALUES ('1514443449', '陈世伦', '18数据');
INSERT INTO `student` VALUES ('1520379867', '李锦松', '20软件2');
INSERT INTO `student` VALUES ('1529212762', '贾晟毅', '19软件2');
INSERT INTO `student` VALUES ('1538573929', '童家定', '19软件2');
INSERT INTO `student` VALUES ('1546305908', '蒋亢杰', '18数据');
INSERT INTO `student` VALUES ('1546749868', '胡传钢', '18数据');
INSERT INTO `student` VALUES ('1563361033', '周艺博', '19软件2');
INSERT INTO `student` VALUES ('1569036587', '李佳慧', '20数据');
INSERT INTO `student` VALUES ('1569245448', '严雯', '20软件2');
INSERT INTO `student` VALUES ('1579217496', '江婉琪', '18数据');
INSERT INTO `student` VALUES ('1605051832', '史程伟', '18数据');
INSERT INTO `student` VALUES ('1606492982', '占帅帅', '19软件2');
INSERT INTO `student` VALUES ('1607720953', '陈炳寰', '18数据');
INSERT INTO `student` VALUES ('1617268588', '黄明娟', '19软件2');
INSERT INTO `student` VALUES ('1650293672', '赵茂', '20数据');
INSERT INTO `student` VALUES ('1654826315', '李希伟', '18数据');
INSERT INTO `student` VALUES ('1667459851', '周婷', '19软件2');
INSERT INTO `student` VALUES ('1695893380', '姚慧静', '19软件2');
INSERT INTO `student` VALUES ('1721488387', '彭曙光', '19软件2');
INSERT INTO `student` VALUES ('1729965457', '王晓晓', '19软件2');
INSERT INTO `student` VALUES ('1738675921', '刘建宏', '19软件2');
INSERT INTO `student` VALUES ('1741459386', '吴梦莹', '19软件2');
INSERT INTO `student` VALUES ('1755484895', '王丽萍', '18数据');
INSERT INTO `student` VALUES ('1757724386', '刘嗣帅', '20软件2');
INSERT INTO `student` VALUES ('1758003138', '王晓莉', '19软件2');
INSERT INTO `student` VALUES ('1776565000', '方浩扬', '20软件2');
INSERT INTO `student` VALUES ('1779849981', '刘雅婷', '19软件2');
INSERT INTO `student` VALUES ('1791260858', '蔡伟毅', '19软件2');
INSERT INTO `student` VALUES ('1799239677', '强璇', '19软件2');
INSERT INTO `student` VALUES ('1806881429', '樊科', '18数据');
INSERT INTO `student` VALUES ('1814783395', '高丽', '20数据');
INSERT INTO `student` VALUES ('183268352', '孙创业', '20软件2');
INSERT INTO `student` VALUES ('183505678', '乔靖杰', '18数据');
INSERT INTO `student` VALUES ('1848685904', '王建国', '18数据');
INSERT INTO `student` VALUES ('1908045405', '张茂', '20软件2');
INSERT INTO `student` VALUES ('1910822079', '韦松', '20数据');
INSERT INTO `student` VALUES ('1913800785', '周政伟', '19软件2');
INSERT INTO `student` VALUES ('1916757301', '宋子璇', '19软件2');
INSERT INTO `student` VALUES ('1924507651', '郑梦可', '20软件2');
INSERT INTO `student` VALUES ('1932586701', '严凯敏', '19软件2');
INSERT INTO `student` VALUES ('1959209891', '杜芷若', '20数据');
INSERT INTO `student` VALUES ('1969717812', '徐小琳', '19软件2');
INSERT INTO `student` VALUES ('2017630558', '高标', '18数据');
INSERT INTO `student` VALUES ('203516291', '朱林', '20数据');
INSERT INTO `student` VALUES ('2044435806', '王玮瑶', '20软件2');
INSERT INTO `student` VALUES ('2064363526', '苏颖', '19软件2');
INSERT INTO `student` VALUES ('206460950', '李渊睿', '19软件2');
INSERT INTO `student` VALUES ('209617832', '吕晨搏', '20数据');
INSERT INTO `student` VALUES ('2105339715', '赵林林', '20软件2');
INSERT INTO `student` VALUES ('2114940672', '方明暄', '20数据');
INSERT INTO `student` VALUES ('2145039799', '郭雨钿', '20软件2');
INSERT INTO `student` VALUES ('2194159100', '饶克奎', '20数据');
INSERT INTO `student` VALUES ('2196155975', '高萌', '20数据');
INSERT INTO `student` VALUES ('2210492129', '王晨雨', '18数据');
INSERT INTO `student` VALUES ('2213535079', '王辛薇', '20数据');
INSERT INTO `student` VALUES ('2215291943', '张丹', '20数据');
INSERT INTO `student` VALUES ('2234421866', '张凤飞', '20软件2');
INSERT INTO `student` VALUES ('2235357497', '范金生', '20软件2');
INSERT INTO `student` VALUES ('2251145976', '王诗琪', '20软件2');
INSERT INTO `student` VALUES ('2276872110', '高武', '19软件2');
INSERT INTO `student` VALUES ('2282161961', '赵惠杰', '20软件2');
INSERT INTO `student` VALUES ('2293923761', '徐程', '20软件2');
INSERT INTO `student` VALUES ('2303812413', '弓艾丽', '20数据');
INSERT INTO `student` VALUES ('2310701779', '翟林炜', '20数据');
INSERT INTO `student` VALUES ('2316501367', '张曼', '20软件2');
INSERT INTO `student` VALUES ('2320869780', '余烨滨', '20数据');
INSERT INTO `student` VALUES ('2371453667', '李佳奇', '20数据');
INSERT INTO `student` VALUES ('2376441554', '梁源', '18数据');
INSERT INTO `student` VALUES ('2380484650', '钱轶瑶', '20数据');
INSERT INTO `student` VALUES ('2396978763', '王瑀霖', '19软件2');
INSERT INTO `student` VALUES ('2410323243', '戴衍', '20软件2');
INSERT INTO `student` VALUES ('2411954749', '陈可昕', '20数据');
INSERT INTO `student` VALUES ('2418251700', '陈龙', '19软件2');
INSERT INTO `student` VALUES ('2419363229', '马慧奇', '18数据');
INSERT INTO `student` VALUES ('2428286276', '马晨曦', '20软件2');
INSERT INTO `student` VALUES ('2429711889', '左蓉蓉', '19软件2');
INSERT INTO `student` VALUES ('2430485450', '何伟', '20软件2');
INSERT INTO `student` VALUES ('2435257655', '顾佳君', '20数据');
INSERT INTO `student` VALUES ('2439194671', '周静雯', '20数据');
INSERT INTO `student` VALUES ('2440913157', '甄子函', '19软件2');
INSERT INTO `student` VALUES ('2453258835', '王娜', '19软件2');
INSERT INTO `student` VALUES ('2470684840', '包智予', '20软件2');
INSERT INTO `student` VALUES ('2473330993', '程轲', '18数据');
INSERT INTO `student` VALUES ('2481668585', '李云梦', '20数据');
INSERT INTO `student` VALUES ('2494344138', '陆小茜', '20软件2');
INSERT INTO `student` VALUES ('2499869608', '蔡紫旋', '20软件2');
INSERT INTO `student` VALUES ('2501116423', '蒋益', '20软件2');
INSERT INTO `student` VALUES ('2502439129', '李川', '18数据');
INSERT INTO `student` VALUES ('2508838514', '刘振林', '19软件2');
INSERT INTO `student` VALUES ('2509141720', '张妙妍', '19软件2');
INSERT INTO `student` VALUES ('2529315991', '关星月', '20软件2');
INSERT INTO `student` VALUES ('253918670', '徐卫中', '18数据');
INSERT INTO `student` VALUES ('2549590734', '杨畅', '19软件2');
INSERT INTO `student` VALUES ('2558464414', '张青鹏', '20数据');
INSERT INTO `student` VALUES ('2563787865', '张阳', '20软件2');
INSERT INTO `student` VALUES ('2571940933', '朱茜茜', '20软件2');
INSERT INTO `student` VALUES ('2585443920', '李雅婧', '20软件2');
INSERT INTO `student` VALUES ('2593658505', '吴京', '20软件2');
INSERT INTO `student` VALUES ('2608454567', '郑文婷', '19软件2');
INSERT INTO `student` VALUES ('2623279137', '张淘', '18数据');
INSERT INTO `student` VALUES ('2624509092', '吴可秦', '18数据');
INSERT INTO `student` VALUES ('2631509949', '徐傲', '19软件2');
INSERT INTO `student` VALUES ('2643109172', '孙爱琳', '20软件2');
INSERT INTO `student` VALUES ('2653090107', '陈华俊', '20软件2');
INSERT INTO `student` VALUES ('2667096287', '黄大俊', '20数据');
INSERT INTO `student` VALUES ('2673380881', '徐佳佳', '20数据');
INSERT INTO `student` VALUES ('2680260895', '吴紫茜', '20软件2');
INSERT INTO `student` VALUES ('2681248139', '江双双', '19软件2');
INSERT INTO `student` VALUES ('2734076433', '王迪', '20软件2');
INSERT INTO `student` VALUES ('2745716636', '石新伟', '18数据');
INSERT INTO `student` VALUES ('2753757325', '行孟杰', '20软件2');
INSERT INTO `student` VALUES ('2759638913', '李晓婧', '20软件2');
INSERT INTO `student` VALUES ('2781468167', '陈蝶', '20软件2');
INSERT INTO `student` VALUES ('2798976142', '孙茹茹', '20数据');
INSERT INTO `student` VALUES ('2826877129', '郝一鸣', '19软件2');
INSERT INTO `student` VALUES ('2834024743', '王嘉明', '20软件2');
INSERT INTO `student` VALUES ('2861321865', '梁梦华', '20数据');
INSERT INTO `student` VALUES ('2890514017', '师伟', '19软件2');
INSERT INTO `student` VALUES ('2895799065', '李海燕', '19软件2');
INSERT INTO `student` VALUES ('2900208198', '尉鑫鑫', '19软件2');
INSERT INTO `student` VALUES ('2904641709', '李苏杰', '20软件2');
INSERT INTO `student` VALUES ('2909235457', '陈琦', '20软件2');
INSERT INTO `student` VALUES ('2914543970', '杨程', '18数据');
INSERT INTO `student` VALUES ('291774830', '朱新茹', '19软件2');
INSERT INTO `student` VALUES ('2939934570', '张洪婉', '20软件2');
INSERT INTO `student` VALUES ('2943882089', '郭楠楠', '20数据');
INSERT INTO `student` VALUES ('2959674812', '谢逸凡', '20软件2');
INSERT INTO `student` VALUES ('2969787424', '陆雅婷', '20软件2');
INSERT INTO `student` VALUES ('2970237687', '宋盈盈', '20数据');
INSERT INTO `student` VALUES ('2981712004', '陈森', '20数据');
INSERT INTO `student` VALUES ('3029917557', '张娴', '19软件2');
INSERT INTO `student` VALUES ('3031531805', '马新萍', '20软件2');
INSERT INTO `student` VALUES ('3050870263', '王语', '18数据');
INSERT INTO `student` VALUES ('3062393700', '孔雪梅', '20数据');
INSERT INTO `student` VALUES ('309619403', '李康', '19软件2');
INSERT INTO `student` VALUES ('3120489147', '倪颖', '20数据');
INSERT INTO `student` VALUES ('3137791187', '褚奉民', '18数据');
INSERT INTO `student` VALUES ('3141158350', '陈爽', '20软件2');
INSERT INTO `student` VALUES ('3197367918', '张妍琳', '20数据');
INSERT INTO `student` VALUES ('320976399', '张泰铭', '18数据');
INSERT INTO `student` VALUES ('3253382667', '孟蕊', '20数据');
INSERT INTO `student` VALUES ('3270095598', '王必滨', '19软件2');
INSERT INTO `student` VALUES ('3285485668', '张丽华', '18数据');
INSERT INTO `student` VALUES ('3357457720', '贾燕霞', '20数据');
INSERT INTO `student` VALUES ('3383405990', '许霞', '19软件2');
INSERT INTO `student` VALUES ('3394532894', '段丽新', '20数据');
INSERT INTO `student` VALUES ('3407202995', '季泽', '20数据');
INSERT INTO `student` VALUES ('3431844035', '李紫微', '20数据');
INSERT INTO `student` VALUES ('3506676171', '卓庆', '19软件2');
INSERT INTO `student` VALUES ('3529411304', '朱帅波', '18数据');
INSERT INTO `student` VALUES ('3530637699', '陆雪艳', '19软件2');
INSERT INTO `student` VALUES ('3536987271', '范彩云', '20数据');
INSERT INTO `student` VALUES ('3600013695', '盛洁', '18数据');
INSERT INTO `student` VALUES ('421712537', '彭海洋', '20软件2');
INSERT INTO `student` VALUES ('441977824', '侯超', '19软件2');
INSERT INTO `student` VALUES ('446078839', '夏玮', '18数据');
INSERT INTO `student` VALUES ('465386317', '盛思浩', '20数据');
INSERT INTO `student` VALUES ('536327120', '王宇欣', '19软件2');
INSERT INTO `student` VALUES ('571807394', '张磊', '20软件2');
INSERT INTO `student` VALUES ('572713539', '付翔宇', '19软件2');
INSERT INTO `student` VALUES ('573507487', '丁雯', '18数据');
INSERT INTO `student` VALUES ('603226819', '陈力列', '19软件2');
INSERT INTO `student` VALUES ('630017682', '章宏霄', '19软件2');
INSERT INTO `student` VALUES ('673576534', '沈依青', '20软件2');
INSERT INTO `student` VALUES ('705192968', '徐雕', '20数据');
INSERT INTO `student` VALUES ('709887371', '王永辉', '18数据');
INSERT INTO `student` VALUES ('732788562', '吴征昊', '20软件2');
INSERT INTO `student` VALUES ('741349469', '李雨欣', '20软件2');
INSERT INTO `student` VALUES ('758766041', '黄艳阳', '20软件2');
INSERT INTO `student` VALUES ('763039204', '孙重祎', '20软件2');
INSERT INTO `student` VALUES ('769591414', '房晨宇', '20软件2');
INSERT INTO `student` VALUES ('809294927', '武小涵', '18数据');
INSERT INTO `student` VALUES ('809976462', '林海燕', '18数据');
INSERT INTO `student` VALUES ('819508292', '李鹏龙', '19软件2');
INSERT INTO `student` VALUES ('826694415', '周贞雄', '18数据');
INSERT INTO `student` VALUES ('840066450', '江舟', '20软件2');
INSERT INTO `student` VALUES ('848782369', '刘奔', '19软件2');
INSERT INTO `student` VALUES ('853579314', '席浩迅', '20软件2');
INSERT INTO `student` VALUES ('859437529', '何洋', '20数据');
INSERT INTO `student` VALUES ('876985645', '关心怡', '18数据');
INSERT INTO `student` VALUES ('907949114', '江培松', '19软件2');
INSERT INTO `student` VALUES ('934034168', '柯楠', '20数据');
INSERT INTO `student` VALUES ('960919037', '李晓同', '20软件2');
INSERT INTO `student` VALUES ('981961894', '何香泳', '19软件2');
INSERT INTO `student` VALUES ('985937844', '朱霏', '19软件2');
INSERT INTO `student` VALUES ('996344589', '单香怡', '19软件2');
