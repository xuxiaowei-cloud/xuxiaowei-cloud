package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersFeishuWebpage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 飞书用户表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-02
 */
public interface UsersFeishuWebpageMapper extends BaseMapper<UsersFeishuWebpage> {

	UsersFeishuWebpage getByAppidAndOpenId(@Param("appid") String appid, @Param("openId") String openId);

}
