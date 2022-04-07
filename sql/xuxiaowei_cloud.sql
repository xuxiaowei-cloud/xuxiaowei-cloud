/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.5.4-docker
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 192.168.5.4:3306
 Source Schema         : xuxiaowei_cloud

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/04/2022 17:28:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authority` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  UNIQUE INDEX `ix_auth_username`(`username`, `authority`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表。\r\n原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl\r\n原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`  (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` mediumblob NULL,
  `token_json` json NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authentication` mediumblob NULL,
  `authentication_json` json NULL,
  `refresh_token` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  INDEX `idx__oauth_access_token__authentication_id`(`authentication_id`) USING BTREE,
  INDEX `idx__oauth_access_token__token_id`(`token_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`  (
  `userId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expiresAt` timestamp(0) NULL DEFAULT NULL,
  `lastModifiedAt` timestamp(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(0) NULL DEFAULT NULL,
  `refresh_token_validity` int(0) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`  (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` mediumblob NULL,
  `token_json` json NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权码，唯一键：uk__oauth_code__code',
  `authentication` mediumblob NULL COMMENT '权限',
  `authentication_json` json NULL COMMENT '权限JSON',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  UNIQUE INDEX `uk__oauth_code__code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` mediumblob NULL,
  `token_json` json NULL,
  `authentication` mediumblob NULL,
  `authentication_json` json NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名，不能为空，唯一键：uk__users__username',
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码，不能为空',
  `enabled` tinyint(1) NOT NULL COMMENT '是否启用，不能为空',
  `account_non_expired` tinyint(1) NOT NULL COMMENT '帐户未过期，不能为空',
  `credentials_non_expired` tinyint(1) NOT NULL COMMENT '凭证未过期，不能为空',
  `account_non_locked` tinyint(1) NOT NULL COMMENT '帐户未锁定，不能为空',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间，未更新时为空',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，不为空，数据库自动生成',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。',
  UNIQUE INDEX `uk__users__username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表。\r\n原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl\r\n原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl\r\nGitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl\r\nGitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
