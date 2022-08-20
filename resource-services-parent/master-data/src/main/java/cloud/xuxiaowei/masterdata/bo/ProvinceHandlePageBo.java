package cloud.xuxiaowei.masterdata.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 省份分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ProvinceHandlePageBo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long current;

	private Long size;

	private String provinceCode;

	private String provinceName;

}
