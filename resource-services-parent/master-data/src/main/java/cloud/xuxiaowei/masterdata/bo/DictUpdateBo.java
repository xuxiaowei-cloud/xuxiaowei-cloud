package cloud.xuxiaowei.masterdata.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 更新字典表参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictUpdateBo extends DictBo {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典代码
	 */
	@NotEmpty(message = "字典代码 不能为空")
	private String dictCode;

}
