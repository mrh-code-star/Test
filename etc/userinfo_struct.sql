SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `userinfo`;

CREATE DATABASE `userinfo`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `userinfo`;

#
# Structure for the `account_main` table : 
#

CREATE TABLE `account_main` (
  `accountid` bigint(20) unsigned NOT NULL auto_increment COMMENT '记录流水号',
  `account` varchar(20) NOT NULL COMMENT '登录账号',
  `passwd` varchar(40) NOT NULL COMMENT '登录口令',
  `logintime` bigint(20) default '0' COMMENT '最近登录时间',
  `status` smallint(6) unsigned default '0' COMMENT '状态',
  `updatetime` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `will1` varbinary(100) default NULL COMMENT '预留1',
  PRIMARY KEY  (`accountid`),
  UNIQUE KEY `accountid` (`accountid`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `person_main` table : 
#

CREATE TABLE `person_main` (
  `personid` bigint(20) unsigned NOT NULL auto_increment COMMENT '记录流水号',
  `pname` varchar(20) NOT NULL COMMENT '用户姓名',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `age` int(11) default '18' COMMENT '年龄',
  `prefer` varchar(100) default NULL COMMENT '兴趣爱好',
  `address` varchar(100) default NULL COMMENT '现在住址',
  `status` smallint(6) default '0' COMMENT '状态',
  `updatetime` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新日期',
  `will1` varbinary(100) default NULL COMMENT '预留1',
  PRIMARY KEY  (`personid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

