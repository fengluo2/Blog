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
import org.dromara.blog.domain.vo.BlogPrivateChatVo;
import org.dromara.blog.domain.bo.BlogPrivateChatBo;
import org.dromara.blog.service.IBlogPrivateChatService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 私聊
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/privateChat")
public class BlogPrivateChatController extends BaseController {

    private final IBlogPrivateChatService blogPrivateChatService;

    /**
     * 查询私聊列表
     */
    @SaCheckPermission("blog:privateChat:list")
    @GetMapping("/list")
    public TableDataInfo<BlogPrivateChatVo> list(BlogPrivateChatBo bo, PageQuery pageQuery) {
        return blogPrivateChatService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出私聊列表
     */
    @SaCheckPermission("blog:privateChat:export")
    @Log(title = "私聊", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogPrivateChatBo bo, HttpServletResponse response) {
        List<BlogPrivateChatVo> list = blogPrivateChatService.queryList(bo);
        ExcelUtil.exportExcel(list, "私聊", BlogPrivateChatVo.class, response);
    }

    /**
     * 获取私聊详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:privateChat:query")
    @GetMapping("/{id}")
    public R<BlogPrivateChatVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(blogPrivateChatService.queryById(id));
    }

    /**
     * 新增私聊
     */
    @SaCheckPermission("blog:privateChat:add")
    @Log(title = "私聊", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogPrivateChatBo bo) {
        return toAjax(blogPrivateChatService.insertByBo(bo));
    }

    /**
     * 修改私聊
     */
    @SaCheckPermission("blog:privateChat:edit")
    @Log(title = "私聊", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogPrivateChatBo bo) {
        return toAjax(blogPrivateChatService.updateByBo(bo));
    }

    /**
     * 删除私聊
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:privateChat:remove")
    @Log(title = "私聊", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogPrivateChatService.deleteWithValidByIds(List.of(ids), true));
    }
}
