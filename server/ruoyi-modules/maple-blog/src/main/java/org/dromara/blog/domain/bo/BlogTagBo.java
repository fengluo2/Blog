package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogTag;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 标签业务对象 blog_tag
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogTag.class, reverseConvertGenerate = false)
public class BlogTagBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;


}
