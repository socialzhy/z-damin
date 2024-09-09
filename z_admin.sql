/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : z_admin

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 09/09/2024 16:40:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'POST' COMMENT '请求方法',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作路由',
  `type` int NOT NULL DEFAULT '1' COMMENT '0超管权限 1页面权限 2操作权限',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '权限父节点id',
  `allow_anonymous` tinyint NOT NULL DEFAULT '0' COMMENT '允许匿名访问 0 不允许 1允许',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '描述',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`parent_id`) USING BTREE,
  UNIQUE KEY `uni_method_path` (`method`,`path`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of system_permission
-- ----------------------------
BEGIN;
INSERT INTO `system_permission` (`id`, `method`, `path`, `type`, `parent_id`, `allow_anonymous`, `name`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (-1, '', '*', -1, -1, 0, '超管权限', '超管权限', 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
INSERT INTO `system_permission` (`id`, `method`, `path`, `type`, `parent_id`, `allow_anonymous`, `name`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (1, '', '/system', 1, 0, 0, '系统设置', '系统设置', 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
INSERT INTO `system_permission` (`id`, `method`, `path`, `type`, `parent_id`, `allow_anonymous`, `name`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (2, '', '/system/user', 1, 1, 0, '用户管理', '用户管理', 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
INSERT INTO `system_permission` (`id`, `method`, `path`, `type`, `parent_id`, `allow_anonymous`, `name`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (3, 'POST', '/system/user/add', 2, 11, 0, '新增用户', '新增用户', 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
INSERT INTO `system_permission` (`id`, `method`, `path`, `type`, `parent_id`, `allow_anonymous`, `name`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (8, 'GET', '/test/test', 2, 11, 0, '接口测试', '接口测试', 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '权限父节点id',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` (`id`, `name`, `parent_id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (1, '超级管理员', 0, 0, '2023-11-30 10:33:15', 0, '2023-11-30 10:33:15', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` (`id`, `mobile`, `email`, `username`, `password`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (1, '1', '1', 'admin', '$2a$10$Cp0qBasdGs.fDEQnw9sZCeywCcrNF9VqpE/fewzecCvHu39XDsn.C', '1', 1111, '2022-12-22 15:27:23', 1111, '2022-12-22 15:27:23', 0, 0);
INSERT INTO `system_user` (`id`, `mobile`, `email`, `username`, `password`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (2, '1', '1', 'editor', '$2a$10$Cp0qBasdGs.fDEQnw9sZCeywCcrNF9VqpE/fewzecCvHu39XDsn.C', '1', 1111, '2022-12-22 17:27:30', 1111, '2022-12-22 17:27:30', 0, 0);
INSERT INTO `system_user` (`id`, `mobile`, `email`, `username`, `password`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (3, '', '', 'test', '$2a$10$apImrmCRJokgQRC9qigSOuskAJ0/sBuzYke7hJMg/BRbnjpEw24Su', '', 0, '2024-09-06 11:33:50', 0, '2024-09-06 11:33:50', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for system_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_user_permission`;
CREATE TABLE `system_user_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `permission_id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `create_by` bigint DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户权限关联';

-- ----------------------------
-- Records of system_user_permission
-- ----------------------------
BEGIN;
INSERT INTO `system_user_permission` (`id`, `user_id`, `permission_id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (1, 23, 1, 0, '2023-11-30 10:33:15', 0, '2023-11-30 10:33:15', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联';

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_disabled`, `is_deleted`) VALUES (1, 1, 1, 0, '2023-01-06 17:19:30', 0, '2023-01-06 17:19:30', 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
