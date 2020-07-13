/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : hole

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-07-13 15:11:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `atlas`
-- ----------------------------
DROP TABLE IF EXISTS `atlas`;
CREATE TABLE `atlas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `statue` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atlas
-- ----------------------------
INSERT INTO `atlas` VALUES ('17', '千山万水的美景', '翻开在藏北草原工作时的日记，一九七四年五月十一日这天这样记着“今晨起来，才知昨晚的雪下了一夜，积雪足有半人高。单薄的帐篷倾塌了，斜压在我们身上，我们竞浑然不觉。厚厚的雪被下，睡得十分惬意。这是平生第一次感受到雪的重量和温暖。', '2020-06-25 23:17:43', '1');
INSERT INTO `atlas` VALUES ('18', '藏地西方之旅', '翻开在藏北草原工作时的日记，一九七四年五月十一日这天这样记着“今晨起来，才知昨晚的雪下了一夜，积雪足有半人高。单薄的帐篷倾塌了，斜压在我们身上，我们竞浑然不觉。厚厚的雪被下，睡得十分惬意。这是平生第一次感受到雪的重量和温暖。', '2020-07-10 23:28:01', '1');
INSERT INTO `atlas` VALUES ('19', '昆仑雪山', '这样一个地方，它仿佛是这个世界的禁地，很少有人敢去踏足，它被公认为世界十大秘境之一，一起走近令人望而却步的昆仑山地狱之门，看看这背后鲜为人知的恐怖与神秘。“天苍苍，野茫茫，风吹草低见牛羊”，在牧人眼中，草肥水足的地方是他们放牧的天堂。\n\n但是在昆仑山生活的牧羊人却宁愿因没有肥草吃使牛羊饿死在戈壁滩上，也不敢进入昆仑山那个牧草繁茂的古老而沉寂的深谷。', '2020-07-10 23:33:56', '1');
INSERT INTO `atlas` VALUES ('20', '生活记录', '翻开在藏北草原工作时的日记，一九七四年五月十一日这天这样记着“今晨起来，才知昨晚的雪下了一夜，积雪足有半人高。单薄的帐篷倾塌了，斜压在我们身上，我们竞浑然不觉。厚厚的雪被下，睡得十分惬意。这是平生第一次感受到雪的重量和温暖。...', '2020-07-11 00:42:21', '1');
INSERT INTO `atlas` VALUES ('21', '快乐的源泉', '翻开在藏北草原工作时的日记，一九七四年五月十一日这天这样记着“今晨起来，才知昨晚的雪下了一夜，积雪足有半人高。单薄的帐篷倾塌了，斜压在我们身上，我们竞浑然不觉。厚厚的雪被下，睡得十分惬意。这是平生第一次感受到雪的重量和温暖。...', '2020-07-11 01:34:11', '1');
INSERT INTO `atlas` VALUES ('22', '晨曦', '翻开在藏北草原工作时的日记，一九七四年五月十一日这天这样记着“今晨起来，才知昨晚的雪下了一夜，积雪足有半人高。单薄的帐篷倾塌了，斜压在我们身上，我们竞浑然不觉。厚厚的雪被下，睡得十分惬意。这是平生第一次感受到雪的重量和温暖。...', '2020-07-11 01:54:34', '1');

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `statue` int(11) DEFAULT NULL,
  `recommend` tinyint(1) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('10', '开源软件 Apache Dubbo牵手IDE插件，开发部署提速不止8倍', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程，并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上，\n                      同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '### 阿里云\n这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程，并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台）上，同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。\n### 演示\n自从产品经理银时小伙和他的团队在去年11月发布 Cloud Toolkit（一款 IDE 插件）以来，已帮助数以万计的开发者们提高了开发、测试、诊断以及应用部署效率。期间，他们还发布了 Contributor Ranking List，和开发者们一同定义、打造好用的 IDE 插件。下文将为大家演示如何利用 Cloud Toolkit 快速创建 Apache Dubbo 工程，以及运行并验证这个样例工程的可执行性。\n\n![图片](https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=304341154,1082578760&fm=26&gp=0.jpg)\n\n\n\n\n', '1', '1', '2098', '2020-07-04 16:35:50', '2020-07-07 17:08:12', '6', '1');
INSERT INTO `blog` VALUES ('11', '测试文章2', '测试文章2', '测试文章2', '0', '0', '5', '2020-07-07 13:41:41', null, '6', '1');
INSERT INTO `blog` VALUES ('12', '测试文章3', '测试文章3测试文章3测试文章3测试文章3测试文章3', '测试文章3测试文章3测试文章3测试文章3测试文章3', '-1', '0', '0', '2020-07-07 13:47:20', '2020-07-07 13:47:55', '5', '1');
INSERT INTO `blog` VALUES ('13', '测试文章4', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '1', '0', '10', '2020-07-07 13:50:12', '2020-07-07 14:32:56', '4', '1');
INSERT INTO `blog` VALUES ('14', 'MySQL数据库系列', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '1', '0', '3', '2020-07-09 18:45:32', null, '7', '1');
INSERT INTO `blog` VALUES ('15', 'MySQL数据库系列只SQL调优', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '这一次，在最新的版本中，插件热情牵手了全国受欢迎的开源社区 Apache Dubbo，支持快速创建 RPC 框架 Dubbo 项目工程， 并且将 Dubbo 应用快速部署到 EDAS（一个应用托管和微服务管理的PaaS平台） 上， 同时支持本地服务和部署在 EDAS 上的服务之间互相调用。希望插件在拥抱开源的路上，服务好更多的开发者。', '1', '0', '0', '2020-07-09 23:05:24', null, '7', '1');
INSERT INTO `blog` VALUES ('16', '『 Redis实战 』 快速上手Redis', 'Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。', 'Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。\n', '1', '1', '3', '2020-07-11 17:00:15', null, '12', '1');

-- ----------------------------
-- Table structure for `blog_tags`
-- ----------------------------
DROP TABLE IF EXISTS `blog_tags`;
CREATE TABLE `blog_tags` (
  `blog_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_tags
