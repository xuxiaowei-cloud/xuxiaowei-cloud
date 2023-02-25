package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersWxWorkWebsite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业微信-网站用户 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-29
 */
public interface UsersWxWorkWebsiteMapper extends BaseMapper<UsersWxWorkWebsite> {

	/**
	 * 根据 企业ID、应用ID、用户唯一标识 查询用户
	 * @param appid 企业ID
	 * @param agentid 应用ID
	 * @param openid 用户唯一标识
	 * @return 用户
	 */
	UsersWxWorkWebsite getByAppidAndAgentidAndOpenid(@Param("appid") String appid, @Param("agentid") String agentid,
			@Param("openid") String openid);

}
