package cloud.xuxiaowei.masterdata.service.impl;

import cloud.xuxiaowei.masterdata.entity.Dict;
import cloud.xuxiaowei.masterdata.mapper.DictMapper;
import cloud.xuxiaowei.masterdata.service.IDictService;
import cloud.xuxiaowei.masterdata.vo.DictVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	/**
	 * 查询字典代码列表
	 * @return 返回查询结果
	 */
	@Override
	public List<DictVo> listDictVo() {
		List<Dict> dictList = list();
		List<DictVo> dictVoList = new ArrayList<>();
		for (Dict dict : dictList) {
			DictVo dictVo = new DictVo();
			BeanUtils.copyProperties(dict, dictVo);
			dictVoList.add(dictVo);
		}
		return dictVoList;
	}

}
