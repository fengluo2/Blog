package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogPost;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 文章业务对象 blog_post
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogPost.class, reverseConvertGenerate = false)
public class BlogPostBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 简介
     */
    @NotBlank(message = "简介不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contentShort;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 作者署名
     */
    @NotBlank(message = "作者署名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String authorSignature;

    /**
     * 权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）
     */
    @NotBlank(message = "权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String levelFlag;


}
