package cloud.xuxiaowei.masterdata.service.impl;

import cloud.xuxiaowei.masterdata.bo.DictDataPageBo;
import cloud.xuxiaowei.masterdata.entity.DictData;
import cloud.xuxiaowei.masterdata.mapper.DictDataMapper;
import cloud.xuxiaowei.masterdata.service.IDictDataService;
import cloud.xuxiaowei.masterdata.vo.DictDataVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 字典数据表
 * <p>
 * 为何本表不使用自增主键？
 * <p>
 * 答：考虑到开发环境添加数据、正式环境添加数据、开发环境向正式环境同步字典数据等情况，相同的字典代码与字典数据代码可能对应不同的自增主键，故放弃自增主键，改用字典代码与字典数据代码作为联合主键。
 * <p>
 * 本表external_code与external_label开头的字段有何作用？
 * <p>
 * 答：对接外部系统时，如果外部系统与本系统针对某一字典值、名称不同时，在此增加两列做对照，并在字段中在增加对外部系统的描述，使用时，直接取不同系统对照的不同字段即可 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

	/**
	 * 根据字典代码查询字典列表
	 * @param dictCode 字典代码
	 * @return 返回 查询结果
	 */
	@Override
	public List<DictDataVo> listByDictCode(String dictCode) {
		return baseMapper.listByDictCode(dictCode);
	}

	/**
	 * 分页查询字典数据
	 * @param dictDataPageBo 字典数据分页参数
	 * @return 返回 查询结果
	 */
	@Override
	public IPage<DictData> pageByDictDataPageBo(DictDataPageBo dictDataPageBo) {
		QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
		Long current = dictDataPageBo.getCurrent();
		Long size = dictDataPageBo.getSize();

		String dictCode = dictDataPageBo.getDictCode();
		String dictDataCode = dictDataPageBo.getDictDataCode();
		String dictDataLabel = dictDataPageBo.getDictDataLabel();
		Integer dictDataSort = dictDataPageBo.getDictDataSort();
		String dictDataExplain = dictDataPageBo.getDictDataExplain();
		String externalCodeOne = dictDataPageBo.getExternalCodeOne();
		String externalLabelOne = dictDataPageBo.getExternalLabelOne();
		String remark = dictDataPageBo.getRemark();

		if (StringUtils.hasText(dictCode)) {
			queryWrapper.eq("dict_code", dictCode);
		}
		if (StringUtils.hasText(dictDataCode)) {
			queryWrapper.eq("dict_data_code", dictDataCode);
		}
		if (dictDataLabel != null) {
			queryWrapper.eq("dict_data_label", dictDataLabel);
		}
		if (dictDataSort != null) {
			queryWrapper.eq("dict_data_sort", dictDataSort);
		}
		if (StringUtils.hasText(dictDataExplain)) {
			queryWrapper.eq("dict_data_explain", dictDataExplain);
		}
		if (StringUtils.hasText(externalCodeOne)) {
			queryWrapper.eq("external_code_one", externalCodeOne);
		}
		if (StringUtils.hasText(externalLabelOne)) {
			queryWrapper.eq("external_label_one", externalLabelOne);
		}
		if (StringUtils.hasText(remark)) {
			queryWrapper.eq("remark", remark);
		}

		IPage<DictData> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);

		return page(page, queryWrapper);
	}

}
