package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersDingtalk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 钉钉用户表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-31
 */
public interface UsersDingtalkMapper extends BaseMapper<UsersDingtalk> {

	/**
	 * 根据 钉钉-网站用户ID、用户唯一标识 查询用户
	 * @param appid 钉钉-网站用户ID
	 * @param openId 用户唯一标识
	 * @return 返回 用户
	 */
	UsersDingtalk getByAppidAndOpenId(@Param("appid") String appid, @Param("openId") String openId);

}
