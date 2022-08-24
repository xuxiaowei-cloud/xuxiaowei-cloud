package cloud.xuxiaowei.masterdata.service;

import cloud.xuxiaowei.masterdata.bo.DictPageBo;
import cloud.xuxiaowei.masterdata.bo.DictSaveBo;
import cloud.xuxiaowei.masterdata.bo.DictUpdateBo;
import cloud.xuxiaowei.masterdata.entity.Dict;
import cloud.xuxiaowei.masterdata.vo.DictVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
public interface IDictService extends IService<Dict> {

	/**
	 * 查询字典代码列表
	 * @return 返回查询结果
	 */
	List<DictVo> listDictVo();

	/**
	 * 分页查询字典
	 * @param dictPageBo 字典分页参数
	 * @return 返回查询结果
	 */
	IPage<Dict> pageByDictPageBo(DictPageBo dictPageBo);

	/**
	 * 保存 字典
	 * @param dictSaveBo 保存字典表参数
	 * @return 返回 保存结果
	 */
	boolean saveByDictSaveBo(DictSaveBo dictSaveBo);

	/**
	 * 更新 字典
	 * @param dictUpdateBo 更新字典表参数
	 * @return 返回 更新结果
	 */
	boolean updateByDictUpdateBo(DictUpdateBo dictUpdateBo);

}
