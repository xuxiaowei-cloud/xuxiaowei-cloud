-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`(`username`, `password`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `update_date`, `create_date`, `deleted`) VALUES ('xuxiaowei', '{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK', 1, 1, 1, 1, NULL, '2022-04-06 17:32:43', 0);

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES (1, 'xuxiaowei', 'user', '2022-04-10 21:25:00', '2022-04-07 01:33:16', 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES (2, 'xuxiaowei', 'audit_code_read', NULL, '2022-04-08 13:16:41', 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES (3, 'xuxiaowei', 'audit_accessToken_read', '2022-04-10 21:24:58', '2022-04-10 18:31:17', 0);
INSERT INTO `authorities` (`authorities_id`, `username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES (4, 'xuxiaowei', 'audit_refreshToken_read', '2022-04-10 21:24:56', '2022-04-10 19:42:32', 0);

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('xuxiaowei_client_id', NULL, '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,client_credentials', 'http://passport.example.xuxiaowei.cloud:1401/code,http://gateway.example.xuxiaowei.cloud:1101/passport/code,https://gateway.example.xuxiaowei.cloud/passport/code', NULL, NULL, NULL, NULL, 'true');
