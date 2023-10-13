package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogUserRelationshipBo;
import org.dromara.blog.domain.vo.BlogUserRelationshipVo;
import org.dromara.blog.service.IBlogUserRelationshipService;
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
 * 用户关系
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/userRelationship")
public class BlogUserRelationshipController extends BaseController {

    private final IBlogUserRelationshipService blogUserRelationshipService;

    /**
     * 查询用户关系列表
     */
    @SaCheckPermission("blog:userRelationship:list")
    @GetMapping("/list")
    public TableDataInfo<BlogUserRelationshipVo> list(BlogUserRelationshipBo bo, PageQuery pageQuery) {
        return blogUserRelationshipService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户关系列表
     */
    @SaCheckPermission("blog:userRelationship:export")
    @Log(title = "用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogUserRelationshipBo bo, HttpServletResponse response) {
        List<BlogUserRelationshipVo> list = blogUserRelationshipService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户关系", BlogUserRelationshipVo.class, response);
    }

    /**
     * 获取用户关系详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:userRelationship:query")
    @GetMapping("/{id}")
    public R<BlogUserRelationshipVo> getInfo(@NotNull(message = "主键不能为空")
                                             @PathVariable Long id) {
        return R.ok(blogUserRelationshipService.queryById(id));
    }

    /**
     * 新增用户关系
     */
    @SaCheckPermission("blog:userRelationship:add")
    @Log(title = "用户关系", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogUserRelationshipBo bo) {
        return toAjax(blogUserRelationshipService.insertByBo(bo));
    }

    /**
     * 修改用户关系
     */
    @SaCheckPermission("blog:userRelationship:edit")
    @Log(title = "用户关系", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogUserRelationshipBo bo) {
        return toAjax(blogUserRelationshipService.updateByBo(bo));
    }

    /**
     * 删除用户关系
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:userRelationship:remove")
    @Log(title = "用户关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogUserRelationshipService.deleteWithValidByIds(List.of(ids), true));
    }
}
