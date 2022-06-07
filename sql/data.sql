-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` (`users_id`, `username`, `nickname`, `password`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `create_date`, `update_date`, `deleted`) VALUES (1, 'xuxiaowei', '徐晓伟', '{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK', 1, 1, 1, 1, '2022-04-06 17:32:43', '2022-05-10 05:16:57', 0);

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (1, 'xuxiaowei', 'audit_accessToken_delete', '2022-04-17 22:39:46', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (2, 'xuxiaowei', 'audit_accessToken_read', '2022-04-10 18:31:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (3, 'xuxiaowei', 'audit_code_delete', '2022-04-17 21:19:18', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (4, 'xuxiaowei', 'audit_code_read', '2022-04-08 13:16:41', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (5, 'xuxiaowei', 'audit_refreshToken_delete', '2022-04-17 22:51:25', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (6, 'xuxiaowei', 'audit_refreshToken_read', '2022-04-10 19:42:32', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (7, 'xuxiaowei', 'user_authorities', '2022-04-17 15:56:32', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (8, 'xuxiaowei', 'user_details', '2022-04-17 15:57:08', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (9, 'xuxiaowei', 'user_info', '2022-04-07 01:33:16', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (10, 'xuxiaowei', 'user_oauth2_oauth2Request', '2022-04-17 16:08:14', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (11, 'xuxiaowei', 'user_oauth2_userAuthentication', '2022-04-17 16:08:04', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (12, 'xuxiaowei', 'manage_user_add', '2022-04-30 07:49:16', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (13, 'xuxiaowei', 'manage_user_delete', '2022-04-30 07:49:16', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (14, 'xuxiaowei', 'manage_user_edit', '2022-04-30 07:49:16', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (15, 'xuxiaowei', 'manage_user_read', '2022-04-30 07:49:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (16, 'xuxiaowei', 'manage_user_authority', '2022-05-17 09:21:29', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (17, 'xuxiaowei', 'manage_client_add', '2022-04-30 07:49:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (18, 'xuxiaowei', 'manage_client_delete', '2022-04-30 07:49:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (19, 'xuxiaowei', 'manage_client_edit', '2022-04-30 07:49:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (20, 'xuxiaowei', 'manage_client_read', '2022-04-30 07:49:17', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (21, 'xuxiaowei', 'clientId_token_delete', '2022-06-07 08:22:52', NULL);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`) VALUES (22, 'xuxiaowei', 'username_token_delete', '2022-06-07 08:23:11', NULL);

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_accessToken_delete', '删除授权Token');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_accessToken_read', '查看授权Token');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_code_delete', '删除授权码Code');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_code_read', '查看授权码Code');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_refreshToken_delete', '删除刷新Token');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('audit_refreshToken_read', '查看刷新Token');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('clientId_token_delete', '客户Token 删除权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_client_add', '管理客户 添加权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_client_delete', '管理客户 删除权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_client_edit', '管理客户 修改权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_client_read', '管理客户 读取权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_user_add', '管理用户 查询权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_user_authority', '管理用户 授权权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_user_delete', '管理用户 删除权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_user_edit', '管理用户 修改权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('manage_user_read', '管理用户 查询权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('snsapi_base', NULL);
INSERT INTO `authority`(`authority`, `explain`) VALUES ('snsapi_userinfo', NULL);
INSERT INTO `authority`(`authority`, `explain`) VALUES ('username_token_delete', '用户Token 删除权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('user_authorities', '查看用户权限');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('user_details', '查看用户详情');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('user_info', '查看用户信息');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('user_oauth2_oauth2Request', '查看 oauth2 用户请求');
INSERT INTO `authority`(`authority`, `explain`) VALUES ('user_oauth2_userAuthentication', '查看 oauth2 用户身份验证');

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` (`oauth_client_details_id`, `client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_date`, `update_date`, `deleted`) VALUES (1, 'xuxiaowei_client_id', NULL, '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,client_credentials,password,implicit', 'http://passport.example.xuxiaowei.cloud:1411/code,http://gateway.example.xuxiaowei.cloud:1101/passport/code,https://gateway.example.xuxiaowei.cloud/passport/code', NULL, NULL, NULL, NULL, 'true', '2022-05-25 06:16:20', '2022-05-25 14:21:48', 0);
INSERT INTO `oauth_client_details` (`oauth_client_details_id`, `client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_date`, `update_date`, `deleted`) VALUES (2, 'xuxiaowei_client_wechat_applet_id', NULL, '{bcrypt}$2a$10$R5eMdsuM7W8gEtqPYyRcNOBjZwaYMcpIgl7Yhqubv6KmKANmKFxY.', 'snsapi_base,snsapi_userinfo', 'password', NULL, NULL, NULL, NULL, NULL, NULL, '2022-06-07 08:33:52', NULL, 0);
