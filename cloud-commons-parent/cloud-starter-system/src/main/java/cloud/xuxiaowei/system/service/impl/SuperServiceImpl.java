package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.service.ISuperService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

/**
 * 超级服务实现
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class SuperServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ISuperService<T> {

	/**
	 * 测试时间
	 * @return
	 */
	@Override
	public LocalDateTime testTime() {
		return LocalDateTime.now();
	}

}
