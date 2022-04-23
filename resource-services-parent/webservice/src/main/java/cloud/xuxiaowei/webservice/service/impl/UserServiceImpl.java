package cloud.xuxiaowei.webservice.service.impl;

import cloud.xuxiaowei.webservice.bo.UserBo;
import cloud.xuxiaowei.webservice.service.UserService;
import cloud.xuxiaowei.webservice.vo.ResponseUserVo;
import cloud.xuxiaowei.webservice.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 用户 WebService 接口
 *
 * @author 徐晓伟
 */
@Service
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@WebService(targetNamespace = "http://webservice.xuxiaowei.cloud")
public class UserServiceImpl implements UserService {

    /**
     * 根据 用户ID 查询用户
     *
     * @param userBo 用户ID
     * @return 返回 用户
     */
    @Override
    @WebMethod
    @WebResult(name = "response")
    public ResponseUserVo getById(@WebParam(name = "request") UserBo userBo) {
        UserVo userVo = new UserVo();

        String id = userBo.getId();

        userVo.setId(id);
        userVo.setUsername("用户-" + id);
        userVo.setPassword("用户-" + id + "-密码");
        return ResponseUserVo.ok(userVo);
    }

}
