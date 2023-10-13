package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogSystemNotificationReadBo;
import org.dromara.blog.domain.vo.BlogSystemNotificationReadVo;
import org.dromara.blog.service.IBlogSystemNotificationReadService;
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
 * 系统通知查看
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/systemNotificationRead")
public class BlogSystemNotificationReadController extends BaseController {

    private final IBlogSystemNotificationReadService blogSystemNotificationReadService;

    /**
     * 查询系统通知查看列表
     */
    @SaCheckPermission("blog:systemNotificationRead:list")
    @GetMapping("/list")
    public TableDataInfo<BlogSystemNotificationReadVo> list(BlogSystemNotificationReadBo bo, PageQuery pageQuery) {
        return blogSystemNotificationReadService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统通知查看列表
     */
    @SaCheckPermission("blog:systemNotificationRead:export")
    @Log(title = "系统通知查看", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogSystemNotificationReadBo bo, HttpServletResponse response) {
        List<BlogSystemNotificationReadVo> list = blogSystemNotificationReadService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统通知查看", BlogSystemNotificationReadVo.class, response);
    }

    /**
     * 获取系统通知查看详细信息
     *
     * @param systemNotificationId 主键
     */
    @SaCheckPermission("blog:systemNotificationRead:query")
    @GetMapping("/{systemNotificationId}")
    public R<BlogSystemNotificationReadVo> getInfo(@NotNull(message = "主键不能为空")
                                                   @PathVariable Long systemNotificationId) {
        return R.ok(blogSystemNotificationReadService.queryById(systemNotificationId));
    }

    /**
     * 新增系统通知查看
     */
    @SaCheckPermission("blog:systemNotificationRead:add")
    @Log(title = "系统通知查看", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogSystemNotificationReadBo bo) {
        return toAjax(blogSystemNotificationReadService.insertByBo(bo));
    }

    /**
     * 修改系统通知查看
     */
    @SaCheckPermission("blog:systemNotificationRead:edit")
    @Log(title = "系统通知查看", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogSystemNotificationReadBo bo) {
        return toAjax(blogSystemNotificationReadService.updateByBo(bo));
    }

    /**
     * 删除系统通知查看
     *
     * @param systemNotificationIds 主键串
     */
    @SaCheckPermission("blog:systemNotificationRead:remove")
    @Log(title = "系统通知查看", businessType = BusinessType.DELETE)
    @DeleteMapping("/{systemNotificationIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] systemNotificationIds) {
        return toAjax(blogSystemNotificationReadService.deleteWithValidByIds(List.of(systemNotificationIds), true));
    }
}
