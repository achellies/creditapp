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
  `real_name` varchar(30) NOT NULL COMMENT '真实姓名',
  `id_card_num` varchar(36) NOT NULL COMMENT '身份证号',
  `cell_phone_num` varchar(30) NOT NULL COMMENT '手机号',
  `account_name` varchar(64) NOT NULL COMMENT '公积金账户名',
  `account_password` varchar(64) NOT NULL COMMENT '公积金账户密码',
  `region_name` varchar(128) NOT NULL COMMENT '区域名称',
  `region_code` varchar(64) NOT NULL COMMENT '区域编码',
  `region_full_code` varchar(64) NOT NULL COMMENT '区域全编码',
  `region_levels` varchar(10) NOT NULL,
  `user_id` int(10) unsigned NOT NULL COMMENT '所属的用户ID',
  `data_version` int(6) DEFAULT NULL COMMENT '记录版本号',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录修改时间',
  `create_user` varchar(128) NOT NULL COMMENT '创建者',
  `update_user` varchar(128) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Table structure for table `app_car_illegal_info` */

DROP TABLE IF EXISTS `app_car_illegal_info`;

CREATE TABLE `app_car_illegal_info` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Table structure for table `app_car_info` */

DROP TABLE IF EXISTS `app_car_info`;

CREATE TABLE `app_car_info` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `app_dissent` */

DROP TABLE IF EXISTS `app_dissent`;

CREATE TABLE `app_dissent` (
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='异议表，记录了用户对某条查询记录的异议内容';

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

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

/*Table structure for table `app_query_record` */

DROP TABLE IF EXISTS `app_query_record`;

CREATE TABLE `app_query_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serialNo` varchar(128) NOT NULL COMMENT '序列号',
  `productType` varchar(30) NOT NULL COMMENT '产品类型',
  `parameter` varchar(255) DEFAULT NULL COMMENT '输入参数',
  `response` text NOT NULL COMMENT '返回结果',
  `userId` int(10) DEFAULT NULL COMMENT '用户ID',
  `data_version` int(10) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录更新时间',
  `create_user` varchar(128) NOT NULL COMMENT '记录创建者',
  `update_user` varchar(128) NOT NULL COMMENT '记录更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `app_users` */

DROP TABLE IF EXISTS `app_users`;

CREATE TABLE `app_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uap_pwid` varchar(128) NOT NULL COMMENT '统一账户平台用户唯一标识',
  `user_phone` varchar(11) NOT NULL COMMENT '手机号',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(128) NOT NULL COMMENT '密码',
  `user_relname` varchar(128) DEFAULT NULL COMMENT '真实姓名',
  `user_gender` int(1) DEFAULT NULL COMMENT '性别',
  `user_id_number` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `user_type` int(2) DEFAULT NULL COMMENT '用户类型',
  `user_nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `user_email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `user_imgurl` varchar(512) DEFAULT NULL COMMENT '头像访问的URL',
  `user_imgpath` varchar(512) DEFAULT NULL COMMENT '头像保存路径',
  `user_message` varchar(256) DEFAULT NULL COMMENT '预留信息',
  `data_status` int(2) DEFAULT '1' COMMENT '状态',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `app_verify_codes` */

DROP TABLE IF EXISTS `app_verify_codes`;

CREATE TABLE `app_verify_codes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `verify_receiver` varchar(128) NOT NULL COMMENT '验证码接受者(根据msgconfig判断是手机号或邮箱)',
  `verify_code` varchar(100) NOT NULL COMMENT '验证码',
  `expired_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '失效时间',
  `verify_type` int(2) NOT NULL COMMENT '验证码类型：1-短信注册2-短信重置密码 3-注册图片',
  `data_status` int(2) DEFAULT '1' COMMENT '状态0-无效的1-初始状态2-被验证过的',
  `data_version` int(6) DEFAULT '1' COMMENT '版本',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(128) NOT NULL,
  `update_user` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
