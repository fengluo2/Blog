package org.dromara.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.BlogCollectionBo;
import org.dromara.blog.domain.vo.BlogCollectionVo;
import org.dromara.blog.service.IBlogCollectionService;
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
 * 合集
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/collection")
public class BlogCollectionController extends BaseController {

    private final IBlogCollectionService blogCollectionService;

    /**
     * 查询合集列表
     */
    @SaCheckPermission("blog:collection:list")
    @GetMapping("/list")
    public TableDataInfo<BlogCollectionVo> list(BlogCollectionBo bo, PageQuery pageQuery) {
        return blogCollectionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出合集列表
     */
    @SaCheckPermission("blog:collection:export")
    @Log(title = "合集", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogCollectionBo bo, HttpServletResponse response) {
        List<BlogCollectionVo> list = blogCollectionService.queryList(bo);
        ExcelUtil.exportExcel(list, "合集", BlogCollectionVo.class, response);
    }

    /**
     * 获取合集详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:collection:query")
    @GetMapping("/{id}")
    public R<BlogCollectionVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(blogCollectionService.queryById(id));
    }

    /**
     * 新增合集
     */
    @SaCheckPermission("blog:collection:add")
    @Log(title = "合集", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogCollectionBo bo) {
        return toAjax(blogCollectionService.insertByBo(bo));
    }

    /**
     * 修改合集
     */
    @SaCheckPermission("blog:collection:edit")
    @Log(title = "合集", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogCollectionBo bo) {
        return toAjax(blogCollectionService.updateByBo(bo));
    }

    /**
     * 删除合集
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:collection:remove")
    @Log(title = "合集", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(blogCollectionService.deleteWithValidByIds(List.of(ids), true));
    }
}
