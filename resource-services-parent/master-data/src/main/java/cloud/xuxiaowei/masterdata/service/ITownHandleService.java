package cloud.xuxiaowei.masterdata.service;

import cloud.xuxiaowei.masterdata.bo.TownHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.TownHandle;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 镇 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
public interface ITownHandleService extends IService<TownHandle> {

	/**
	 * 分页查询镇
	 * @param townHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	IPage<TownHandle> pageByTownHandlePageBo(TownHandlePageBo townHandlePageBo);

}
