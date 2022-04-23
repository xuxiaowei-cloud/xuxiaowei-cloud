package cloud.xuxiaowei.webservice.service;

import cloud.xuxiaowei.webservice.bo.UserBo;
import cloud.xuxiaowei.webservice.vo.ResponseUserVo;

/**
 * 用户 WebService 接口
 *
 * @author 徐晓伟
 */
public interface UserService {

    /**
     * 根据 用户ID 查询用户
     *
     * @param userBo 用户ID
     * @return 返回 用户
     */
    ResponseUserVo getById(UserBo userBo);

}
