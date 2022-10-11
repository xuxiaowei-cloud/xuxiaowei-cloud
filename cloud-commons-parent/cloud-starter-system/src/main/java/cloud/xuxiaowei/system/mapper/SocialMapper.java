package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.vo.SocialVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface SocialMapper {

    /**
     * 根据用户主键获取社交绑定
     * @param usersId 用户主键
     * @return 返回 社交绑定
     */
    List<SocialVo> listByUsersId(@Param("usersId") Long usersId);

}
