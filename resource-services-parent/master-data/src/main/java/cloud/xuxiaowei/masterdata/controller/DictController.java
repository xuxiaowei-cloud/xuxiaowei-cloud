package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.DictPageBo;
import cloud.xuxiaowei.masterdata.bo.DictSaveBo;
import cloud.xuxiaowei.masterdata.bo.DictUpdateBo;
import cloud.xuxiaowei.masterdata.entity.Dict;
import cloud.xuxiaowei.masterdata.service.IDictService;
import cloud.xuxiaowei.masterdata.vo.DictVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 字典表 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/dict")
public class DictController {

	private IDictService dictService;

	@Autowired
	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	/**
	 * 查询字典代码列表
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "查询字典代码列表")
	@PreAuthorize("@ant.hasAuthority('dict:read')")
	@PostMapping("/list")
	public Response<?> list(HttpServletRequest request, HttpServletResponse response) {
		List<DictVo> dictVoList = dictService.listDictVo();
		return Response.ok(dictVoList);
	}

	/**
	 * 根据 字典代码 查询
	 * @param request 请求
	 * @param response 响应
	 * @param dictCode 字典代码
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据 字典代码 查询")
	@PreAuthorize("@ant.hasAuthority('dict:read')")
	@PostMapping("/getById/{dictCode}")
	public Response<?> getById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("dictCode") String dictCode) {
		Dict dict = dictService.getById(dictCode);
		if (dict == null) {
			return Response.ok((Dict) null);
		}
		DictVo dictVo = new DictVo();
		BeanUtils.copyProperties(dict, dictVo);
		return Response.ok(dictVo);
	}

	/**
	 * 保存 字典
	 * @param request 请求
	 * @param response 响应
	 * @param dictSaveBo 保存字典表参数
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "保存 字典")
	@PostMapping("/save")
	@PreAuthorize("@ant.hasAuthority('dict:add')")
	public Response<?> save(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictSaveBo dictSaveBo) {

		boolean save = dictService.saveByDictSaveBo(dictSaveBo);

		return Response.ok(save);
	}

	/**
	 * 更新 字典
	 * @param request 请求
	 * @param response 响应
	 * @param dictUpdateBo 更新字典表参数
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "更新 字典")
	@PostMapping("/updateById")
	@PreAuthorize("@ant.hasAuthority('dict:edit')")
	public Response<?> updateById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictUpdateBo dictUpdateBo) {

		boolean update = dictService.updateByDictUpdateBo(dictUpdateBo);

		return Response.ok(update);
	}

	/**
	 * 分页查询字典
	 * @param request 请求
	 * @param response 响应
	 * @param dictPageBo 字典分页参数
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "分页查询字典")
	@PreAuthorize("@ant.hasAuthority('dict:read')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody DictPageBo dictPageBo) {
		IPage<Dict> page = dictService.pageByDictPageBo(dictPageBo);
		return Response.ok(page);
	}

	/**
	 * 根据 字典代码 删除
	 * @param request 请求
	 * @param response 响应
	 * @param dictCode 字典代码
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 字典代码 删除")
	@PreAuthorize("@ant.hasAuthority('dict:delete')")
	@PostMapping("/removeById/{dictCode}")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("dictCode") String dictCode) {

		boolean removeById = dictService.removeById(dictCode);

		return Response.ok(removeById);
	}

	/**
	 * 根据 字典代码 批量删除
	 * @param request 请求
	 * @param response 响应
	 * @param dictCodes 字典代码
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 字典代码 批量删除")
	@PreAuthorize("@ant.hasAuthority('dict:delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<String> dictCodes) {

		AssertUtils.sizeNonNull(dictCodes, 1, 50, "非法数据长度");

		boolean removeByIds = dictService.removeByIds(dictCodes);

		return Response.ok(removeByIds);
	}

}
