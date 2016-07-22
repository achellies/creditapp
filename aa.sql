/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.6.13-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

insert into `app_verify_configs` (`config_name`, `template_code`, `verifyCode_validTime`, `verify_type`, `verify_length`, `verify_charArray`, `data_status`, `data_version`, `created_at`, `updated_at`, `create_user`, `update_user`) values('注册验证码','1','1800','1','6','0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz','1','1','2016-07-21 10:40:23','2016-07-21 10:40:23','','');
insert into `app_verify_configs` (`config_name`, `template_code`, `verifyCode_validTime`, `verify_type`, `verify_length`, `verify_charArray`, `data_status`, `data_version`, `created_at`, `updated_at`, `create_user`, `update_user`) values('图片验证码','','1800','3','4','3456789ABCDEFGHJKLMNPQRSTUVWXYabcdefghjklmnpqrstuvwxy','1','1','2016-07-21 10:40:23','2016-07-21 10:40:23','','');
