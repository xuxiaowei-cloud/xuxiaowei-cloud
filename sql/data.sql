-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('xuxiaowei', '{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK', 1);

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('xuxiaowei', 'user');

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('xuxiaowei_client_id', NULL, '{bcrypt}$2a$10$s/3CEiHunH9wo2qr7JfeD.SRa8kK2Y8lOriHWrWhidQX3hyhuORlO', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,client_credentials', 'http://passport.example.xuxiaowei.cloud:1411/code,https://passport.example.xuxiaowei.cloud/code', NULL, NULL, NULL, NULL, 'true');
