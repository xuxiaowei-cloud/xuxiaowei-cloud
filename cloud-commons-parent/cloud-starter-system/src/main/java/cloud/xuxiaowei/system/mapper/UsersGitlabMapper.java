package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.UsersGitlab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * GitLab用户 Mapper 接口
 * </p>
 *
 * @author 徐晓伟
 * @since 2022-12-01
 */
public interface UsersGitlabMapper extends BaseMapper<UsersGitlab> {

	/**
	 * 根据 GitLab-网站用户ID、用户唯一标识 查询用户
	 * @param appid GitLab-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	UsersGitlab getByAppidAndOpenid(@Param("appid") String appid, @Param("id") long id);

}
