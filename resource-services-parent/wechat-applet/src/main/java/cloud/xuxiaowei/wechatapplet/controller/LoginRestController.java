package cloud.xuxiaowei.wechatapplet.controller;

import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
public class LoginRestController {

    private WxMaService wxMaService;

    @Autowired
    public void setWxMaService(WxMaService wxMaService) {
        this.wxMaService = wxMaService;
    }

    /**
     * 微信小程序登录
     *
     * @param request  请求
     * @param response 响应
     * @param code     微信小程序登录code
     * @return 返回登录结果
     */
    @RequestMapping("/onLogin")
    public Response<?> onLogin(HttpServletRequest request, HttpServletResponse response, String code) {

        WxMaJscode2SessionResult wxMaJscode2SessionResult;
        try {
            wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(code);
            log.info(String.valueOf(wxMaJscode2SessionResult));
        } catch (WxErrorException e) {
            log.error("微信小程序登录失败", e);
            return Response.error();
        }

        return ResponseMap.ok().put("openid", wxMaJscode2SessionResult.getOpenid()).put("unionid", wxMaJscode2SessionResult.getUnionid());
    }

}
