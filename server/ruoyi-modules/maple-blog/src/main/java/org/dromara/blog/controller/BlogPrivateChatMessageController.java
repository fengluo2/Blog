package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogPrivateChatMessageBo;
import org.dromara.blog.domain.vo.BlogPrivateChatMessageVo;
import org.dromara.blog.service.IBlogPrivateChatMessageService;
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
 * 私聊消息
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/privateChatMessage")
public class BlogPrivateChatMessageController extends BaseController {

    private final IBlogPrivateChatMessageService blogPrivateChatMessageService;

    /**
     * 查询私聊消息列表
     */
    @SaCheckPermission("blog:privateChatMessage:list")
    @GetMapping("/list")
    public TableDataInfo<BlogPrivateChatMessageVo> list(BlogPrivateChatMessageBo bo, PageQuery pageQuery) {
        return blogPrivateChatMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出私聊消息列表
     */
    @SaCheckPermission("blog:privateChatMessage:export")
    @Log(title = "私聊消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogPrivateChatMessageBo bo, HttpServletResponse response) {
        List<BlogPrivateChatMessageVo> list = blogPrivateChatMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "私聊消息", BlogPrivateChatMessageVo.class, response);
    }

    /**
     * 获取私聊消息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:privateChatMessage:query")
    @GetMapping("/{id}")
    public R<BlogPrivateChatMessageVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(blogPrivateChatMessageService.queryById(id));
    }

    /**
     * 新增私聊消息
     */
    @SaCheckPermission("blog:privateChatMessage:add")
    @Log(title = "私聊消息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogPrivateChatMessageBo bo) {
        return toAjax(blogPrivateChatMessageService.insertByBo(bo));
    }

    /**
     * 修改私聊消息
     */
    @SaCheckPermission("blog:privateChatMessage:edit")
    @Log(title = "私聊消息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogPrivateChatMessageBo bo) {
        return toAjax(blogPrivateChatMessageService.updateByBo(bo));
    }

    /**
     * 删除私聊消息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:privateChatMessage:remove")
    @Log(title = "私聊消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogPrivateChatMessageService.deleteWithValidByIds(List.of(ids), true));
    }
}
