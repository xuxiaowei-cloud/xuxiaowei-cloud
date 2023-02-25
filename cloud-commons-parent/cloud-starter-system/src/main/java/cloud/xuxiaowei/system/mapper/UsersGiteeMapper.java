package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersGitee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 码云Gitee用户表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-10
 */
public interface UsersGiteeMapper extends BaseMapper<UsersGitee> {

	/**
	 * 根据 码云Gitee唯一标识 查询 码云Gitee用户表
	 * @param appid AppID(码云Gitee client_id)
	 * @param id 码云Gitee唯一标识，不为空
	 * @return 返回 码云Gitee用户表
	 */
	UsersGitee getByAppidAndId(@Param("appid") String appid, @Param("id") Integer id);

}
