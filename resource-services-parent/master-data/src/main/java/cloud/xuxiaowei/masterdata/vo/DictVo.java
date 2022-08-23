package cloud.xuxiaowei.masterdata.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class DictVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典代码，主键
	 * <p>
	 * 为何本表不使用自增主键？
	 * <p>
	 * 答：考虑到开发环境添加数据、正式环境添加数据、开发环境向正式环境同步字典数据等情况，相同的字典代码可能对应不同的自增主键，故放弃自增主键，改用字典代码作为主键。
	 */
	private String dictCode;

	/**
	 * 字典说明，不为空
	 */
	private String dictExplain;

}
