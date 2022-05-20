-- 创建一个表
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 创建一个索引
CREATE INDEX `idx_name` ON `test` (`name`);
-- 创建一个触发器
CREATE TRIGGER `trig_test` AFTER INSERT ON `test` FOR EACH ROW BEGIN
  INSERT INTO `test_log` (`id`, `name`, `age`) VALUES (new.id, new.name, new.age);
END;
-- 制造一些数据
INSERT INTO `test` (`name`, `age`) VALUES ('张三', 18);
INSERT INTO `test` (`name`, `age`) VALUES ('李四', 19);
INSERT INTO `test` (`name`, `age`) VALUES ('王五', 20);
INSERT INTO `test` (`name`, `age`) VALUES ('赵六', 21);
-- 查询名字为张三的数据
SELECT * FROM `test` WHERE `name` = '张三';