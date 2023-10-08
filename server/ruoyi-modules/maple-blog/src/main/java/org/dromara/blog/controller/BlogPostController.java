package org.dromara.blog.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
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
import org.dromara.blog.domain.vo.BlogPostVo;
import org.dromara.blog.domain.bo.BlogPostBo;
import org.dromara.blog.service.IBlogPostService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文章
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/post")
public class BlogPostController extends BaseController {

    private final IBlogPostService blogPostService;

    /**
     * 查询文章列表
     */
    @SaCheckPermission("blog:post:list")
    @GetMapping("/list")
    public TableDataInfo<BlogPostVo> list(BlogPostBo bo, PageQuery pageQuery) {
        return blogPostService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章列表
     */
    @SaCheckPermission("blog:post:export")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogPostBo bo, HttpServletResponse response) {
        List<BlogPostVo> list = blogPostService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章", BlogPostVo.class, response);
    }

    /**
     * 获取文章详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:post:query")
    @GetMapping("/{id}")
    public R<BlogPostVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(blogPostService.queryById(id));
    }

    /**
     * 新增文章
     */
    @SaCheckPermission("blog:post:add")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogPostBo bo) {
        return toAjax(blogPostService.insertByBo(bo));
    }

    /**
     * 修改文章
     */
    @SaCheckPermission("blog:post:edit")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogPostBo bo) {
        return toAjax(blogPostService.updateByBo(bo));
    }

    /**
     * 删除文章
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:post:remove")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogPostService.deleteWithValidByIds(List.of(ids), true));
    }
}
