package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.AlipayOplatformWebsiteUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 支付宝网站用户 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-03
 */
public interface AlipayOplatformWebsiteUsersMapper extends BaseMapper<AlipayOplatformWebsiteUsers> {

	AlipayOplatformWebsiteUsers getByAppidAndUserId(@Param("appid") String appid, @Param("userId") String userId);

}
