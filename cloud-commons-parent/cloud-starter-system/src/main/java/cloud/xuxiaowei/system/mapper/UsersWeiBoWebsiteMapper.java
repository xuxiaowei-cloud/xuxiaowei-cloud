package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersWeiBoWebsite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 微博网站用户 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-22
 */
public interface UsersWeiBoWebsiteMapper extends BaseMapper<UsersWeiBoWebsite> {

	/**
	 * 根据 微博开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微博开放平台-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	UsersWeiBoWebsite getByAppidAndId(@Param("appid") String appid, @Param("id") String id);

}
