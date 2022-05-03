drop table if exists `test`;
create table `test` (
  `id` bigint not null comment 'id',
  `name` varchar(50) not null comment '名称',
  `password` varchar(50) not null comment '密码',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='test table';