-- ----------------------------
INSERT INTO `blog_tags` VALUES ('10', '6');
INSERT INTO `blog_tags` VALUES ('11', '8');
INSERT INTO `blog_tags` VALUES ('11', '7');
INSERT INTO `blog_tags` VALUES ('11', '6');
INSERT INTO `blog_tags` VALUES ('12', '9');
INSERT INTO `blog_tags` VALUES ('12', '7');
INSERT INTO `blog_tags` VALUES ('13', '8');
INSERT INTO `blog_tags` VALUES ('13', '7');
INSERT INTO `blog_tags` VALUES ('13', '6');
INSERT INTO `blog_tags` VALUES ('14', '14');
INSERT INTO `blog_tags` VALUES ('14', '9');
INSERT INTO `blog_tags` VALUES ('15', '9');
INSERT INTO `blog_tags` VALUES ('15', '10');
INSERT INTO `blog_tags` VALUES ('16', '4');
INSERT INTO `blog_tags` VALUES ('16', '16');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `statue` tinyint(1) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_blog` (`bid`),
  CONSTRAINT `fk_comment_blog` FOREIGN KEY (`bid`) REFERENCES `blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('2', '清风明月', '与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念, 与现实生活一致：与现实生活的流程、逻辑保持一致。', '2020-07-06 16:19:33', '592318425@qq.com', '1', '10');
