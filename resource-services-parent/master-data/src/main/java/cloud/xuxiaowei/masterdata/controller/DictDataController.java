package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.DictDataPageBo;
import cloud.xuxiaowei.masterdata.entity.DictData;
import cloud.xuxiaowei.masterdata.service.IDictDataService;
import cloud.xuxiaowei.masterdata.vo.DictDataVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
 * 答：对接外部系统时，如果外部系统与本系统针对某一字典值、名称不同时，在此增加两列做对照，并在字段中在增加对外部系统的描述，使用时，直接取不同系统对照的不同字段即可 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/dict-data")
public class DictDataController {

	private IDictDataService dictDataService;

	@Autowired
	public void setDictDataService(IDictDataService dictDataService) {
		this.dictDataService = dictDataService;
	}

	/**
	 * 根据字典代码查询字典列表
	 * @param request 请求
	 * @param response 响应
	 * @param dictCode 字典代码
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据字典代码查询字典列表")
	@PreAuthorize("hasAnyAuthority('dict_read', 'user_info')")
	@RequestMapping("/{dictCode}")
	public Response<?> listByDictCode(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("dictCode") String dictCode) {
		List<DictDataVo> dictDataVoList = dictDataService.listByDictCode(dictCode);
		return Response.ok(dictDataVoList);
	}

	/**
	 * 分页查询字典数据
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataPageBo 字典分页参数
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "分页查询字典数据")
	@PreAuthorize("hasAuthority('dict_read')")
	@RequestMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataPageBo dictDataPageBo) {
		IPage<DictData> page = dictDataService.pageByDictDataPageBo(dictDataPageBo);
		return Response.ok(page);
	}

}
