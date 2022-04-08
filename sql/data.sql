-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`(`username`, `password`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `update_date`, `create_date`, `deleted`) VALUES ('xuxiaowei', '{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK', 1, 1, 1, 1, NULL, '2022-04-06 17:32:43', 0);

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities`(`username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES ('xuxiaowei', 'user', NULL, '2022-04-06 17:33:16', 0);
INSERT INTO `authorities`(`username`, `authority`, `update_date`, `create_date`, `deleted`) VALUES ('xuxiaowei', 'audit_code_read', NULL, '2022-04-08 05:16:41', 0);

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('xuxiaowei_client_id', NULL, '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,client_credentials', 'http://passport.example.xuxiaowei.cloud:1411/code,http://gateway.example.xuxiaowei.cloud:1101/passport/code,https://gateway.example.xuxiaowei.cloud/passport/code', NULL, NULL, NULL, NULL, 'true');
