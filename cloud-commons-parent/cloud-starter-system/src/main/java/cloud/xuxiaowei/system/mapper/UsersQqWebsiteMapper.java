package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersQqWebsite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * QQ网站应用表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-13
 */
public interface UsersQqWebsiteMapper extends BaseMapper<UsersQqWebsite> {

	/**
	 * 根据 QQ开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid QQ开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	UsersQqWebsite getByAppidAndOpenid(@Param("appid") String appid, @Param("openid") String openid);

}
