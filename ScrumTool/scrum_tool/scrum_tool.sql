

-- ----------------------------
--用户
-- ----------------------------
DROP TABLE IF EXISTS `scrum_user`;
CREATE TABLE `scrum_user` (
`user_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`user_no`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户32位16进制ID' ,
`team_id`  bigint(11) COMMENT 'FK' ,
`user_name`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名' ,
`password`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' ,
`safety_factor`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全因子，用于密码安全' ,
`real_name`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名' ,
`role`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' ,
`email`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EMAIL' ,
`gender`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别' ,
`create_time`  datetime NULL DEFAULT NOW() COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SCRUM_USER_NO` ON `scrum_user`(`user_no`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_USER_USER_NAME` ON `scrum_user`(`user_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_USER_REAL_NAME` ON `scrum_user`(`real_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_USER_EMAIL` ON `scrum_user`(`email`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_USER_TEAM_ID` ON `scrum_user`(`team_id`) USING BTREE ;


-- ----------------------------
--迭代
-- ----------------------------
DROP TABLE IF EXISTS `scrum_sprint`;
CREATE TABLE `scrum_sprint` (
`sprint_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`team_id`  bigint(11) COMMENT '所属于团队ID' ,
`team_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队名称' ,
`sprint_name`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迭代名称' ,
`mail_to` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '晨会邮件发送人，多人以逗号隔开' ,
`cc_to` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送的人，多人以逗号隔开' ,
`auto_send_email` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否自动发送邮件' ,
`plan_cycle` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划迭代周期' ,
`real_cycle` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际迭代周期' ,
`create_time` datetime not null default now() COMMENT '创建时间' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`sprint_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SCRUM_SPRINT_TEAM_NAME` ON `scrum_sprint`(`team_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_SPRINT_SPRINT_NAME` ON `scrum_sprint`(`sprint_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_SPRINT_TEAM_ID` ON `scrum_sprint`(`team_id`) USING BTREE ;



-- ----------------------------
--迭代 小组
-- ----------------------------
DROP TABLE IF EXISTS `sprint_teams`;
CREATE TABLE `sprint_teams` (
`team_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`team_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队名称' ,
`create_time` datetime not null default now() COMMENT '创建时间' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`team_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SPRINT_TEAM_NAME` ON `sprint_teams`(`team_name`) USING BTREE ;

-- ----------------------------
--迭代任务
-- ----------------------------
DROP TABLE IF EXISTS `sprint_task`;
CREATE TABLE `sprint_task` (
`task_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`task_no`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务编号' ,
`task_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称' ,
`sprint_id`  bigint(11) NOT NULL COMMENT '所属迭代ID' ,
`elapsed_time` smallint(3) NOT NULL COMMENT '耗时，所需开发时间，单位小时' ,
`create_time` datetime not null default now() COMMENT '创建时间' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`task_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SCRUM_TASK_TASK_NO` ON `sprint_task`(`task_no`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_NAME` ON `sprint_task`(`task_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_SCRUM_ID` ON `sprint_task`(`sprint_id`) USING BTREE ;


-- ----------------------------
--迭代任务拆分
-- ----------------------------
DROP TABLE IF EXISTS `sprint_task_item`;
CREATE TABLE `sprint_task_item` (
`task_item_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`task_item_no`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务拆分编号' ,
`task_id`  bigint(11) NOT NULL COMMENT '所属任务' ,
`task_item_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务拆分名' ,
`elapsed_time` smallint(3) NOT NULL COMMENT '所需开发时间，单位小时' ,
`developer`  bigint(11) NOT NULL COMMENT '所属开发人员' ,
`developer_percent` smallint(3) NOT NULL DEFAULT 0 COMMENT '开发完成百分比' ,
`is_test` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否送测' ,
`testor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '测试人员，多个以逗号隔开' ,
`test_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '测试号' ,
`test_percent` smallint(3) NOT NULL DEFAULT 0 COMMENT '测试完成百分比' ,
`is_on_line` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL  DEFAULT 'N' COMMENT '是否上线' ,
`on_line_date` date NULL COMMENT '上线日期' ,
`create_time` datetime not null default now() COMMENT '创建时间' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`task_item_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SCRUM_TASK_ITEM_NO` ON `sprint_task_item`(`task_item_no`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_ITEM_TASK_ID` ON `sprint_task_item`(`task_id`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_ITEM_NAME` ON `sprint_task_item`(`task_item_name`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_ITEM_DEVELOPER` ON `sprint_task_item`(`developer`) USING BTREE ;
CREATE INDEX `IDX_SCRUM_TASK_ITEM_TESTOR` ON `sprint_task_item`(`testor`) USING BTREE ;



-- ----------------------------
--迭代每日纪要
-- ----------------------------
DROP TABLE IF EXISTS `sprint_daily_report`;
CREATE TABLE `sprint_daily_report` (
`report_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`report_no`  char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告编号，时间戳2016/09/01' ,
`sprint_id`  bigint(11) NOT NULL COMMENT '所属 迭代ID' ,
`user_id`  bigint(11) NOT NULL COMMENT '用户ID，当前纪要是谁的' ,
`yesterday_report` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '昨日工作纪要，多条以逗号隔开' ,
`today_plan` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '今日工作计划，多条以逗号隔开' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`report_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_SPRINT_DAILY_REPORT_NO` ON `sprint_daily_report`(`report_no`) USING BTREE ;
CREATE INDEX `IDX_SPRINT_DAILY_REPORT_SPRINT_ID` ON `sprint_daily_report`(`sprint_id`) USING BTREE ;
CREATE INDEX `IDX_SPRINT_DAILY_REPORT_USER_ID` ON `sprint_daily_report`(`user_id`) USING BTREE ;


-- ----------------------------
--每日线上问题,投诉 汇总
-- ----------------------------
DROP TABLE IF EXISTS `daily_line_problem`;
CREATE TABLE `daily_line_problem` (
`line_problem_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
`line_problem_no`  char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉批号 如 2016-09-10' ,
`problem_describe` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题描述' ,
`problem_analysis` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题分析' ,
`ways_of_resolution` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '解决方式' ,
`processor` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '处理人，多人以逗号隔开' ,
`watch` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '值班人' ,
`is_solved` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '是否解决' ,
`solved_time` datetime null COMMENT '解决时间，什么时间解决的问题' ,
`is_repeat` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否重复问题' ,
`need_follow_up` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否需要跟进' ,
`report_time` datetime not null COMMENT '报告时间，什么时间反馈的投诉问题' ,
`create_time` datetime not null default now() COMMENT '创建时间' ,
`update_time` datetime null COMMENT '修改日期' ,
`remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
PRIMARY KEY (`line_problem_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='消费者'
;
--创建索引
CREATE INDEX `IDX_DAILY_LINE_PROBLEM_NO` ON `daily_line_problem`(`line_problem_no`) USING BTREE ;
CREATE INDEX `IDX_DAILY_LINE_PROBLEM_WATCH` ON `daily_line_problem`(`watch`) USING BTREE ;
CREATE INDEX `IDX_DAILY_LINE_PROBLEM_REPORT_TIME` ON `daily_line_problem`(`report_time`) USING BTREE ;
CREATE INDEX `IDX_DAILY_LINE_PROBLEM_CREATE_TIME` ON `daily_line_problem`(`create_time`) USING BTREE ;


