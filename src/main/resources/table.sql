drop database if exists mybatis_test;
create database mybatis_test;
alter database mybatis_test character set utf8;
use mybatis_test;
create table `t_user` (
	`id` int primary key auto_increment,
	`username` varchar(100),
	`password` varchar(100),
	`nickname` varchar(100)
);