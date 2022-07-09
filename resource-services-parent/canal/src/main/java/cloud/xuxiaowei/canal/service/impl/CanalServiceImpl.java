package cloud.xuxiaowei.canal.service.impl;

import cloud.xuxiaowei.canal.mapper.CanalMapper;
import cloud.xuxiaowei.canal.service.CanalService;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * canal 服务 接口实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
@DS("log")
public class CanalServiceImpl implements CanalService {

	@Resource
	private CanalMapper canalMapper;

	@Override
	public int insert(String sql) {
		return canalMapper.insert(sql);
	}

	@Override
	public int update(String sql) {
		return canalMapper.update(sql);
	}

	@Override
	public int delete(String sql) {
		return canalMapper.delete(sql);
	}

}
