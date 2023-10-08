package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogCollection;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 合集业务对象 blog_collection
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogCollection.class, reverseConvertGenerate = false)
public class BlogCollectionBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合集名称
     */
    @NotBlank(message = "合集名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 合集描述
     */
    @NotBlank(message = "合集描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String describe;

    /**
     * 订阅标志（0为免费 1为订阅）
     */
    @NotBlank(message = "订阅标志（0为免费 1为订阅）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payFlag;

    /**
     * 从第节开始付费
     */
    @NotNull(message = "从第节开始付费不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long payIndex;


}
