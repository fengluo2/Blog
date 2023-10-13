package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogSystemNotificationBo;
import org.dromara.blog.domain.vo.BlogSystemNotificationVo;
import org.dromara.blog.service.IBlogSystemNotificationService;
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
 * 系统通知
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/systemNotification")
public class BlogSystemNotificationController extends BaseController {

    private final IBlogSystemNotificationService blogSystemNotificationService;

    /**
     * 查询系统通知列表
     */
    @SaCheckPermission("blog:systemNotification:list")
    @GetMapping("/list")
    public TableDataInfo<BlogSystemNotificationVo> list(BlogSystemNotificationBo bo, PageQuery pageQuery) {
        return blogSystemNotificationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统通知列表
     */
    @SaCheckPermission("blog:systemNotification:export")
    @Log(title = "系统通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogSystemNotificationBo bo, HttpServletResponse response) {
        List<BlogSystemNotificationVo> list = blogSystemNotificationService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统通知", BlogSystemNotificationVo.class, response);
    }

    /**
     * 获取系统通知详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:systemNotification:query")
    @GetMapping("/{id}")
    public R<BlogSystemNotificationVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(blogSystemNotificationService.queryById(id));
    }

    /**
     * 新增系统通知
     */
    @SaCheckPermission("blog:systemNotification:add")
    @Log(title = "系统通知", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogSystemNotificationBo bo) {
        return toAjax(blogSystemNotificationService.insertByBo(bo));
    }

    /**
     * 修改系统通知
     */
    @SaCheckPermission("blog:systemNotification:edit")
    @Log(title = "系统通知", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogSystemNotificationBo bo) {
        return toAjax(blogSystemNotificationService.updateByBo(bo));
    }

    /**
     * 删除系统通知
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:systemNotification:remove")
    @Log(title = "系统通知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogSystemNotificationService.deleteWithValidByIds(List.of(ids), true));
    }
}
