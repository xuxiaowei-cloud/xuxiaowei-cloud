package cloud.xuxiaowei.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

/**
 * 超级服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface ISuperService<T> extends IService<T> {

	/**
	 * 测试时间
	 * @return
	 */
	LocalDateTime testTime();

}
