/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.27-30306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : 192.168.0.27:30306
 Source Schema         : xuxiaowei_cloud

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 08/04/2023 23:10:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES (7, 1, 'user:authorities', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (8, 1, 'user:details', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (9, 1, 'user:info', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (10, 1, 'user:oauth2_oauth2Request', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (11, 1, 'user:oauth2_userAuthentication', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (12, 1, 'manage_user:add', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (13, 1, 'manage_user:delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (14, 1, 'manage_user:edit', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (15, 1, 'manage_user:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (16, 1, 'manage_user:authority', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (17, 1, 'manage_user:token_delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (18, 1, 'manage_client:add', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (19, 1, 'manage_client:delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (20, 1, 'manage_client:edit', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (21, 1, 'manage_client:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (22, 1, 'manage_client:token_delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (23, 1, 'audit_authorization:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (24, 1, 'audit_authorization:delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (25, 1, 'audit_authorization_consent:delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (26, 1, 'audit_authorization_consent:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (27, 1, 'region:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (28, 1, 'dict:delete', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (29, 1, 'dict:edit', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (30, 1, 'dict:read', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (31, 1, 'dict:add', NULL, '', '2023-04-07 20:54:19', '', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (32, 1, 'user:*', NULL, '1', '2023-04-07 22:55:58', '192.168.0.8', NULL, '2023-04-07 22:56:22', NULL, 1);
INSERT INTO `authorities` VALUES (33, 2, 'user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (34, 2, 'audit_authorization_consent:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (35, 2, 'audit_authorization:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (36, 2, 'manage_user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (37, 2, 'dict:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (38, 2, 'manage_client:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (39, 3, 'user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (40, 3, 'audit_authorization_consent:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (41, 3, 'audit_authorization:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (42, 3, 'manage_user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (43, 3, 'dict:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (44, 3, 'manage_client:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (45, 4, 'user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (46, 4, 'audit_authorization_consent:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (47, 4, 'audit_authorization:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (48, 4, 'manage_user:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (49, 4, 'dict:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `authorities` VALUES (50, 4, 'manage_client:*', NULL, '1', '2023-04-08 17:20:13', '192.168.0.8', NULL, NULL, NULL, 0);

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('audit_authorization:*', '授权记录所有权限');
INSERT INTO `authority` VALUES ('audit_authorization:delete', '删除授权记录权限');
INSERT INTO `authority` VALUES ('audit_authorization:read', '查看授权记录权限');
INSERT INTO `authority` VALUES ('audit_authorization_consent:*', '所有授权同意书权限');
INSERT INTO `authority` VALUES ('audit_authorization_consent:delete', '删除授权同意书权限');
INSERT INTO `authority` VALUES ('audit_authorization_consent:read', '查看授权同意书权限');
INSERT INTO `authority` VALUES ('dict:*', '所有字典权限');
INSERT INTO `authority` VALUES ('dict:add', '添加字典权限');
INSERT INTO `authority` VALUES ('dict:delete', '删除字典权限');
INSERT INTO `authority` VALUES ('dict:edit', '修改字典权限');
INSERT INTO `authority` VALUES ('dict:read', '查看字典权限');
INSERT INTO `authority` VALUES ('manage_client:*', '管理客户 所有权限');
INSERT INTO `authority` VALUES ('manage_client:add', '管理客户 添加权限');
INSERT INTO `authority` VALUES ('manage_client:delete', '管理客户 删除权限');
INSERT INTO `authority` VALUES ('manage_client:edit', '管理客户 修改权限');
INSERT INTO `authority` VALUES ('manage_client:read', '管理客户 读取权限');
INSERT INTO `authority` VALUES ('manage_client:token_delete', '管理客户 删除Token权限');
INSERT INTO `authority` VALUES ('manage_user:*', '管理用户 所有权限');
INSERT INTO `authority` VALUES ('manage_user:add', '管理用户 查询权限');
INSERT INTO `authority` VALUES ('manage_user:authority', '管理用户 授权权限');
INSERT INTO `authority` VALUES ('manage_user:delete', '管理用户 删除权限');
INSERT INTO `authority` VALUES ('manage_user:edit', '管理用户 修改权限');
INSERT INTO `authority` VALUES ('manage_user:read', '管理用户 查询权限');
INSERT INTO `authority` VALUES ('manage_user:token_delete', '用户Token 删除权限');
INSERT INTO `authority` VALUES ('region:read', '查看省市区县镇居委会权限');
INSERT INTO `authority` VALUES ('user:*', '用户所有权限');
INSERT INTO `authority` VALUES ('user:authorities', '查看用户权限');
INSERT INTO `authority` VALUES ('user:details', '查看用户详情');
INSERT INTO `authority` VALUES ('user:info', '查看用户信息');
INSERT INTO `authority` VALUES ('user:oauth2_oauth2Request', '查看 oauth2 用户请求');
INSERT INTO `authority` VALUES ('user:oauth2_userAuthentication', '查看 oauth2 用户身份验证');

-- ----------------------------
-- Records of city_handle
-- ----------------------------

-- ----------------------------
-- Records of county_handle
-- ----------------------------

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('client_type', '客户类型', 605, NULL, NULL, NULL, '1', '2023-04-08 19:11:37', '192.168.0.8', NULL, NULL, NULL, 0);
INSERT INTO `dict` VALUES ('family_relationship', '家庭关系', 1009, 'GB/T 4761-2008', 'https://openstd.samr.gov.cn/bzgk/gb/newGbInfo?hcno=AC29B8CD5B7CFC6D73405F2F7B7D7F00', NULL, '1', '2022-09-16 21:01:41', '192.168.0.8', NULL, '2022-09-16 21:10:47', NULL, 0);
INSERT INTO `dict` VALUES ('reset_password_type', '重置密码类型', 4000, NULL, NULL, NULL, '1', '2022-09-21 09:03:17', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict` VALUES ('sex', '性别', 3606, 'GB/T 2261.1-2003', 'https://openstd.samr.gov.cn/bzgk/gb/newGbInfo?hcno=0FC942D542BC6EE3C707B2647EF81CD8', NULL, '1', '2022-08-23 09:18:44', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict` VALUES ('sms_platform', '短信平台', 100, NULL, NULL, NULL, '1', '2022-09-16 06:38:58', '127.0.0.1', NULL, NULL, NULL, 0);

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` VALUES ('client_type', 'alipay_miniprogram', '支付宝小程序', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'alipay_oplatform_website', '支付宝网站应用', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'dingtalk', '钉钉', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'feishu_webpage', '飞书网站应用', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'gitee', '码云', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'github', 'github', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:20:25', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'gitlab', 'gitlab', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'other', '其他', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'qq_miniprogram', 'QQ小程序', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'qq_website', 'QQ扫码', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'web', '网站应用', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:12:49', '192.168.0.8', NULL, '2023-04-08 19:29:04', NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'wechat_miniprogram', '微信小程序', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'wechat_offiaccount', '微信公众号', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'wechat_oplatform_website', '微信扫码', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'wechat_work_website', '企业微信扫码', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('client_type', 'weibo_website', '微博', NULL, NULL, NULL, NULL, NULL, '1', '2023-04-08 19:28:52', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '01', '本人', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:02:22', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '02', '户主', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:17', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '10', '配偶', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '11', '夫', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '12', '妻', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '20', '子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '21', '独生子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '22', '长子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '23', '次子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '24', '三子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '25', '四子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '26', '五子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '27', '养子或继子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '28', '女婿', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '29', '其他儿子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '30', '女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '31', '独生女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '32', '长女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '33', '次女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '34', '三女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '35', '四女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '36', '五女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '37', '养女或继女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '38', '儿媳', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '39', '其他女儿', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '40', '孙子、孙女或外孙子、外孙女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '41', '孙子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '42', '孙女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '43', '外孙子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '44', '外岁女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '45', '孙媳妇或外孙媳妇', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '46', '孙女婿或外孙女婿', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '47', '曾孙子或外曾孙子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '48', '曾孙女或外曾孙女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '49', '其他穗子、孙女或外孙子、外孙女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '50', '父母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '51', '父亲', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '52', '母亲', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '53', '公公', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '54', '婆婆', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '55', '岳父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '56', '岳母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '57', '继父或养父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '58', '继母或养母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '59', '其他父母关系', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '60', '祖父母或外祖父母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '61', '祖父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '62', '祖母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '63', '外祖父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '64', '外祖母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '65', '配偶的祖父母或外祖父母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '66', '曾祖父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '67', '曾祖母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '68', '配偶的曾祖父母或外曾祖父母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '69', '其他祖父母或外祖父母关系', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '70', '兄弟姐妹', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '71', '兄', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '72', '嫂', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '73', '弟', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '74', '弟媳', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '75', '姐姐', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '76', '姐夫', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '77', '妹妹', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '78', '妹夫', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '79', '其他兄弟姐妹', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '80', '其他', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '81', '伯父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '82', '伯母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '83', '叔父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '84', '婶母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '85', '舅父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '86', '舅母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '87', '姨夫', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '88', '姨母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '89', '姑父', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '90', '姑母', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '91', '堂兄弟、堂姐妹', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '92', '表兄弟、表姐妹', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '93', '侄子', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '94', '侄女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '95', '外甥', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '96', '外甥女', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '97', '其他亲戚', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('family_relationship', '99', '非亲属', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 21:08:24', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('reset_password_type', '1', '管理员重置密码', NULL, '管理员重置密码', NULL, NULL, NULL, '1', '2022-09-21 09:05:58', '127.0.0.1', NULL, '2022-09-21 09:06:46', NULL, 0);
INSERT INTO `dict_data` VALUES ('reset_password_type', '2', '用邮件找回密码', NULL, '用邮件找回密码', NULL, NULL, NULL, '1', '2022-09-21 09:04:41', '127.0.0.1', NULL, '2022-09-21 09:19:28', NULL, 0);
INSERT INTO `dict_data` VALUES ('reset_password_type', '3', '用手机号找回密码', NULL, '用手机号找回密码', NULL, NULL, NULL, '1', '2022-09-21 09:05:24', '127.0.0.1', NULL, '2022-09-21 09:19:31', NULL, 0);
INSERT INTO `dict_data` VALUES ('reset_password_type', '4', '用户重置密码', NULL, '用户重置密码', NULL, NULL, NULL, '1', '2022-09-21 09:18:45', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('sex', '0', '未知', 0, '未知的性别', NULL, NULL, NULL, '1', '2022-08-23 09:22:46', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('sex', '1', '男', 1, '男性', NULL, NULL, NULL, '1', '2022-08-23 09:22:46', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('sex', '2', '女', 2, '女性', NULL, NULL, NULL, '1', '2022-08-23 09:22:46', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('sex', '9', '未说明', 3, '未说明的性别', NULL, NULL, NULL, '1', '2022-08-23 09:23:50', '127.0.0.1', NULL, NULL, NULL, 0);
INSERT INTO `dict_data` VALUES ('sms_platform', '1', '阿里云短信', NULL, NULL, NULL, NULL, NULL, '1', '2022-09-16 07:54:18', '127.0.0.1', NULL, NULL, NULL, 0);

-- ----------------------------
-- Records of group_authorities
-- ----------------------------

-- ----------------------------
-- Records of group_members
-- ----------------------------

-- ----------------------------
-- Records of groups
-- ----------------------------

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
INSERT INTO `oauth2_registered_client` VALUES ('1', 'xuxiaowei_client_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', NULL, '网站', 'client_secret_post,client_secret_basic', 'refresh_token,implicit,client_credentials,authorization_code', 'http://127.0.0.1:1401/code/xuxiaowei_client_id,http://gateway.example.xuxiaowei.cloud:1101/passport/code/xuxiaowei_client_id,https://gateway.example.xuxiaowei.cloud/passport/code/xuxiaowei_client_id', 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'web', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('10', 'xuxiaowei_client_github_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$9SObYifLH3xBRyf7KExvQ.w4bVlmKyFRossIBxL7IsVr9GRYjYM6m', NULL, 'github', 'client_secret_post,client_secret_basic', 'refresh_token,github', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'github', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('11', 'xuxiaowei_client_dingtalk_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$X2tYxfOjjBF82IPaBLr2XuwTAY5gBmfmaN3i9h5OooGS9e/dKDDQy', NULL, '钉钉dingtalk', 'client_secret_post,client_secret_basic', 'refresh_token,dingtalk', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'dingtalk', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('12', 'xuxiaowei_client_qq_miniprogram_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$UNR8xj35/Oj/x4RsaF3o1e3ZTzic2BtpHzFiuFmlri2cbGN03KRCS', NULL, 'QQ小程序', 'client_secret_post,client_secret_basic', 'refresh_token,qq_miniprogram', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'qq_miniprogram', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('13', 'xuxiaowei_client_alipay_miniprogram_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$hlp0UhFzOXNMV0hAsGRNSu8ySLTi0bi8sb/9I47.Ak0xDfHFnAzh2', NULL, '支付宝小程序', 'client_secret_post,client_secret_basic', 'refresh_token,alipay_miniprogram', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'alipay_miniprogram', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('14', 'xuxiaowei_client_alipay_oplatform_website_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$yL65pnAxvmc21rZLGdoQFO0AUyiZ0GscZQqhtdX/NfSt6qS7JaERu', NULL, '支付宝网站应用', 'client_secret_post,client_secret_basic', 'refresh_token,alipay_oplatform_website', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'alipay_oplatform_website', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('15', 'xuxiaowei_client_feishu_webpage_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$sabF440ydPV97QmfdEZPZO8QvYNzzub0g.6rn1cG42Cyx34f4lExG', NULL, '飞书网页应用', 'client_secret_post,client_secret_basic', 'refresh_token,feishu_webpage', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'feishu_webpage', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('16', 'xuxiaowei_client_client_secret_jwt_id', '2022-05-30 17:17:41', '6c63d616146a4c3db7d0d2f3ec6d93ce', NULL, '使用 client_secret_jwt 验证客户凭证使用', 'client_secret_jwt', 'client_credentials', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false,\"settings.client.token-endpoint-authentication-signing-algorithm\":[\"org.springframework.security.oauth2.jose.jws.MacAlgorithm\",\"HS256\"]}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'other', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('17', 'tenant_id_1_client_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$UPOmvbWKsNQdiHC8V4/HlONghc41h5kCAHAv/NFNpjGUrENSkJeWq', NULL, '网站', 'client_secret_post,client_secret_basic', 'refresh_token,implicit,client_credentials,authorization_code', 'http://127.0.0.1:1401/code/tenant_id_1_client_id,http://gateway.example.xuxiaowei.cloud:1101/passport/code/tenant_id_1_client_id,https://gateway.example.xuxiaowei.cloud/passport/code/tenant_id_1_client_id', 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 2, 'web', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('18', 'tenant_id_2_client_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$.GpIDuMuZu.M2cPsvWX15e5pwlZzSK6AmjkyL53gwRhY9AqNE/f6e', NULL, '网站', 'client_secret_post,client_secret_basic', 'refresh_token,implicit,client_credentials,authorization_code', 'http://127.0.0.1:1401/code/tenant_id_2_client_id,http://gateway.example.xuxiaowei.cloud:1101/passport/code/tenant_id_2_client_id,https://gateway.example.xuxiaowei.cloud/passport/code/tenant_id_2_client_id', 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 3, 'web', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('2', 'xuxiaowei_client_wechat_miniprogram_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$fyV3TJPS2VFUYK9qFk7i6O4WoCiWNlDS1VlK/TTgtKJZ4I.Rd7.RO', NULL, '微信小程序', 'client_secret_post,client_secret_basic', 'refresh_token,wechat_miniprogram', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'wechat_miniprogram', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('3', 'xuxiaowei_client_wechat_offiaccount_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$o286LHcchn7ciKotuc3J/.XSxluOpC9BEBxuX38q3ad1KdKnvZtlq', NULL, '微信公众号', 'client_secret_post,client_secret_basic', 'refresh_token,wechat_offiaccount', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'wechat_offiaccount', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('4', 'xuxiaowei_client_wechat_oplatform_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$7cB4PtRs/hrHq69KVpedIOz0RYDMgcTtSUGTlbVOaaoZvf1JZsVwK', NULL, '微信扫码', 'client_secret_post,client_secret_basic', 'refresh_token,wechat_oplatform_website', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'wechat_oplatform_website', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('5', 'xuxiaowei_client_gitee_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$ThNHhwAhm8tNahJmw/0xXOHWQhECdoEkL80qFSMUDIejowzMj2XvW', NULL, '码云Gitee', 'client_secret_post,client_secret_basic', 'refresh_token,gitee', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'gitee', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('6', 'xuxiaowei_client_qq_website_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$UyOxyK18VHMFF/WT196RRuBkjSVl7qogRaBYgs0L/Oe/GZIFC37La', NULL, 'QQ扫码', 'client_secret_post,client_secret_basic', 'refresh_token,qq_website', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'qq_website', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('7', 'xuxiaowei_client_weibo_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$1D70RZBidNgDNSs0/NTXkOdZdCkGZDNXJcvP6Z0bTzryuyZHnbpI6', NULL, '微博扫码', 'client_secret_post,client_secret_basic', 'refresh_token,weibo_website', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'weibo_website', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('8', 'xuxiaowei_client_gitlab_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$7T/85U7ZdQZfyVjXuyuutu1SPwMpRvX/FrrvbQNpY5X64ljWcZDCK', NULL, 'gitlab', 'client_secret_post,client_secret_basic', 'refresh_token,gitlab', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'gitlab', NULL);
INSERT INTO `oauth2_registered_client` VALUES ('9', 'xuxiaowei_client_wechat_work_website_id', '2022-05-30 17:17:41', '{bcrypt}$2a$10$KlRi1sdY9ZGk5LABBPul0udL0U8Z44MAH1d40e5Z0TbNHCYL7H.Ma', NULL, '企业微信扫码', 'client_secret_post,client_secret_basic', 'refresh_token,wechat_work_website', NULL, 'snsapi_base,snsapi_info', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}', 1, 'wechat_work_website', NULL);

-- ----------------------------
-- Records of province_handle
-- ----------------------------

-- ----------------------------
-- Records of reset_password
-- ----------------------------

-- ----------------------------
-- Records of sms
-- ----------------------------

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES (1, '徐晓伟工作室（默认）', 1, 1, NULL, NULL, '2023-04-06 19:13:48', '2023-04-06 19:28:56', '1', NULL, '127.0.0.1', NULL, 0);
INSERT INTO `tenant` VALUES (2, '青岛XX公司', 1, 1, NULL, NULL, '2023-04-08 17:41:53', NULL, '1', NULL, '127.0.0.1', NULL, 0);
INSERT INTO `tenant` VALUES (3, '上海XX公司', 1, 1, NULL, NULL, '2023-04-08 17:41:53', NULL, '1', NULL, '127.0.0.1', NULL, 0);

-- ----------------------------
-- Records of town_handle
-- ----------------------------

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 1, 'xuxiaowei', 'xuxiaowei@xuxiaowei.com.cn', 1, NULL, 0, '徐晓伟', '{bcrypt}$2a$10$OvbERDWiG5AJ7ESejoSsv.Lrjt.JBwlBBo44seP4ERSAHUmhmoXLC', '1', '1994-08-05', 37, 3702, 370213, 370213008, 370213008010, 'XX小区 105-1-505', 1, 1, 1, 1, '2022-04-06 17:32:43', '2023-04-08 18:32:27', 0);
INSERT INTO `users` VALUES (2, 1, 'xuxiaowei-2', 'xuxiaowei-2@xuxiaowei.com.cn', 1, NULL, 0, '徐晓伟-2', '{bcrypt}$2a$10$OvbERDWiG5AJ7ESejoSsv.Lrjt.JBwlBBo44seP4ERSAHUmhmoXLC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2023-04-08 17:16:38', '2023-04-08 18:32:27', 0);
INSERT INTO `users` VALUES (3, 2, 'xuxiaowei', 'xuxiaowei@xuxiaowei.com', 1, NULL, 0, '徐晓伟-青岛', '{bcrypt}$2a$10$OvbERDWiG5AJ7ESejoSsv.Lrjt.JBwlBBo44seP4ERSAHUmhmoXLC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2023-04-08 17:16:38', '2023-04-08 18:32:27', 0);
INSERT INTO `users` VALUES (4, 3, 'zhangsan', 'xuxiaowei@xuxiaowei.cn', 1, NULL, 0, '张三', '{bcrypt}$2a$10$OvbERDWiG5AJ7ESejoSsv.Lrjt.JBwlBBo44seP4ERSAHUmhmoXLC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2023-04-08 17:16:38', '2023-04-08 18:32:27', 0);

-- ----------------------------
-- Records of users_alipay_miniprogram
-- ----------------------------

-- ----------------------------
-- Records of users_alipay_oplatform_website
-- ----------------------------

-- ----------------------------
-- Records of users_dingtalk
-- ----------------------------

-- ----------------------------
-- Records of users_feishu_webpage
-- ----------------------------

-- ----------------------------
-- Records of users_gitee
-- ----------------------------

-- ----------------------------
-- Records of users_github
-- ----------------------------

-- ----------------------------
-- Records of users_gitlab
-- ----------------------------

-- ----------------------------
-- Records of users_login
-- ----------------------------

-- ----------------------------
-- Records of users_qq_miniprogram
-- ----------------------------

-- ----------------------------
-- Records of users_qq_website
-- ----------------------------

-- ----------------------------
-- Records of users_wei_bo_website
-- ----------------------------

-- ----------------------------
-- Records of users_wx_ma
-- ----------------------------

-- ----------------------------
-- Records of users_wx_mp
-- ----------------------------

-- ----------------------------
-- Records of users_wx_open_website
-- ----------------------------

-- ----------------------------
-- Records of users_wx_work_website
-- ----------------------------

-- ----------------------------
-- Records of village_handle
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
