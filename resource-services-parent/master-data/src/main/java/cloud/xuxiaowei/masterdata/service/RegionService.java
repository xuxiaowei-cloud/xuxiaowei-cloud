package cloud.xuxiaowei.masterdata.service;

import cloud.xuxiaowei.masterdata.bo.RegionPageBo;
import cloud.xuxiaowei.masterdata.vo.RegionPageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 区域 服务类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface RegionService {

	/**
	 * 分页查询区域
	 * @param regionPageBo 分页参数
	 * @return 返回 查询结果
	 */
	IPage<RegionPageVo> page(RegionPageBo regionPageBo);

}