INSERT INTO `comment` VALUES ('3', '越来越男生', '叔叔说', '2020-07-06 23:04:35', '555@qq.com', '1', '10');
INSERT INTO `comment` VALUES ('4', 'sss', 'sss', '2020-07-06 23:22:58', '166@163.com', '0', '10');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `statue` tinyint(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', 'B站链接', '2020-07-05 18:19:00', '1', 'https://www.bilibili.com');
INSERT INTO `link` VALUES ('5', '百度一下', '2020-07-05 21:11:03', '1', 'https://www.baidu.com');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_date` datetime DEFAULT NULL,
  `logout_date` datetime DEFAULT NULL,
  `type_device` varchar(255) DEFAULT NULL,
  `address_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('2', '2020-06-20 20:10:43', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('3', '2020-06-20 21:03:10', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('4', '2020-06-20 22:00:17', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('5', '2020-06-20 22:58:13', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('6', '2020-06-21 12:12:27', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('7', '2020-06-21 12:19:07', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('8', '2020-06-21 12:58:28', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('9', '2020-06-21 16:20:58', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('10', '2020-06-21 16:21:05', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('11', '2020-06-21 16:21:10', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('12', '2020-06-21 16:21:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('13', '2020-06-21 20:25:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('14', '2020-06-21 20:26:41', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('15', '2020-06-21 20:29:20', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('16', '2020-06-21 20:30:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('17', '2020-06-21 21:08:12', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('18', '2020-06-21 21:19:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('19', '2020-06-21 21:58:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('20', '2020-06-21 22:20:22', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('21', '2020-06-21 22:34:19', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('22', '2020-06-21 22:35:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('23', '2020-06-21 22:37:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('24', '2020-06-21 22:39:11', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('25', '2020-06-21 23:07:35', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('26', '2020-06-22 14:25:12', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('27', '2020-06-22 20:33:04', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('28', '2020-06-22 20:53:15', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('29', '2020-06-22 20:54:48', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('30', '2020-06-22 20:58:53', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('31', '2020-06-23 11:58:50', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('32', '2020-06-23 12:06:17', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('33', '2020-06-23 12:08:30', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('34', '2020-06-23 12:10:22', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('35', '2020-06-23 12:47:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('36', '2020-06-23 12:51:42', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('37', '2020-06-23 12:53:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('38', '2020-06-23 12:59:05', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('39', '2020-06-23 13:01:16', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('40', '2020-06-23 13:06:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('41', '2020-06-23 13:12:48', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('42', '2020-06-23 13:16:35', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('43', '2020-06-23 13:18:06', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('44', '2020-06-23 13:23:06', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('45', '2020-06-23 13:25:24', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('46', '2020-06-23 14:01:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('47', '2020-06-23 14:57:47', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('48', '2020-06-23 19:43:42', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('49', '2020-06-23 19:58:20', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('50', '2020-06-23 20:00:04', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('51', '2020-06-23 20:01:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('52', '2020-06-23 20:06:53', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('53', '2020-06-23 20:13:14', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('54', '2020-06-23 20:15:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('55', '2020-06-23 20:37:41', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('56', '2020-06-23 22:27:13', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('57', '2020-06-23 22:37:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('58', '2020-06-23 22:39:46', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('59', '2020-06-24 14:35:46', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('60', '2020-06-24 15:53:51', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('61', '2020-06-24 15:59:50', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('62', '2020-06-24 16:22:54', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('63', '2020-06-24 16:30:43', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('64', '2020-06-24 16:43:07', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('65', '2020-06-24 17:05:32', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('66', '2020-06-24 17:20:02', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('67', '2020-06-24 17:24:23', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('68', '2020-06-24 17:31:53', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('69', '2020-06-24 17:34:28', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('70', '2020-06-24 17:49:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('71', '2020-06-24 17:54:27', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('72', '2020-06-24 18:55:54', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('73', '2020-06-24 19:01:23', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('74', '2020-06-24 19:03:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('75', '2020-06-24 19:51:55', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('76', '2020-06-24 22:39:22', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('77', '2020-06-25 11:23:49', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('78', '2020-06-25 13:23:45', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('79', '2020-06-25 13:35:25', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('80', '2020-06-25 14:03:38', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('81', '2020-06-25 14:33:51', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('82', '2020-06-25 14:37:01', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('83', '2020-06-25 14:40:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('84', '2020-06-25 15:38:05', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('85', '2020-06-25 17:03:55', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('86', '2020-06-25 17:32:13', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('87', '2020-06-25 17:36:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('88', '2020-06-25 18:03:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('89', '2020-06-25 18:18:10', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('90', '2020-06-25 18:41:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('91', '2020-06-25 18:46:07', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('92', '2020-06-25 19:40:21', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('93', '2020-06-25 22:23:45', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('94', '2020-06-26 00:34:58', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('95', '2020-06-26 18:43:08', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('96', '2020-06-26 19:58:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('97', '2020-06-26 21:50:30', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('98', '2020-06-26 22:01:07', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('99', '2020-06-26 22:52:29', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('100', '2020-06-26 23:25:32', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('101', '2020-06-26 23:25:48', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('102', '2020-06-26 23:41:37', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('103', '2020-06-26 23:47:57', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('104', '2020-06-26 23:50:36', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('105', '2020-06-26 23:53:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('106', '2020-06-26 23:55:28', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('107', '2020-06-27 00:01:53', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('108', '2020-06-27 00:12:06', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('109', '2020-06-27 00:13:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('110', '2020-06-27 00:14:55', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('111', '2020-06-27 00:17:18', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('112', '2020-06-27 00:21:02', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('113', '2020-06-27 13:00:48', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('114', '2020-06-28 18:20:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('115', '2020-06-28 19:28:23', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('116', '2020-06-28 21:11:52', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('117', '2020-06-28 22:04:10', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('118', '2020-06-28 22:15:46', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('119', '2020-06-28 22:17:50', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('120', '2020-06-28 22:39:12', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('121', '2020-07-01 13:58:15', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('122', '2020-07-01 14:20:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('123', '2020-07-01 14:32:42', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('124', '2020-07-01 15:14:26', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('125', '2020-07-03 17:25:52', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('126', '2020-07-03 18:55:17', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('127', '2020-07-03 22:40:00', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('128', '2020-07-03 22:57:21', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('129', '2020-07-04 14:28:24', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('130', '2020-07-04 15:33:45', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('131', '2020-07-04 21:05:16', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('132', '2020-07-04 22:19:36', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('133', '2020-07-04 22:24:05', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('134', '2020-07-04 22:37:58', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('135', '2020-07-05 00:23:28', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('136', '2020-07-05 00:31:55', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('137', '2020-07-05 00:34:57', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('138', '2020-07-05 01:07:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('139', '2020-07-05 12:27:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('140', '2020-07-05 14:29:00', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('141', '2020-07-05 15:14:54', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('142', '2020-07-05 15:20:31', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('143', '2020-07-05 16:00:30', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('144', '2020-07-05 17:36:53', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('145', '2020-07-05 18:13:28', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('146', '2020-07-05 18:17:25', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('147', '2020-07-05 18:25:04', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('148', '2020-07-05 18:26:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('149', '2020-07-05 18:36:52', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('150', '2020-07-05 18:50:19', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('151', '2020-07-05 19:03:04', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('152', '2020-07-05 19:21:19', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('153', '2020-07-05 19:21:59', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('154', '2020-07-05 20:48:36', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('155', '2020-07-05 20:55:26', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('156', '2020-07-05 21:01:51', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('157', '2020-07-05 21:02:49', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('158', '2020-07-05 21:08:14', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('159', '2020-07-06 12:19:25', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('160', '2020-07-06 12:22:18', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('161', '2020-07-06 13:50:25', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('162', '2020-07-06 14:06:45', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('163', '2020-07-06 14:26:25', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('164', '2020-07-06 17:01:50', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('165', '2020-07-06 17:18:37', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('166', '2020-07-06 17:24:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('167', '2020-07-06 17:36:04', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('168', '2020-07-06 18:04:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('169', '2020-07-06 18:05:11', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('170', '2020-07-06 18:34:40', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('171', '2020-07-06 18:38:37', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('172', '2020-07-06 18:57:37', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('173', '2020-07-06 18:58:49', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('174', '2020-07-06 18:59:22', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('175', '2020-07-06 19:05:11', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('176', '2020-07-06 19:12:01', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('177', '2020-07-06 19:17:36', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('178', '2020-07-06 20:32:08', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('179', '2020-07-06 20:55:50', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('180', '2020-07-06 20:57:59', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('181', '2020-07-06 22:17:00', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('182', '2020-07-06 22:54:48', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('183', '2020-07-06 22:56:52', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('184', '2020-07-06 23:03:37', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('185', '2020-07-06 23:13:11', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('186', '2020-07-06 23:32:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('187', '2020-07-07 13:14:43', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('188', '2020-07-07 17:07:20', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('189', '2020-07-07 19:09:49', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('190', '2020-07-09 18:38:58', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('191', '2020-07-09 18:53:34', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('192', '2020-07-09 22:56:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('193', '2020-07-10 14:01:44', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('194', '2020-07-10 22:17:23', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('195', '2020-07-11 00:42:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('196', '2020-07-11 12:22:33', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('197', '2020-07-11 22:43:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('198', '2020-07-12 00:18:42', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('199', '2020-07-12 16:56:06', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('200', '2020-07-12 17:54:23', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('201', '2020-07-12 18:05:56', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('202', '2020-07-12 20:11:01', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('203', '2020-07-12 23:07:03', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('204', '2020-07-12 23:10:59', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('205', '2020-07-13 00:24:17', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('206', '2020-07-13 13:59:54', null, 'PC', '192.168.0.15');
INSERT INTO `log` VALUES ('207', '2020-07-13 14:51:58', null, 'PC', '192.168.0.15');

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `atlas_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('46', '美景', '美丽的山景', '2020-07-10 22:17:56', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo17\\46.jpg', '17');
INSERT INTO `photo` VALUES ('47', '绿色', '绿色的景色', '2020-07-10 22:24:37', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo17\\47.jpg', '17');
INSERT INTO `photo` VALUES ('48', '粉白', '粉白', '2020-07-10 22:25:48', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo17\\48.jpg', '17');
INSERT INTO `photo` VALUES ('49', '藏地', '藏地', '2020-07-10 23:29:38', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo18\\49.jpg', '18');
INSERT INTO `photo` VALUES ('50', '藏地湖景', '藏地', '2020-07-10 23:30:01', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo18\\50.jpg', '18');
INSERT INTO `photo` VALUES ('51', '山', '山山山', '2020-07-10 23:35:23', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo19\\51.jpg', '19');
INSERT INTO `photo` VALUES ('52', '天空', '天空', '2020-07-11 00:43:17', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo20\\52.jpg', '20');
INSERT INTO `photo` VALUES ('54', '快乐的源泉', '快乐的源泉', '2020-07-11 01:35:02', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo21\\54.jpg', '21');
INSERT INTO `photo` VALUES ('55', '晨曦', '晨曦', '2020-07-11 01:55:28', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\photo22\\55.jpg', '22');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reply_comment` (`cid`),
  CONSTRAINT `fk_reply_comment` FOREIGN KEY (`cid`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('3', '清风明月的树洞', '没懂呀！', '2020-07-06 23:08:24', '3', '3');
INSERT INTO `reply` VALUES ('4', '清风明月的树洞', '还是没懂呀\n', '2020-07-06 23:08:47', '3', '3');
INSERT INTO `reply` VALUES ('5', '清风明月的树洞', '感谢关注，晚点会来回复您', '2020-07-06 23:22:35', '2', '2');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('4', 'SpringBoot', '2020-06-26 00:49:38');
INSERT INTO `tag` VALUES ('5', 'Yes AMD', '2020-06-26 00:49:46');
INSERT INTO `tag` VALUES ('6', 'Java', '2020-06-26 20:02:55');
INSERT INTO `tag` VALUES ('7', '竞标赛', '2020-06-26 20:03:03');
INSERT INTO `tag` VALUES ('8', 'ACM算法大赛', '2020-06-26 20:03:16');
INSERT INTO `tag` VALUES ('9', 'MySQL', '2020-07-09 18:39:16');
INSERT INTO `tag` VALUES ('10', 'SQL调优', '2020-07-09 18:39:47');
INSERT INTO `tag` VALUES ('11', 'Spring', '2020-07-09 18:40:25');
INSERT INTO `tag` VALUES ('12', 'SpringBoot', '2020-07-09 18:40:43');
INSERT INTO `tag` VALUES ('13', 'MyBatis', '2020-07-09 18:40:52');
INSERT INTO `tag` VALUES ('14', 'MyBatis Plus', '2020-07-09 18:41:18');
INSERT INTO `tag` VALUES ('15', '2018广东云曦大会', '2020-07-09 18:42:14');
INSERT INTO `tag` VALUES ('16', 'Redis', '2020-07-11 16:59:37');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('4', '阿里云竞标赛', '2020-06-26 00:12:58');
INSERT INTO `type` VALUES ('5', 'LetCode刷题', '2020-06-26 19:35:03');
INSERT INTO `type` VALUES ('6', '算法与数据结构解读', '2020-06-27 13:01:56');
INSERT INTO `type` VALUES ('7', 'MySQL', '2020-07-09 18:44:42');
INSERT INTO `type` VALUES ('8', 'SpringBoot', '2020-07-10 17:54:34');
INSERT INTO `type` VALUES ('9', 'Mybatis', '2020-07-10 17:54:44');
INSERT INTO `type` VALUES ('10', '云境', '2020-07-10 17:55:04');
INSERT INTO `type` VALUES ('11', '杂文', '2020-07-10 17:55:17');
INSERT INTO `type` VALUES ('12', 'Redis', '2020-07-10 17:55:30');
INSERT INTO `type` VALUES ('13', '集合', '2020-07-10 17:56:00');
INSERT INTO `type` VALUES ('14', '多线程编程', '2020-07-10 17:56:14');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'qfmy', '清风明月的树洞', '96e79218965eb72c92a549dd5a330112', '躯体已在生活中迷失，请用自由的信念去引领灵魂前行', '0', 'C:\\Users\\Administrator\\Desktop\\java-blog-serve\\src\\main\\webapp\\img\\avatar\\1.jpg');

-- ----------------------------
-- Table structure for `webcom`
-- ----------------------------
DROP TABLE IF EXISTS `webcom`;
CREATE TABLE `webcom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `statue` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of webcom
-- ----------------------------
INSERT INTO `webcom` VALUES ('1', '失落的小白兔', '592318425@qq.com', '写的真好', '2020-07-12 00:07:04', '1');
INSERT INTO `webcom` VALUES ('2', '流氓兔', '17730075906@163.com', '站点走的是小清新的风格哈', '2020-07-12 19:46:35', '1');

-- ----------------------------
-- Table structure for `webrep`
-- ----------------------------
DROP TABLE IF EXISTS `webrep`;
CREATE TABLE `webrep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `com_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of webrep
-- ----------------------------
INSERT INTO `webrep` VALUES ('2', '清风明月的树洞', '蟹蟹夸奖', '2020-07-12 17:25:54', '1');
INSERT INTO `webrep` VALUES ('4', '清风明月的树洞', '是的呢！！！', '2020-07-12 20:11:15', '2');
