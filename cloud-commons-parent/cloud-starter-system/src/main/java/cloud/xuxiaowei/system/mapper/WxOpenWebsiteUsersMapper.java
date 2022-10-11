package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.WxOpenWebsiteUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 微信开放平台-网站用户 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-01
 */
public interface WxOpenWebsiteUsersMapper extends BaseMapper<WxOpenWebsiteUsers> {

	/**
	 * 根据 微信开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	WxOpenWebsiteUsers getByAppidAndOpenid(@Param("appid") String appid, @Param("openid") String openid);

}
