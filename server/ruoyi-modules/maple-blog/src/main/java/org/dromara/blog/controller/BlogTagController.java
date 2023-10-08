package org.dromara.blog.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.blog.domain.bo.BlogTagBo;
import org.dromara.blog.service.IBlogTagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.blog.domain.vo.BlogTagVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 标签
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController extends BaseController {

    private final IBlogTagService blogTagService;

    /**
     * 查询标签列表
     */
    @SaCheckPermission("blog:tag:list")
    @GetMapping("/list")
    public TableDataInfo<BlogTagVo> list(BlogTagBo bo, PageQuery pageQuery) {
        return blogTagService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出标签列表
     */
    @SaCheckPermission("blog:tag:export")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogTagBo bo, HttpServletResponse response) {
        List<BlogTagVo> list = blogTagService.queryList(bo);
        ExcelUtil.exportExcel(list, "标签", BlogTagVo.class, response);
    }

    /**
     * 获取标签详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:tag:query")
    @GetMapping("/{id}")
    public R<BlogTagVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(blogTagService.queryById(id));
    }

    /**
     * 新增标签
     */
    @SaCheckPermission("blog:tag:add")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogTagBo bo) {
        return toAjax(blogTagService.insertByBo(bo));
    }

    /**
     * 修改标签
     */
    @SaCheckPermission("blog:tag:edit")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogTagBo bo) {
        return toAjax(blogTagService.updateByBo(bo));
    }

    /**
     * 删除标签
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:tag:remove")
    @Log(title = "标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogTagService.deleteWithValidByIds(List.of(ids), true));
    }
}
