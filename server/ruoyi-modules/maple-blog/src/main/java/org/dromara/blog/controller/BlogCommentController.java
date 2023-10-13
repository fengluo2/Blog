package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogCommentBo;
import org.dromara.blog.domain.vo.BlogCommentVo;
import org.dromara.blog.service.IBlogCommentService;
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
 * 评论
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/comment")
public class BlogCommentController extends BaseController {

    private final IBlogCommentService blogCommentService;

    /**
     * 查询评论列表
     */
    @SaCheckPermission("blog:comment:list")
    @GetMapping("/list")
    public TableDataInfo<BlogCommentVo> list(BlogCommentBo bo, PageQuery pageQuery) {
        return blogCommentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出评论列表
     */
    @SaCheckPermission("blog:comment:export")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogCommentBo bo, HttpServletResponse response) {
        List<BlogCommentVo> list = blogCommentService.queryList(bo);
        ExcelUtil.exportExcel(list, "评论", BlogCommentVo.class, response);
    }

    /**
     * 获取评论详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:comment:query")
    @GetMapping("/{id}")
    public R<BlogCommentVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(blogCommentService.queryById(id));
    }

    /**
     * 新增评论
     */
    @SaCheckPermission("blog:comment:add")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogCommentBo bo) {
        return toAjax(blogCommentService.insertByBo(bo));
    }

    /**
     * 修改评论
     */
    @SaCheckPermission("blog:comment:edit")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogCommentBo bo) {
        return toAjax(blogCommentService.updateByBo(bo));
    }

    /**
     * 删除评论
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:comment:remove")
    @Log(title = "评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogCommentService.deleteWithValidByIds(List.of(ids), true));
    }
}
