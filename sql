-- z_admin.system_permission definition

CREATE TABLE `system_permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'POST' COMMENT '请求方法',
  `path` varchar(255) NOT NULL DEFAULT '',
  `type` int NOT NULL DEFAULT '1' COMMENT '1页面权限 2操作权限',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '权限父节点id',
  `allow_anonymous` tinyint NOT NULL DEFAULT '0' COMMENT '允许匿名访问 0 不允许 1允许',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '描述',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_method_path` (`method`,`path`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';


-- z_admin.system_role definition

CREATE TABLE `system_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '权限父节点id',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';


-- z_admin.`system_user` definition

CREATE TABLE `system_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `mobile` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- z_admin.system_user_permission definition

CREATE TABLE `system_user_permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `permission_id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `create_by` int DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户权限关联';


-- z_admin.system_user_role definition

CREATE TABLE `system_user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_by` int NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联';



INSERT INTO z_admin.system_permission (`method`, `path`, `type`, parent_id, allow_anonymous, name, description, create_by, create_time, update_by, update_time, is_disabled, is_deleted) VALUES('POST', '', 1, 0, 0, '', '', 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, 0);
INSERT INTO z_admin.system_role (name, parent_id, create_by, create_time, update_by, update_time, is_disabled, is_deleted) VALUES('', 0, 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, 0);
INSERT INTO z_admin.`system_user` (mobile, email, username, password, name, create_by, create_time, update_by, update_time, is_disabled, is_deleted) VALUES('', '', '', '', '', 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, 0);
INSERT INTO z_admin.system_user_permission (user_id, permission_id, create_by, create_time, update_by, update_time, is_disabled, is_deleted) VALUES(0, 0, 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, 0);
INSERT INTO z_admin.system_user_role (user_id, role_id, create_by, create_time, update_by, update_time, is_disabled, is_deleted) VALUES(0, 0, 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, 0);
