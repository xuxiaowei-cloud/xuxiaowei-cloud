package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersGithub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Github用户表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-30
 */
public interface UsersGithubMapper extends BaseMapper<UsersGithub> {

	/**
	 * 根据 GitHub唯一标识 查询 GitHub用户表
	 * @param appid 此处是 {@link String} 类型的 clientId，并不是 GitHub 中的 {@link Integer} 类型的appid
	 * @param id GitHub唯一标识
	 * @return 返回 GitHub用户表
	 */
	UsersGithub getByAppidAndId(@Param("appid") String appid, @Param("id") int id);

}
