-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`(`username`, `password`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `update_date`, `create_date`, `deleted`) VALUES ('xuxiaowei', '{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK', 1, 1, 1, 1, NULL, '2022-04-06 17:32:43', 0);

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (1, 'xuxiaowei', 'audit_accessToken_delete', '2022-04-17 22:39:46', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (2, 'xuxiaowei', 'audit_accessToken_read', '2022-04-10 18:31:17', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (3, 'xuxiaowei', 'audit_code_delete', '2022-04-17 21:19:18', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (4, 'xuxiaowei', 'audit_code_read', '2022-04-08 13:16:41', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (5, 'xuxiaowei', 'audit_refreshToken_delete', '2022-04-17 22:51:25', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (6, 'xuxiaowei', 'audit_refreshToken_read', '2022-04-10 19:42:32', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (7, 'xuxiaowei', 'user_authorities', '2022-04-17 15:56:32', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (8, 'xuxiaowei', 'user_details', '2022-04-17 15:57:08', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (9, 'xuxiaowei', 'user_info', '2022-04-07 01:33:16', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (10, 'xuxiaowei', 'user_oauth2_oauth2Request', '2022-04-17 16:08:14', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (11, 'xuxiaowei', 'user_oauth2_userAuthentication', '2022-04-17 16:08:04', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (12, 'xuxiaowei', 'manage_user_add', '2022-04-30 07:49:16', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (13, 'xuxiaowei', 'manage_user_delete', '2022-04-30 07:49:16', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (14, 'xuxiaowei', 'manage_user_edit', '2022-04-30 07:49:16', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (15, 'xuxiaowei', 'manage_user_read', '2022-04-30 07:49:17', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (16, 'xuxiaowei', 'manage_user_authority', '2022-05-17 09:21:29', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (17, 'xuxiaowei', 'manage_client_add', '2022-04-30 07:49:17', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (18, 'xuxiaowei', 'manage_client_delete', '2022-04-30 07:49:17', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (19, 'xuxiaowei', 'manage_client_edit', '2022-04-30 07:49:17', NULL, 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `create_date`, `update_date`, `deleted`) VALUES (20, 'xuxiaowei', 'manage_client_read', '2022-04-30 07:49:17', NULL, 0);

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` (`oauth_client_details_id`, `client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES (1, 'xuxiaowei_client_id', NULL, '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,client_credentials,password,implicit', 'http://passport.example.xuxiaowei.cloud:1411/code,http://gateway.example.xuxiaowei.cloud:1101/passport/code,https://gateway.example.xuxiaowei.cloud/passport/code', NULL, NULL, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` (`oauth_client_details_id`, `client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES (2, 'xuxiaowei_client_wechat_applet_id', NULL, '{bcrypt}$2a$10$R5eMdsuM7W8gEtqPYyRcNOBjZwaYMcpIgl7Yhqubv6KmKANmKFxY.', 'snsapi_base,snsapi_userinfo', 'password', NULL, NULL, NULL, NULL, NULL, NULL);
