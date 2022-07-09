package cloud.xuxiaowei.example.cxfclient.service;

import cloud.xuxiaowei.example.cxfclient.bo.UserBo;
import cloud.xuxiaowei.example.cxfclient.vo.ResponseUserVo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 用户 WebService 接口-客户端
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@WebService(targetNamespace = "http://webservice.xuxiaowei.cloud")
public interface UserServiceClient {

	/**
	 * 根据 用户ID 查询用户
	 * @param userBo 用户ID
	 * @return 返回 用户
	 */
	@WebMethod
	@WebResult(name = "response")
	ResponseUserVo getById(@WebParam(name = "request") UserBo userBo);

}
