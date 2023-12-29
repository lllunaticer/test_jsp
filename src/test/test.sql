CREATE TABLE `admin_batch_create_natural_user`
(
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
`phone_number` varchar(64) DEFAULT NULL COMMENT '用户手机号',
`password` varchar(128) NOT NULL DEFAULT '' COMMENT '用户密码',
`kwai_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'kwaiID',
`user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
`bucket` varchar(20) NOT NULL DEFAULT '' COMMENT '创建时的用户桶',
`device_id` varchar(50) NOT NULL DEFAULT '' COMMENT '设备ID',
`admin_id` bigint(20) DEFAULT '0' COMMENT '操作人' COMMENT '账号创建人',
`create_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
PRIMARY KEY (`id`), UNIQUE KEY `uniq_user` (`user_id`) )
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert into `admin_batch_create_natural_user` (user_id, phone_number, password, kwai_id, user_name, bucket, device_id, admin_id, create_time) values
()