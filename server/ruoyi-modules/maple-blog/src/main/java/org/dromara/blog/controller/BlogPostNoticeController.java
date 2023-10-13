package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogPostNoticeBo;
import org.dromara.blog.domain.vo.BlogPostNoticeVo;
import org.dromara.blog.service.IBlogPostNoticeService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章通知
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/postNotice")
public class BlogPostNoticeController extends BaseController {

    private final IBlogPostNoticeService blogPostNoticeService;

    /**
     * 查询文章通知列表
     */
    @SaCheckPermission("blog:postNotice:list")
    @GetMapping("/list")
    public TableDataInfo<BlogPostNoticeVo> list(BlogPostNoticeBo bo, PageQuery pageQuery) {
        return blogPostNoticeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章通知列表
     */
    @SaCheckPermission("blog:postNotice:export")
    @Log(title = "文章通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogPostNoticeBo bo, HttpServletResponse response) {
        List<BlogPostNoticeVo> list = blogPostNoticeService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章通知", BlogPostNoticeVo.class, response);
    }

    /**
     * 获取文章通知详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:postNotice:query")
    @GetMapping("/{id}")
    public R<BlogPostNoticeVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(blogPostNoticeService.queryById(id));
    }

    /**
     * 新增文章通知
     */
    @SaCheckPermission("blog:postNotice:add")
    @Log(title = "文章通知", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogPostNoticeBo bo) {
        return toAjax(blogPostNoticeService.insertByBo(bo));
    }

    /**
     * 修改文章通知
     */
    @SaCheckPermission("blog:postNotice:edit")
    @Log(title = "文章通知", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogPostNoticeBo bo) {
        return toAjax(blogPostNoticeService.updateByBo(bo));
    }

    /**
     * 删除文章通知
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:postNotice:remove")
    @Log(title = "文章通知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogPostNoticeService.deleteWithValidByIds(List.of(ids), true));
    }
}
