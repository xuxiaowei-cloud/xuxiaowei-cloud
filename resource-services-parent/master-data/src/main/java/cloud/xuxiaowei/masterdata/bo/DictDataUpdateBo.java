package cloud.xuxiaowei.masterdata.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 字典数据表更新参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataUpdateBo extends DictDataBo {

	/**
	 * 字典代码，联合主键，取表：dict.dict_code
	 */
	@NotNull(message = "字典代码 不能为空")
	private String dictCode;

	/**
	 * 字典数据代码，联合主键
	 */
	@NotNull(message = "字典数据代码 不能为空")
	private String dictDataCode;

}
