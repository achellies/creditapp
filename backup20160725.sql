/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.6.13-log : Database - creditapp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `creditapp`;

/*Table structure for table `app_accumulation_account` */

DROP TABLE IF EXISTS `app_accumulation_account`;

CREATE TABLE `app_accumulation_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `tabs` varchar(255) NOT NULL,
  `datas` varchar(255) NOT NULL,
  `account_password` varchar(255) NOT NULL COMMENT '公积金密码',
  `user_id` int(10) unsigned NOT NULL COMMENT '所属的用户ID',
  `data_version` int(6) DEFAULT NULL COMMENT '记录版本号',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录修改时间',
  `create_user` varchar(128) NOT NULL COMMENT '创建者',
  `update_user` varchar(128) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `app_accumulation_account` */

/*Table structure for table `app_car_illegal_infos` */

DROP TABLE IF EXISTS `app_car_illegal_infos`;

CREATE TABLE `app_car_illegal_infos` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `car_number` varchar(255) NOT NULL COMMENT '车牌号',
  `car_type` int(3) NOT NULL COMMENT '车辆类型',
  `car_code` varchar(20) NOT NULL COMMENT '车架号',
  `car_engine` varchar(20) NOT NULL COMMENT '发动机号',
  `car_illegals` mediumtext NOT NULL COMMENT '违章信息',
  `car_status` varchar(3) NOT NULL COMMENT '查询车辆后的状态',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `data_status` int(2) DEFAULT '1' COMMENT '状态',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `app_car_illegal_infos` */

insert  into `app_car_illegal_infos`(`id`,`car_number`,`car_type`,`car_code`,`car_engine`,`car_illegals`,`car_status`,`user_id`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (1,'1',1,'1','1','1','1',1,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00','1','1');

/*Table structure for table `app_car_infos` */

DROP TABLE IF EXISTS `app_car_infos`;

CREATE TABLE `app_car_infos` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `card_no` varchar(18) NOT NULL COMMENT '身份证号码',
  `license_no` varchar(20) NOT NULL COMMENT '车牌号码',
  `car_type` int(2) NOT NULL COMMENT '号牌种类',
  `car_code` varchar(15) DEFAULT NULL COMMENT '车架号',
  `vin_17` varchar(17) DEFAULT NULL COMMENT '完整的17位车架号',
  `register_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '初次登记日期',
  `car_detail` mediumtext COMMENT '机动车信息',
  `car_status` int(2) DEFAULT NULL COMMENT '车辆状态',
  `data_status` int(2) DEFAULT '1' COMMENT '状态',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(255) NOT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `app_car_infos` */

