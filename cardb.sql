/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : cardb

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 05/08/2023 10:11:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cartype
-- ----------------------------
DROP TABLE IF EXISTS `cartype`;
CREATE TABLE `cartype`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of cartype
-- ----------------------------
INSERT INTO `cartype` VALUES (4, '宝马');
INSERT INTO `cartype` VALUES (5, '奔驰');
INSERT INTO `cartype` VALUES (6, '大众');
INSERT INTO `cartype` VALUES (7, '现代');
INSERT INTO `cartype` VALUES (8, '法拉利');
INSERT INTO `cartype` VALUES (9, '布加迪');

-- ----------------------------
-- Table structure for carxl
-- ----------------------------
DROP TABLE IF EXISTS `carxl`;
CREATE TABLE `carxl`  (
  `xid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `xname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `fk_carxlaid`(`aid`) USING BTREE,
  CONSTRAINT `fk_carxlaid` FOREIGN KEY (`aid`) REFERENCES `cartype` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of carxl
-- ----------------------------
INSERT INTO `carxl` VALUES (5, 4, '大G-x');
INSERT INTO `carxl` VALUES (6, 5, '奔驰-s1');
INSERT INTO `carxl` VALUES (7, 6, '大众-朗逸');
INSERT INTO `carxl` VALUES (8, 7, '现代-逸轩');
INSERT INTO `carxl` VALUES (16, 5, '奔驰-s2');
INSERT INTO `carxl` VALUES (17, 4, '宝马-Q7');
INSERT INTO `carxl` VALUES (18, 8, '法拉利-f8');
INSERT INTO `carxl` VALUES (19, 9, '威龙');
INSERT INTO `carxl` VALUES (20, 5, '奔驰-x2');

-- ----------------------------
-- Table structure for chong
-- ----------------------------
DROP TABLE IF EXISTS `chong`;
CREATE TABLE `chong`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `omoney` float NOT NULL,
  `yid` int(11) NOT NULL,
  `osmoney` float NOT NULL,
  `olastmoney` float NOT NULL,
  `uid` int(11) NOT NULL,
  `oremark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `otime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `fk_chongrid`(`rid`) USING BTREE,
  INDEX `fk_chongyid`(`yid`) USING BTREE,
  INDEX `fk_chonguid`(`uid`) USING BTREE,
  CONSTRAINT `fk_chongrid` FOREIGN KEY (`rid`) REFERENCES `member` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_chonguid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_chongyid` FOREIGN KEY (`yid`) REFERENCES `youhui` (`yid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of chong
-- ----------------------------
INSERT INTO `chong` VALUES (35, 22, 9000, 9, 1500, 10500, 7, '1', '2023-05-27 07:26:15');
INSERT INTO `chong` VALUES (36, 23, 10000, 7, 1000, 11000, 7, '2', '2023-05-27 07:26:42');
INSERT INTO `chong` VALUES (37, 24, 12000, 9, 1500, 13500, 7, '3', '2023-05-27 07:27:13');
INSERT INTO `chong` VALUES (38, 25, 12000, 13, 0, 12000, 10, '4', '2023-05-29 12:35:33');
INSERT INTO `chong` VALUES (39, 26, 12000, 7, 1000, 13000, 7, '5', '2023-05-29 03:42:13');
INSERT INTO `chong` VALUES (40, 27, 11000, 9, 1500, 12500, 7, '6', '2023-05-29 03:42:44');
INSERT INTO `chong` VALUES (41, 22, 12000, 9, 1500, 13500, 7, '1', '2023-05-29 03:53:58');
INSERT INTO `chong` VALUES (42, 31, 11000, 9, 1500, 12500, 5, '3', '2023-05-29 05:44:19');

-- ----------------------------
-- Table structure for cptype
-- ----------------------------
DROP TABLE IF EXISTS `cptype`;
CREATE TABLE `cptype`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of cptype
-- ----------------------------
INSERT INTO `cptype` VALUES (6, '零件');
INSERT INTO `cptype` VALUES (7, '装饰');
INSERT INTO `cptype` VALUES (8, '布装饰');
INSERT INTO `cptype` VALUES (9, '轮胎');
INSERT INTO `cptype` VALUES (10, '化学');

-- ----------------------------
-- Table structure for dj
-- ----------------------------
DROP TABLE IF EXISTS `dj`;
CREATE TABLE `dj`  (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `djf` int(11) NOT NULL,
  `dmoneyBl` double NOT NULL,
  `dzk` double NOT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of dj
-- ----------------------------
INSERT INTO `dj` VALUES (10, '普通会员', 400, 0.1, 0.9);
INSERT INTO `dj` VALUES (11, '白银会员', 500, 0.3, 0.6);
INSERT INTO `dj` VALUES (12, '特级会员', 800, 0.8, 0.2);
INSERT INTO `dj` VALUES (13, '白金会员', 650, 0.65, 0.3);
INSERT INTO `dj` VALUES (14, '黄金会员', 450, 0.45, 0.5);
INSERT INTO `dj` VALUES (15, '钻石会员', 550, 0.55, 0.4);
INSERT INTO `dj` VALUES (16, '青铜会员', 111, 0.15, 0.8);
INSERT INTO `dj` VALUES (17, '特别会员', 123, 0.57, 0.65);

-- ----------------------------
-- Table structure for duihuan
-- ----------------------------
DROP TABLE IF EXISTS `duihuan`;
CREATE TABLE `duihuan`  (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `nid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `hcount` int(11) NOT NULL,
  `htime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`hid`) USING BTREE,
  INDEX `fk_duihuannid`(`nid`) USING BTREE,
  INDEX `fk_duihuanrid`(`rid`) USING BTREE,
  INDEX `fk_duihuanuid`(`uid`) USING BTREE,
  CONSTRAINT `fk_duihuannid` FOREIGN KEY (`nid`) REFERENCES `lipin` (`nid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_duihuanrid` FOREIGN KEY (`rid`) REFERENCES `member` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_duihuanuid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of duihuan
-- ----------------------------
INSERT INTO `duihuan` VALUES (7, 3, 22, 4, '2023-05-27 09:04:02', 5);
INSERT INTO `duihuan` VALUES (8, 4, 23, 5, '2023-05-28 07:17:55', 7);
INSERT INTO `duihuan` VALUES (9, 5, 24, 4, '2023-05-29 12:04:38', 8);
INSERT INTO `duihuan` VALUES (10, 3, 23, 4, '2023-05-29 12:05:36', 8);
INSERT INTO `duihuan` VALUES (11, 3, 25, 2, '2023-05-29 12:54:58', 7);
INSERT INTO `duihuan` VALUES (12, 6, 31, 4, '2023-05-29 05:53:02', 5);

-- ----------------------------
-- Table structure for getcp
-- ----------------------------
DROP TABLE IF EXISTS `getcp`;
CREATE TABLE `getcp`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) NOT NULL,
  `gcount` int(11) NOT NULL,
  `gtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`gid`) USING BTREE,
  INDEX `fk_getcpfid`(`fid`) USING BTREE,
  INDEX `fk_getcpuid`(`uid`) USING BTREE,
  CONSTRAINT `fk_getcpfid` FOREIGN KEY (`fid`) REFERENCES `mytf` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_getcpuid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of getcp
-- ----------------------------
INSERT INTO `getcp` VALUES (17, 50, 120, '2023-5-27', 6);
INSERT INTO `getcp` VALUES (18, 49, 150, '2023-5-27', 6);
INSERT INTO `getcp` VALUES (19, 48, 100, '2023-5-27', 5);
INSERT INTO `getcp` VALUES (20, 47, 130, '2023-5-27', 5);
INSERT INTO `getcp` VALUES (21, 46, 135, '2023-5-27', 5);
INSERT INTO `getcp` VALUES (22, 45, 125, '2023-5-27', 5);
INSERT INTO `getcp` VALUES (23, 50, 50, '2023-5-28', 7);
INSERT INTO `getcp` VALUES (24, 51, 200, '2023-05-28 11:24:47', 5);
INSERT INTO `getcp` VALUES (25, 52, 240, '2023-05-29 12:45:11', 7);
INSERT INTO `getcp` VALUES (26, 53, 200, '2023-05-29 05:46:24', 5);

-- ----------------------------
-- Table structure for jici
-- ----------------------------
DROP TABLE IF EXISTS `jici`;
CREATE TABLE `jici`  (
  `jid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `jtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sid` int(11) NOT NULL,
  `jmoney` float NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`jid`) USING BTREE,
  INDEX `fk_jicirid`(`rid`) USING BTREE,
  INDEX `fk_jicisid`(`sid`) USING BTREE,
  INDEX `fk_jiciuid`(`uid`) USING BTREE,
  CONSTRAINT `fk_jicirid` FOREIGN KEY (`rid`) REFERENCES `member` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_jicisid` FOREIGN KEY (`sid`) REFERENCES `servicetype` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_jiciuid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of jici
-- ----------------------------
INSERT INTO `jici` VALUES (2, 22, '2023-05-27 08:12:56', 5, 600, 5);
INSERT INTO `jici` VALUES (3, 24, '2023-05-28 10:18:41', 7, 600, 8);
INSERT INTO `jici` VALUES (4, 25, '2023-05-29 12:53:50', 8, 120, 7);
INSERT INTO `jici` VALUES (5, 22, '2023-05-29 03:52:42', 8, 8041, 7);
INSERT INTO `jici` VALUES (6, 31, '2023-05-29 05:50:42', 8, 300, 5);

-- ----------------------------
-- Table structure for lipin
-- ----------------------------
DROP TABLE IF EXISTS `lipin`;
CREATE TABLE `lipin`  (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `nname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nimg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `njf` int(11) NOT NULL,
  `ncount` int(11) NOT NULL,
  `nncount` int(11) NOT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of lipin
-- ----------------------------
INSERT INTO `lipin` VALUES (3, '大米', 'a.jpg', 15, 200, 190);
INSERT INTO `lipin` VALUES (4, '食用油', 'a.jpg', 35, 200, 195);
INSERT INTO `lipin` VALUES (5, '光头强', 'a.jpg', 15, 200, 196);
INSERT INTO `lipin` VALUES (6, '熊大', 'a.jpg', 10, 200, 196);

-- ----------------------------
-- Table structure for look
-- ----------------------------
DROP TABLE IF EXISTS `look`;
CREATE TABLE `look`  (
  `kid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `ktime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kremark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`kid`) USING BTREE,
  INDEX `fk_lookrid`(`rid`) USING BTREE,
  INDEX `fk_lookuid`(`uid`) USING BTREE,
  CONSTRAINT `fk_lookrid` FOREIGN KEY (`rid`) REFERENCES `member` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_lookuid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of look
-- ----------------------------
INSERT INTO `look` VALUES (1, 22, '2023-05-28 07:18:45', '杨总在外地，这周回不来', 7);
INSERT INTO `look` VALUES (2, 23, '2023-05-28 07:19:59', '刘总在摸摸唱', 7);
INSERT INTO `look` VALUES (3, 25, '2023-05-29 12:57:08', '邓总在忙，下次再说', 7);
INSERT INTO `look` VALUES (4, 31, '2023-05-29 05:54:04', '邓总在外洗脚', 5);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rpwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rimg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rtel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rsex` int(11) NOT NULL,
  `did` int(11) NOT NULL,
  `rbirthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rstatus` int(11) NOT NULL DEFAULT 1,
  `rjf` double NOT NULL DEFAULT 0,
  `rcarnum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xid` int(11) NOT NULL,
  `rcolor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rway` float NULL DEFAULT NULL,
  `zid` int(11) NOT NULL,
  `rnum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `raddress` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rremark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rmoney` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `fk_memberdid`(`did`) USING BTREE,
  INDEX `fk_memberxid`(`xid`) USING BTREE,
  INDEX `fk_memberzid`(`zid`) USING BTREE,
  CONSTRAINT `fk_memberdid` FOREIGN KEY (`did`) REFERENCES `dj` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_memberxid` FOREIGN KEY (`xid`) REFERENCES `carxl` (`xid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_memberzid` FOREIGN KEY (`zid`) REFERENCES `pz` (`zid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (22, 'A001', '杨俊', '123', 'a.jpg', '17633984729', 1, 12, '2023-05-17', 1, 63, '鄂A00000', 18, '黑色', 123, 8, '456232199909076284', '武汉市洪山区', '车真好', '2023-5-27', '13499');
INSERT INTO `member` VALUES (23, 'A002', '刘浩', '345', 'a.jpg', '18382597728', 1, 13, '2023-05-02', 1, 745, '鄂A11111', 5, '红色', 100, 8, '632536199708258394', '湖北黄石', '好', '2023-5-27', '9040');
INSERT INTO `member` VALUES (24, 'A003', '邓颖', '123', '20230527192548444486.png', '15638490923', 1, 14, '2023-05-10', 1, 1272, '鄂A11100', 16, '黑色', 234, 8, '634522200110118529', '武汉', '', '2023-5-29', '10236.0');
INSERT INTO `member` VALUES (25, 'A004', '邓皓', '123', 'a.jpg', '17399715369', 1, 11, '2023-05-17', 1, 78, '鄂A11119', 7, '褐色', 200, 6, '625625199608273872', '武汉', '', '2023-5-27', '10800');
INSERT INTO `member` VALUES (26, 'A005', '杨洋', '123', 'a.jpg', '15692736173', 0, 12, '2023-05-24', 1, 0, '鄂A00001', 8, '蓝色', 250, 8, '623452199809187301', '杭州', '', '2023-5-27', '13000');
INSERT INTO `member` VALUES (27, 'A006', '杨仓', '123', 'a.jpg', '17399163864', 1, 13, '2023-05-16', 1, 0, '鄂A22222', 17, '黑色', 120, 8, '674633199809015926', '南昌', '', '2023-5-27', '12500');
INSERT INTO `member` VALUES (28, 'A007', '陈萧', '123', 'a.jpg', '17399264826', 1, 10, '2023-05-30', 1, 0, '鄂A11199', 7, '褐色', 159, 8, '643532199909083028', '武汉', '', '2023-5-28', '0.0');
INSERT INTO `member` VALUES (29, 'A008', '广之声', '123', 'a.jpg', '17364912793', 1, 15, '2023-05-17', 1, 0, '鄂A11223', 5, '灰色', 123, 9, '352342199901288250', '广州', '', '2023-5-29', '0.0');
INSERT INTO `member` VALUES (30, 'A009', '刘君', '123', 'a.jpg', '17369153701', 1, 15, '2023-05-30', 1, 0, '鄂A22211', 16, '白色', 200, 8, '523423199204249610', '广州', '', '2023-5-29', '0.0');
INSERT INTO `member` VALUES (31, 'K001', '邓光光', '123', 'a.jpg', '17369156283', 1, 12, '2023-05-09', 1, 4.8, '鄂A12345', 18, '褐色', 123, 8, '532235199909012980', '武汉', '', '2023-5-29', '11304');

-- ----------------------------
-- Table structure for mytf
-- ----------------------------
DROP TABLE IF EXISTS `mytf`;
CREATE TABLE `mytf`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cid` int(11) NOT NULL,
  `fdw` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `faddress` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `foutprice` float NOT NULL,
  `finprice` float NOT NULL,
  `fimg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcount` int(11) NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE,
  INDEX `fk_mytfcid`(`cid`) USING BTREE,
  CONSTRAINT `fk_mytfcid` FOREIGN KEY (`cid`) REFERENCES `cptype` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mytf
-- ----------------------------
INSERT INTO `mytf` VALUES (45, '雨刷', 6, '个', '武汉', 75, 10, 'a.jpg', 90);
INSERT INTO `mytf` VALUES (46, '靠垫', 7, '个', '杭州', 100, 45, 'a.jpg', 125);
INSERT INTO `mytf` VALUES (47, '脚垫', 8, '个', '义乌', 120, 55, 'a.jpg', 103);
INSERT INTO `mytf` VALUES (48, '机油', 6, '箱', '武汉', 110, 55, 'a.jpg', 65);
INSERT INTO `mytf` VALUES (49, '玻璃水', 6, '个', '长沙', 90, 45, 'a.jpg', 140);
INSERT INTO `mytf` VALUES (50, '方向盘', 6, '个', '武汉', 200, 90, 'a.jpg', 140);
INSERT INTO `mytf` VALUES (51, '米其林', 9, '个', '武汉', 120, 60, 'a.jpg', 172);
INSERT INTO `mytf` VALUES (52, '佳通', 9, '个', '黄冈', 150, 50, 'a.jpg', 220);
INSERT INTO `mytf` VALUES (53, '润化油', 6, '个', '武汉', 200, 100, 'a.jpg', 180);

-- ----------------------------
-- Table structure for outcp
-- ----------------------------
DROP TABLE IF EXISTS `outcp`;
CREATE TABLE `outcp`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  `tcount` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `ttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tflag` int(11) NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `fk_outcprid`(`rid`) USING BTREE,
  INDEX `fk_outcpfid`(`fid`) USING BTREE,
  INDEX `fk_outcpuid`(`uid`) USING BTREE,
  CONSTRAINT `fk_outcpfid` FOREIGN KEY (`fid`) REFERENCES `mytf` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_outcprid` FOREIGN KEY (`rid`) REFERENCES `member` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_outcpuid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of outcp
-- ----------------------------
INSERT INTO `outcp` VALUES (47, 22, 45, 10, 5, '2023-05-27 07:41:50', 1);
INSERT INTO `outcp` VALUES (48, 22, 48, 10, 5, '2023-05-27 07:42:14', 1);
INSERT INTO `outcp` VALUES (49, 22, 49, 20, 5, '2023-05-27 07:42:28', 1);
INSERT INTO `outcp` VALUES (50, 23, 45, 10, 5, '2023-05-27 07:42:57', 1);
INSERT INTO `outcp` VALUES (51, 23, 48, 5, 5, '2023-05-27 07:43:07', 1);
INSERT INTO `outcp` VALUES (53, 23, 46, 15, 5, '2023-05-27 07:43:29', 1);
INSERT INTO `outcp` VALUES (54, 24, 50, 15, 6, '2023-05-27 07:48:37', 1);
INSERT INTO `outcp` VALUES (55, 24, 47, 12, 6, '2023-05-27 07:48:51', 1);
INSERT INTO `outcp` VALUES (58, 22, 51, 10, 5, '2023-05-28 11:50:56', 0);
INSERT INTO `outcp` VALUES (59, 25, 52, 12, 7, '2023-05-29 12:46:47', 1);
INSERT INTO `outcp` VALUES (60, 25, 47, 15, 7, '2023-05-29 12:47:11', 1);
INSERT INTO `outcp` VALUES (61, 24, 52, 5, 7, '2023-05-29 03:46:52', 0);
INSERT INTO `outcp` VALUES (62, 31, 53, 20, 5, '2023-05-29 05:47:40', 1);
INSERT INTO `outcp` VALUES (63, 31, 51, 4, 5, '2023-05-29 05:47:54', 1);

-- ----------------------------
-- Table structure for outcpp
-- ----------------------------
DROP TABLE IF EXISTS `outcpp`;
CREATE TABLE `outcpp`  (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) NOT NULL,
  `wcount` int(11) NOT NULL,
  `wname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wtel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(11) NOT NULL,
  `wtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`wid`) USING BTREE,
  INDEX `fk_outcp1fid`(`fid`) USING BTREE,
  INDEX `fk_outcp1uid`(`uid`) USING BTREE,
  CONSTRAINT `fk_outcp1fid` FOREIGN KEY (`fid`) REFERENCES `mytf` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_outcp1uid` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of outcpp
-- ----------------------------
INSERT INTO `outcpp` VALUES (3, 48, 5, '张三', '17355289163', 6, '2023-05-27 07:49:53');
INSERT INTO `outcpp` VALUES (4, 48, 5, '王五', '15688259173', 7, '2023-05-27 08:00:02');
INSERT INTO `outcpp` VALUES (5, 45, 5, '全峰', '17526470917', 10, '2023-05-28 10:56:30');
INSERT INTO `outcpp` VALUES (6, 50, 5, '全封', '17533716926', 10, '2023-05-28 11:00:04');
INSERT INTO `outcpp` VALUES (7, 50, 5, '全胜', '17538169264', 10, '2023-05-28 11:11:13');
INSERT INTO `outcpp` VALUES (8, 45, 5, '杨俊', '17527364958', 10, '2023-05-28 11:12:13');
INSERT INTO `outcpp` VALUES (9, 45, 5, '刘浩', '17344796259', 8, '2023-05-28 11:17:40');
INSERT INTO `outcpp` VALUES (10, 51, 8, '广安', '17369163826', 8, '2023-05-29 11:28:03');
INSERT INTO `outcpp` VALUES (11, 52, 8, '王五', '17344910921', 7, '2023-05-29 12:52:42');
INSERT INTO `outcpp` VALUES (12, 48, 5, '邓皓', '18523976385', 5, '2023-05-29 05:49:33');

-- ----------------------------
-- Table structure for pz
-- ----------------------------
DROP TABLE IF EXISTS `pz`;
CREATE TABLE `pz`  (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `zname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`zid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of pz
-- ----------------------------
INSERT INTO `pz` VALUES (5, '结婚证');
INSERT INTO `pz` VALUES (6, '学生证');
INSERT INTO `pz` VALUES (7, '房产证');
INSERT INTO `pz` VALUES (8, '身份证');
INSERT INTO `pz` VALUES (9, '驾驶证');

-- ----------------------------
-- Table structure for servicetype
-- ----------------------------
DROP TABLE IF EXISTS `servicetype`;
CREATE TABLE `servicetype`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of servicetype
-- ----------------------------
INSERT INTO `servicetype` VALUES (5, '专属服务');
INSERT INTO `servicetype` VALUES (6, '聊天服务');
INSERT INTO `servicetype` VALUES (7, '一对一服务');
INSERT INTO `servicetype` VALUES (8, '洗车');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `utel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `usex` int(11) NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (5, 'admin', '123', '邓颖', '18695026274', 0);
INSERT INTO `userinfo` VALUES (6, 'quan', '123', '邓颖颖', '18695026273', 1);
INSERT INTO `userinfo` VALUES (7, 'king', '123', '邓颖颖', '18695026276', 1);
INSERT INTO `userinfo` VALUES (8, 'abc', '123', '张三', '18695026279', 1);
INSERT INTO `userinfo` VALUES (9, 'bvcd', '123', '张飒', '17377493861', 0);
INSERT INTO `userinfo` VALUES (10, 'bc', '123', '全峰', '15733719264', 1);
INSERT INTO `userinfo` VALUES (11, 'ling', '123', '杨样', '18638539170', 0);

-- ----------------------------
-- Table structure for youhui
-- ----------------------------
DROP TABLE IF EXISTS `youhui`;
CREATE TABLE `youhui`  (
  `yid` int(11) NOT NULL AUTO_INCREMENT,
  `ytitle` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ybegintime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `yendtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ymoney` float NULL DEFAULT NULL,
  `ylessmoney` float NULL DEFAULT NULL,
  PRIMARY KEY (`yid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of youhui
-- ----------------------------
INSERT INTO `youhui` VALUES (6, '冲三百送两百', '2023-05-04', '2023-05-25', 200, 300);
INSERT INTO `youhui` VALUES (7, '冲两千送一千', '2023-05-04', '2023-06-01', 1000, 2000);
INSERT INTO `youhui` VALUES (8, '冲一千送六百', '2023-05-08', '2023-06-10', 600, 1000);
INSERT INTO `youhui` VALUES (9, '冲两千送一千五', '2023-05-03', '2023-05-25', 1500, 2000);
INSERT INTO `youhui` VALUES (10, '冲一千送七百', '2023-05-17', '2023-05-26', 700, 1000);
INSERT INTO `youhui` VALUES (11, '冲五百送两百', '2023-05-01', '2023-06-11', 200, 500);
INSERT INTO `youhui` VALUES (12, '冲六百送二百', '2023-05-01', '2023-06-01', 200, 600);
INSERT INTO `youhui` VALUES (13, '无优惠', '2023-05-01', '2023-05-24', 0, 0);
INSERT INTO `youhui` VALUES (14, '秋日大放送', '2023-05-30', '2023-06-01', 500, 1000);

SET FOREIGN_KEY_CHECKS = 1;
