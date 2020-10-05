SET FOREIGN_KEY_CHECKS=0;

USE `userinfo`;

#
# Data for the `account_main` table  (LIMIT 0,500)
#

INSERT INTO `account_main` (`accountid`, `account`, `passwd`, `logintime`, `status`, `updatetime`, `will1`) VALUES 
  (1,'zhangsan','123456',0,0,NULL,NULL),
  (2,'lisi','snksnk',0,0,NULL,NULL);
COMMIT;

#
# Data for the `person_main` table  (LIMIT 0,500)
#

INSERT INTO `person_main` (`personid`, `pname`, `sex`, `age`, `prefer`, `address`, `status`, `updatetime`, `will1`) VALUES 
  (1,'张三','男',21,'足球，游泳','四川大学望江校区三号楼',0,'2020-03-01 00:35:57',NULL),
  (2,'李四','女',19,'保龄球','四川大学望江校区5号宿舍楼',0,'2020-03-01 00:50:46',NULL),
  (3,'唐小林','男',23,'篮球，台球','成都理工大学5号宿舍楼',0,NULL,NULL),
  (4,'王诗妤','女',23,'写作，绘画','四川师范大学12号宿舍楼',0,'2020-03-01 00:51:54',NULL);
COMMIT;
