package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.DictDataPageBo;
import cloud.xuxiaowei.masterdata.bo.DictDataPrimaryKey;
import cloud.xuxiaowei.masterdata.bo.DictDataSaveBo;
import cloud.xuxiaowei.masterdata.bo.DictDataUpdateBo;
import cloud.xuxiaowei.masterdata.entity.DictData;
import cloud.xuxiaowei.masterdata.service.IDictDataService;
import cloud.xuxiaowei.masterdata.vo.DictDataVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "DictDataController", description = "字典数据表")
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
	@PostMapping("/{dictCode}")
	public Response<?> listByDictCode(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("dictCode") String dictCode) {
		List<DictDataVo> dictDataVoList = dictDataService.listByDictCode(dictCode);
		return Response.ok(dictDataVoList);
	}

	/**
	 * 根据 字典数据表联合主键 查询
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataPrimaryKey 字典数据表联合主键
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据 字典数据表联合主键 查询")
	@PreAuthorize("hasAuthority('dict_read')")
	@PostMapping("/getById")
	public Response<?> getById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataPrimaryKey dictDataPrimaryKey) {
		DictData dictData = dictDataService.getByDictDataPrimaryKey(dictDataPrimaryKey);
		if (dictData == null) {
			return Response.ok((DictData) null);
		}
		DictDataVo dictDataVo = new DictDataVo();
		BeanUtils.copyProperties(dictData, dictDataVo);
		return Response.ok(dictDataVo);
	}

	/**
	 * 保存 字典数据
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataSaveBo 保存字典数据表参数
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "保存 字典数据")
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('dict_add')")
	public Response<?> save(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataSaveBo dictDataSaveBo) {

		boolean save = dictDataService.saveByDictDataSaveBo(dictDataSaveBo);

		return Response.ok(save);
	}

	/**
	 * 更新 字典数据
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataUpdateBo 更新字典数据表参数
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "更新 字典数据")
	@PostMapping("/updateById")
	@PreAuthorize("hasAuthority('dict_edit')")
	public Response<?> updateById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataUpdateBo dictDataUpdateBo) {

		boolean update = dictDataService.updateByDictDataUpdateBo(dictDataUpdateBo);

		return Response.ok(update);
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
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataPageBo dictDataPageBo) {
		IPage<DictData> page = dictDataService.pageByDictDataPageBo(dictDataPageBo);
		return Response.ok(page);
	}

	/**
	 * 根据 字典数据表联合主键 删除
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataPrimaryKey 字典数据表联合主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 字典数据表联合主键 删除")
	@PreAuthorize("hasAuthority('dict_delete')")
	@PostMapping("/removeById")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictDataPrimaryKey dictDataPrimaryKey) {

		boolean removeById = dictDataService.removeByDictDataPrimaryKey(dictDataPrimaryKey);

		return Response.ok(removeById);
	}

	/**
	 * 根据 字典数据表联合主键 删除
	 * @param request 请求
	 * @param response 响应
	 * @param dictDataPrimaryKeys 字典数据表联合主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 字典代码 批量删除")
	@PreAuthorize("hasAuthority('dict_delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<DictDataPrimaryKey> dictDataPrimaryKeys) {

		AssertUtils.sizeNonNull(dictDataPrimaryKeys, 1, 50, "非法数据长度");

		boolean removeByIds = dictDataService.removeByDictDataPrimaryKeys(dictDataPrimaryKeys);

		return Response.ok(removeByIds);
	}

}