insert  into `app_car_infos`(`id`,`name`,`card_no`,`license_no`,`car_type`,`car_code`,`vin_17`,`register_time`,`car_detail`,`car_status`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (2,'1','1','1',1,NULL,NULL,NULL,NULL,NULL,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00','SYS',NULL),(3,'2','2','2',2,NULL,NULL,NULL,NULL,NULL,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00','SYS','SYS'),(4,'3','3','3',3,NULL,NULL,NULL,NULL,NULL,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00','SYS','SYS');

/*Table structure for table `app_dissentes` */

DROP TABLE IF EXISTS `app_dissentes`;

CREATE TABLE `app_dissentes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dissent_content` mediumtext NOT NULL COMMENT '异议内容',
  `user_id` int(10) NOT NULL COMMENT '提出异议的用户ID',
  `record_id` int(10) unsigned NOT NULL COMMENT '对应查询记录的ID',
  `data_status` int(2) DEFAULT '1' COMMENT '数据状态',
  `data_version` int(6) DEFAULT '1' COMMENT '记录版本号',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录更新时间',
  `create_user` varchar(128) NOT NULL COMMENT '记录创建者',
  `update_user` varchar(128) DEFAULT NULL COMMENT '记录更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='异议表，记录了用户对某条查询记录的异议内容';

/*Data for the table `app_dissentes` */

insert  into `app_dissentes`(`id`,`dissent_content`,`user_id`,`record_id`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (13,'q',1,1,1,1,'2016-07-21 17:16:28','2016-07-21 17:16:28','SYS','SYS'),(14,'违章停车',2,2,1,1,'2016-07-22 11:00:58','2016-07-22 11:00:58','SYS','SYS'),(15,'您昨天违章停车',3,3,1,1,'2016-07-22 15:02:24','2016-07-22 15:02:24','SYS','SYS'),(16,'',3,3,1,1,'2016-07-22 15:04:18','2016-07-22 15:04:18','SYS','SYS');

/*Table structure for table `app_msg_details` */

DROP TABLE IF EXISTS `app_msg_details`;

CREATE TABLE `app_msg_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `msg_receiver` varchar(120) NOT NULL COMMENT '接收者',
  `msg_title` varchar(100) NOT NULL COMMENT '标题',
  `msg_content` varchar(4000) NOT NULL COMMENT '内容',
  `msg_type` int(2) NOT NULL COMMENT '消息类型类型1-RTX 2-SMS 3-OA 4-邮件 5-图片',
  `verify_code_id` varchar(11) NOT NULL COMMENT '关联到验证码表的主键，标识本条消息发送的验证码',
  `data_status` int(2) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `app_msg_details` */

insert  into `app_msg_details`(`id`,`msg_receiver`,`msg_title`,`msg_content`,`msg_type`,`verify_code_id`,`data_status`,`created_at`,`updated_at`,`create_user`,`update_user`) values (4,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:GM4dDU,请勿泄露！',2,'9',0,'2016-07-18 13:11:33','2016-07-18 13:11:33','',NULL),(5,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:6ic1L5,请勿泄露！',2,'10',0,'2016-07-18 13:30:37','2016-07-18 13:30:37','',NULL),(6,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:ByNK7h,请勿泄露！',2,'11',0,'2016-07-18 13:31:08','2016-07-18 13:31:08','',NULL),(7,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:GKjPtn,请勿泄露！',2,'12',0,'2016-07-18 19:17:56','2016-07-18 19:17:56','',NULL),(8,'18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:sc6kqy,请勿泄露！',2,'13',0,'2016-07-19 17:32:18','2016-07-19 17:32:18','SYS','SYS'),(9,'18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:LaY1st,请勿泄露！',2,'14',0,'2016-07-19 18:01:23','2016-07-19 18:01:23','SYS','SYS'),(10,'18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:77D59V,请勿泄露！',2,'15',0,'2016-07-20 09:41:47','2016-07-20 09:41:47','SYS','SYS'),(11,'18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:mC939S,请勿泄露！',2,'16',0,'2016-07-20 10:47:42','2016-07-20 10:47:42','SYS','SYS'),(12,'18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:76ro70,请勿泄露！',2,'17',0,'2016-07-20 11:26:56','2016-07-20 11:26:56','SYS','SYS'),(13,'18052771830','注册验证码','你好，你正在注册万达征信APP，验证码为:6LuDyC,请勿泄露！',2,'18',0,'2016-07-20 13:30:46','2016-07-20 13:30:46','SYS','SYS'),(14,'18052771832','注册验证码','你好，你正在注册万达征信APP，验证码为:siogIK,请勿泄露！',2,'19',0,'2016-07-20 14:23:01','2016-07-20 14:23:01','SYS','SYS'),(15,'18052771835','注册验证码','你好，你正在注册万达征信APP，验证码为:N876nV,请勿泄露！',2,'20',0,'2016-07-20 14:39:14','2016-07-20 14:39:14','SYS','SYS'),(16,'13400001002','注册验证码','你好，你正在注册万达征信APP，验证码为:Ld22lv,请勿泄露！',2,'22',0,'2016-07-20 15:52:32','2016-07-20 15:52:32','SYS','SYS'),(17,'13400001003','注册验证码','你好，你正在注册万达征信APP，验证码为:rYfDNf,请勿泄露！',2,'23',0,'2016-07-20 17:29:57','2016-07-20 17:29:57','SYS','SYS'),(18,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:XQjDXM,请勿泄露！',2,'24',0,'2016-07-21 15:01:02','2016-07-21 15:01:02','SYS','SYS'),(19,'18052771740','注册验证码','你好，你正在注册万达征信APP，验证码为:HNxqZm,请勿泄露！',2,'25',0,'2016-07-21 15:23:44','2016-07-21 15:23:44','SYS','SYS'),(20,'18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:8gmDZU,请勿泄露！',2,'26',0,'2016-07-21 15:47:12','2016-07-21 15:47:12','SYS','SYS'),(21,'18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:Dqpkdv,请勿泄露！',2,'27',0,'2016-07-21 15:51:47','2016-07-21 15:51:47','SYS','SYS'),(22,'18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:oYUHro,请勿泄露！',2,'28',0,'2016-07-21 15:58:11','2016-07-21 15:58:11','SYS','SYS'),(23,'18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:2zxZdE,请勿泄露！',2,'30',NULL,'2016-07-21 16:26:30','2016-07-21 16:26:30','SYS','SYS'),(24,'18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:YkllFe,请勿泄露！',2,'31',NULL,'2016-07-21 16:42:47','2016-07-21 16:42:47','SYS','SYS'),(25,'18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:j34HZE,请勿泄露！',2,'32',NULL,'2016-07-21 16:43:27','2016-07-21 16:43:27','SYS','SYS'),(26,'18052771851','注册验证码','你好，你正在注册万达征信APP，验证码为:N3nOuA,请勿泄露！',2,'33',NULL,'2016-07-21 16:49:30','2016-07-21 16:49:30','SYS','SYS'),(27,'18052771852','注册验证码','你好，你正在注册万达征信APP，验证码为:p9LEkM,请勿泄露！',2,'34',NULL,'2016-07-22 08:26:35','2016-07-22 08:26:35','SYS','SYS'),(28,'13400001010','注册验证码','你好，你正在注册万达征信APP，验证码为:1jh5I8,请勿泄露！',2,'35',NULL,'2016-07-22 11:19:04','2016-07-22 11:19:04','SYS','SYS'),(29,'13400001010','注册验证码','你好，你正在注册万达征信APP，验证码为:Ddbudq,请勿泄露！',2,'36',NULL,'2016-07-22 11:29:43','2016-07-22 11:29:43','SYS','SYS'),(30,'13400001012','注册验证码','你好，你正在注册万达征信APP，验证码为:VlszFo,请勿泄露！',2,'37',NULL,'2016-07-22 14:27:53','2016-07-22 14:27:53','SYS','SYS'),(31,'13400001013','注册验证码','你好，你正在注册万达征信APP，验证码为:X1CUym,请勿泄露！',2,'38',NULL,'2016-07-25 08:56:56','2016-07-25 08:56:56','SYS','SYS'),(32,'13400001020','注册验证码','你好，你正在注册万达征信APP，验证码为:DJR0cd,请勿泄露！',2,'39',NULL,'2016-07-25 08:58:45','2016-07-25 08:58:45','SYS','SYS'),(33,'13400001021','注册验证码','你好，你正在注册万达征信APP，验证码为:n21pGJ,请勿泄露！',2,'40',NULL,'2016-07-25 09:01:00','2016-07-25 09:01:00','SYS','SYS'),(34,'18052771853','注册验证码','你好，你正在注册万达征信APP，验证码为:oVsszu,请勿泄露！',2,'41',NULL,'2016-07-25 13:13:57','2016-07-25 13:13:57','SYS','SYS'),(35,'18052771854','注册验证码','你好，你正在注册万达征信APP，验证码为:G2wBU7,请勿泄露！',2,'42',NULL,'2016-07-25 13:47:10','2016-07-25 13:47:10','SYS','SYS'),(36,'18052771855','注册验证码','你好，你正在注册万达征信APP，验证码为:J8C6gX,请勿泄露！',2,'43',NULL,'2016-07-25 16:18:18','2016-07-25 16:18:18','SYS','SYS');

/*Table structure for table `app_msg_templates` */

DROP TABLE IF EXISTS `app_msg_templates`;

CREATE TABLE `app_msg_templates` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `template_name` varchar(128) NOT NULL COMMENT '模板名称',
  `msg_title` varchar(128) NOT NULL COMMENT '消息标题',
  `msg_type` int(2) NOT NULL COMMENT '消息类型类型1-RTX 2-SMS 3-OA 4-邮件 5-图片',
  `msg_template` varchar(4000) NOT NULL COMMENT '内容模板',
  `data_status` int(2) DEFAULT '1' COMMENT '数据状态',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `app_msg_templates` */

insert  into `app_msg_templates`(`id`,`template_name`,`msg_title`,`msg_type`,`msg_template`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (1,'注册验证码模板','注册验证码',2,'你好，你正在注册万达征信APP，验证码为:$$verifyCode$$,请勿泄露！',1,1,'2016-07-21 10:38:41','2016-07-18 11:36:55','',NULL);

/*Table structure for table `app_query_record` */

DROP TABLE IF EXISTS `app_query_record`;

CREATE TABLE `app_query_record` (
  `id` int(10) NOT NULL COMMENT '记录ID',
  `parameter` varchar(255) DEFAULT NULL COMMENT '查询参数',
  `result` mediumtext NOT NULL COMMENT '查询结果',
  `query_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '查询时间',
  `user_id` int(10) NOT NULL COMMENT '该报告的拥有者',
  `product_type` varchar(64) NOT NULL COMMENT '产品类型',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `create_user` varchar(64) NOT NULL COMMENT '记录创建者',
  `update_user` varchar(64) DEFAULT NULL COMMENT '记录更新者',
  `data_version` int(6) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `app_query_record` */

/*Table structure for table `app_users` */

DROP TABLE IF EXISTS `app_users`;

CREATE TABLE `app_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uap_pwid` varchar(128) NOT NULL COMMENT 'uap唯一标识',
  `user_phone` varchar(11) NOT NULL COMMENT '手机号',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(128) NOT NULL COMMENT '密码',
  `user_nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `user_gender` int(1) DEFAULT NULL COMMENT '性别',
  `user_email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `user_imgurl` varchar(512) DEFAULT NULL COMMENT '头像访问的URL',
  `user_imgpath` varchar(512) DEFAULT NULL COMMENT '头像保存路径',
  `data_status` int(2) DEFAULT '1' COMMENT '状态1-默认',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `app_users` */

insert  into `app_users`(`id`,`uap_pwid`,`user_phone`,`user_name`,`user_password`,`user_nickname`,`user_gender`,`user_email`,`user_imgurl`,`user_imgpath`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (40,'300111000145407817','18052771855',NULL,'aaa123',NULL,NULL,NULL,NULL,NULL,1,1,'2016-07-25 16:27:52','2016-07-25 16:27:52','SYS','SYS');

/*Table structure for table `app_users_bankcards` */

DROP TABLE IF EXISTS `app_users_bankcards`;

CREATE TABLE `app_users_bankcards` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '对应app_users的id',
  `uap_pwid` varchar(128) DEFAULT NULL COMMENT 'uap唯一标识',
  `card_no` varchar(64) NOT NULL COMMENT '银行卡号',
  `owner_name` varchar(128) NOT NULL COMMENT '持卡人姓名',
  `card_type` int(4) NOT NULL COMMENT '卡类型',
  `bank_mobile_num` varchar(11) DEFAULT NULL COMMENT '银行预留手机号',
  `issuing_bank_code` varchar(64) DEFAULT NULL COMMENT '发卡行编号',
  `issuing_bank_name` varchar(128) DEFAULT NULL COMMENT '发卡行名称',
  `valid_from` varchar(16) DEFAULT NULL COMMENT '有效期(起始)格式:MMYY',
  `valid_thru` varchar(16) DEFAULT NULL COMMENT '有效期(结束)格式:MMYY',
  `check_code` varchar(8) DEFAULT NULL COMMENT '银行卡校验码(卡背面的3位)',
  `external_number` varchar(32) DEFAULT NULL COMMENT '外部编号',
  `data_status` int(2) DEFAULT NULL,
  `data_version` int(6) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `app_users_bankcards` */

/*Table structure for table `app_users_idcards` */

DROP TABLE IF EXISTS `app_users_idcards`;

CREATE TABLE `app_users_idcards` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '对应app_users的id',
  `uap_pwid` varchar(128) DEFAULT NULL COMMENT 'uap唯一标识',
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `idNumber` varchar(64) NOT NULL COMMENT '身份证号',
  `gender` int(1) NOT NULL COMMENT '性别',
  `nation` varchar(32) DEFAULT NULL COMMENT '民族',
  `address` varchar(512) DEFAULT NULL COMMENT '地址',
  `issuing_authority` varchar(128) DEFAULT NULL COMMENT '签发机关',
  `valid_from` date DEFAULT NULL COMMENT '有效期限(起始)格式:yyyyMMdd',
  `valid_thru` date DEFAULT NULL COMMENT '有效期限(结束)格式:yyyyMMdd',
  `data_status` int(2) DEFAULT NULL,
  `data_version` int(6) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `app_users_idcards` */

/*Table structure for table `app_verify_codes` */

DROP TABLE IF EXISTS `app_verify_codes`;

CREATE TABLE `app_verify_codes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `verify_receiver` varchar(128) NOT NULL COMMENT '验证码接受者(根据msgconfig判断是手机号或邮箱)',
  `verify_code` varchar(100) NOT NULL COMMENT '验证码',
  `expired_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '失效时间',
  `verify_type` int(2) NOT NULL COMMENT '验证码类型：1-短信注册2-短信重置密码 3-注册图片',
  `data_status` int(2) NOT NULL DEFAULT '1' COMMENT '状态,0-无效的1-初始状态2-被验证过的',
  `data_version` int(6) NOT NULL DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `app_verify_codes` */

insert  into `app_verify_codes`(`id`,`verify_receiver`,`verify_code`,`expired_time`,`verify_type`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (31,'18052771850','YkllFe','2016-07-21 17:12:46',1,0,2,'2016-07-21 16:42:46','2016-07-21 16:42:46','SYS','SYS'),(32,'18052771850','j34HZE','2016-07-21 17:13:26',1,2,2,'2016-07-21 16:43:26','2016-07-21 16:43:26','SYS','SYS'),(33,'18052771851','N3nOuA','2016-07-21 17:19:29',1,2,2,'2016-07-21 16:49:29','2016-07-21 16:49:29','SYS','SYS'),(34,'18052771852','p9LEkM','2016-07-22 08:56:34',1,2,2,'2016-07-22 08:26:35','2016-07-22 08:26:35','SYS','SYS'),(35,'13400001010','1jh5I8','2016-07-22 11:49:03',1,2,2,'2016-07-22 11:19:03','2016-07-22 11:19:03','SYS','SYS'),(36,'13400001010','Ddbudq','2016-07-22 11:59:42',1,1,1,'2016-07-22 11:29:43','2016-07-22 11:29:43','SYS','SYS'),(37,'13400001012','VlszFo','2016-07-22 14:57:52',1,2,2,'2016-07-22 14:27:53','2016-07-22 14:27:53','SYS','SYS'),(38,'13400001013','X1CUym','2016-07-25 09:26:56',1,2,2,'2016-07-25 08:56:56','2016-07-25 08:56:56','SYS','SYS'),(39,'13400001020','DJR0cd','2016-07-25 09:28:44',1,2,2,'2016-07-25 08:58:44','2016-07-25 08:58:44','SYS','SYS'),(40,'13400001021','n21pGJ','2016-07-25 09:30:59',1,2,2,'2016-07-25 09:00:59','2016-07-25 09:00:59','SYS','SYS'),(41,'18052771853','oVsszu','2016-07-25 13:43:56',1,2,2,'2016-07-25 13:13:56','2016-07-25 13:13:56','SYS','SYS'),(42,'18052771854','G2wBU7','2016-07-25 14:17:09',1,2,2,'2016-07-25 13:47:10','2016-07-25 13:47:10','SYS','SYS'),(43,'18052771855','J8C6gX','2016-07-25 16:48:17',1,2,2,'2016-07-25 16:18:18','2016-07-25 16:18:18','SYS','SYS');

/*Table structure for table `app_verify_configs` */

DROP TABLE IF EXISTS `app_verify_configs`;

CREATE TABLE `app_verify_configs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `config_name` varchar(100) NOT NULL COMMENT '名称',
  `template_code` varchar(8) DEFAULT NULL COMMENT '对应使用的消息模板的id，可以是多个，以逗号隔开',
  `verifyCode_validTime` int(11) DEFAULT '1800' COMMENT '验证码有效时间。默认1800，单位秒',
  `verify_type` int(2) DEFAULT NULL COMMENT '验证码类型：1-短信注册2-短信重置密码 3-注册图片 4-登录图片',
  `verify_length` int(2) NOT NULL DEFAULT '6' COMMENT '生成的验证码长度',
  `verify_charArray` varchar(64) NOT NULL DEFAULT '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' COMMENT '验证码的字符来源。',
  `data_status` int(2) DEFAULT '1' COMMENT '状态',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `app_verify_configs` */

insert  into `app_verify_configs`(`id`,`config_name`,`template_code`,`verifyCode_validTime`,`verify_type`,`verify_length`,`verify_charArray`,`data_status`,`data_version`,`created_at`,`updated_at`,`create_user`,`update_user`) values (2,'注册验证码','1',1800,1,6,'0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz',1,1,'2016-07-21 10:40:23','2016-07-21 10:40:23','',''),(3,'图片验证码','',1800,3,4,'3456789ABCDEFGHJKLMNPQRSTUVWXYabcdefghjklmnpqrstuvwxy',1,1,'2016-07-21 10:40:23','2016-07-21 10:40:23','','');

/*Table structure for table `tsm_messages` */

DROP TABLE IF EXISTS `tsm_messages`;

CREATE TABLE `tsm_messages` (
  `MESSAGEID` varchar(64) NOT NULL COMMENT '消息ID,UUID',
  `TARGET` varchar(48) DEFAULT NULL COMMENT '接受者rtx号或电话号码',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `CONTENT` varchar(4000) DEFAULT NULL COMMENT '消息内容',
  `PRIORITY` int(1) DEFAULT NULL COMMENT '优先级',
  `MESSAGETYPE` int(2) DEFAULT NULL COMMENT '消息类型1-RTX 2-SMS 3-OA',
  `CREATETIME` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `TARGETTIME` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '调度时间',
  `SENDTIME` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发送时间',
  `STATUS` int(1) DEFAULT NULL COMMENT '发送状态0-未处理 1-成功 2-失败',
  `TRYTIMES` int(3) DEFAULT NULL COMMENT '发送次数',
  `ERRORINFO` varchar(256) DEFAULT NULL COMMENT '错误信息',
  PRIMARY KEY (`MESSAGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tsm_messages` */

insert  into `tsm_messages`(`MESSAGEID`,`TARGET`,`TITLE`,`CONTENT`,`PRIORITY`,`MESSAGETYPE`,`CREATETIME`,`TARGETTIME`,`SENDTIME`,`STATUS`,`TRYTIMES`,`ERRORINFO`) values ('04ba5ae7-d34d-45cb-8371-fc7d3ed0561f','18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:2zxZdE,请勿泄露！',1,2,'2016-07-21 16:26:27','2016-07-21 16:26:27','2016-07-21 16:26:27',0,NULL,NULL),('05298286-fe56-4e34-9f29-ee9970370f07','18052771740','注册验证码','你好，你正在注册万达征信APP，验证码为:HNxqZm,请勿泄露！',1,2,'2016-07-21 15:23:43','2016-07-21 15:23:43','2016-07-21 15:23:43',0,NULL,NULL),('07f926f8-14b2-411d-be15-f8b8b7f848ef','18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:Dqpkdv,请勿泄露！',1,2,'2016-07-21 15:51:46','2016-07-21 15:51:46','2016-07-21 15:51:46',0,NULL,NULL),('0cc81343-319c-4aa9-bd97-f2816d576248','18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:YkllFe,请勿泄露！',1,2,'2016-07-21 16:42:46','2016-07-21 16:42:46','2016-07-21 16:42:46',0,NULL,NULL),('19789feb-00cd-4b11-bf5c-869c9a04284f','18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:j34HZE,请勿泄露！',1,2,'2016-07-21 16:43:26','2016-07-21 16:43:26','2016-07-21 16:43:26',0,NULL,NULL),('1aefa0a0-7ac4-4e3d-a8ba-c5cccf25304e','13400001010','注册验证码','你好，你正在注册万达征信APP，验证码为:1jh5I8,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('1ef05a4b-0e13-4307-ae46-4f761c5c370a','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:8gmDZU,请勿泄露！',1,2,'2016-07-21 15:47:11','2016-07-21 15:47:11','2016-07-21 15:47:11',0,NULL,NULL),('209da540-c35b-42c8-b002-ff69d8f4adfe','18052771852','注册验证码','你好，你正在注册万达征信APP，验证码为:p9LEkM,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('2600c25a-61e4-45ee-9813-7bee1f6120a9','18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:76ro70,请勿泄露！',1,2,'2016-07-20 11:26:56','2016-07-20 11:26:56','2016-07-20 11:26:56',0,NULL,NULL),('3117e20d-2dab-42db-b94a-d0cca7f3780a','18052771854','注册验证码','你好，你正在注册万达征信APP，验证码为:G2wBU7,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('3d0ce323-5517-4221-b63e-11bf78180cbb','18052771832','注册验证码','你好，你正在注册万达征信APP，验证码为:siogIK,请勿泄露！',1,2,'2016-07-20 14:23:01','2016-07-20 14:23:01','2016-07-20 14:23:01',0,NULL,NULL),('42145115-b6c2-4146-93b5-fb5de7aaa492','13400001010','注册验证码','你好，你正在注册万达征信APP，验证码为:Ddbudq,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('48cc81b5-34cc-43af-941d-4cef919d7866','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:GKjPtn,请勿泄露！',1,2,'2016-07-18 19:17:54','2016-07-18 19:17:54','2016-07-18 19:17:54',0,NULL,NULL),('4d6b74de-117a-424e-b9db-0d975bb3a252','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:ByNK7h,请勿泄露！',1,2,'2016-07-18 13:30:45','2016-07-18 13:30:45','2016-07-18 13:30:45',0,NULL,NULL),('5905d75d-346e-4d30-ba49-6ce48a90805f','18052771855','注册验证码','你好，你正在注册万达征信APP，验证码为:J8C6gX,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('671ee6cb-8446-4e73-ad64-00276f540f37','18052771850','注册验证码','你好，你正在注册万达征信APP，验证码为:oYUHro,请勿泄露！',1,2,'2016-07-21 15:58:10','2016-07-21 15:58:10','2016-07-21 15:58:10',0,NULL,NULL),('803e04c4-4c41-4f71-9c99-070761166ac1','18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:sc6kqy,请勿泄露！',1,2,'2016-07-19 17:32:18','2016-07-19 17:32:18','2016-07-19 17:32:18',0,NULL,NULL),('852a7d90-acb4-448b-a3d0-35f65bea238c','13400001012','注册验证码','你好，你正在注册万达征信APP，验证码为:VlszFo,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('925252f9-411e-49dc-b49c-d380b3948f4e','18052771853','注册验证码','你好，你正在注册万达征信APP，验证码为:oVsszu,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('930a7ccb-9306-4854-b530-0e8fa573a574','13400001020','注册验证码','你好，你正在注册万达征信APP，验证码为:DJR0cd,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('96d6099d-7566-4178-b37f-08d9ea02bece','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:XQjDXM,请勿泄露！',1,2,'2016-07-21 15:01:01','2016-07-21 15:01:01','2016-07-21 15:01:01',0,NULL,NULL),('977ba89a-728c-4694-9145-4dc54d0470b0','18052771835','注册验证码','你好，你正在注册万达征信APP，验证码为:N876nV,请勿泄露！',1,2,'2016-07-20 14:39:14','2016-07-20 14:39:14','2016-07-20 14:39:14',0,NULL,NULL),('ad2b3741-7fae-4172-9cb2-3df6318223d3','13400001003','注册验证码','你好，你正在注册万达征信APP，验证码为:rYfDNf,请勿泄露！',1,2,'2016-07-20 17:29:55','2016-07-20 17:29:55','2016-07-20 17:29:55',0,NULL,NULL),('b2df29f1-5eb7-47da-97ac-bd24c3784c8c','18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:mC939S,请勿泄露！',1,2,'2016-07-20 10:47:41','2016-07-20 10:47:41','2016-07-20 10:47:41',0,NULL,NULL),('b92ec62d-0bf8-440c-be1b-1ad7b6bc39da','13400001013','注册验证码','你好，你正在注册万达征信APP，验证码为:X1CUym,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('c296edfc-aaf0-42af-b05e-bcc8cbc82711','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:GM4dDU,请勿泄露！',1,2,'2016-07-18 13:11:33','2016-07-18 13:11:33','2016-07-18 13:11:33',0,NULL,NULL),('ce1b8af0-b3ad-4120-9795-7f642221d503','13400001021','注册验证码','你好，你正在注册万达征信APP，验证码为:n21pGJ,请勿泄露！',1,2,NULL,NULL,NULL,0,NULL,NULL),('dc9c59fd-7029-49a1-8662-ae0d61c538c4','18052771851','注册验证码','你好，你正在注册万达征信APP，验证码为:N3nOuA,请勿泄露！',1,2,'2016-07-21 16:49:29','2016-07-21 16:49:29','2016-07-21 16:49:29',0,NULL,NULL),('e67626ca-415f-44e1-8cbe-082439010a0b','13400001002','注册验证码','你好，你正在注册万达征信APP，验证码为:Ld22lv,请勿泄露！',1,2,'2016-07-20 15:52:30','2016-07-20 15:52:30','2016-07-20 15:52:30',0,NULL,NULL),('f4acddc6-cadd-42b0-8f99-82e29be458c4','18052771830','注册验证码','你好，你正在注册万达征信APP，验证码为:6LuDyC,请勿泄露！',1,2,'2016-07-20 13:30:46','2016-07-20 13:30:46','2016-07-20 13:30:46',0,NULL,NULL),('f724b736-6bed-4d1f-942f-e6571a7d8517','18052771756','注册验证码','你好，你正在注册万达征信APP，验证码为:6ic1L5,请勿泄露！',1,2,'2016-07-18 13:30:22','2016-07-18 13:30:22','2016-07-18 13:30:22',0,NULL,NULL),('f889bc7f-6ef6-4b82-9fc7-85eb32a50ec5','18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:77D59V,请勿泄露！',1,2,'2016-07-20 09:41:47','2016-07-20 09:41:47','2016-07-20 09:41:47',0,NULL,NULL),('fd98949a-2364-45eb-baa7-604a284c64da','18052771800','注册验证码','你好，你正在注册万达征信APP，验证码为:LaY1st,请勿泄露！',1,2,'2016-07-19 18:01:23','2016-07-19 18:01:23','2016-07-19 18:01:23',0,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
