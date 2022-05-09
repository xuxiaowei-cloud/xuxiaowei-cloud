package cloud.xuxiaowei.system.service;

import javax.servlet.http.HttpSession;

/**
 * {@link HttpSession} 服务接口
 *
 * @author xuxiaowei
 * @see 0.0.1
 */
public interface SessionService {

    /**
     * 获取 Token
     *
     * @return 返回 Token
     */
    String getTokenValue();

}
