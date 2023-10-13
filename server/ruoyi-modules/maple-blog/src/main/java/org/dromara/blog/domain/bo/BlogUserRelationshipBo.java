package org.dromara.blog.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.blog.domain.BlogUserRelationship;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 用户关系业务对象 blog_user_relationship
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogUserRelationship.class, reverseConvertGenerate = false)
public class BlogUserRelationshipBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * 关联用户
     */
    @NotNull(message = "关联用户不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long associatedUserId;

    /**
     * 关系类型（1代表关注 2代表订阅 3代表拉黑）
     */
    @NotBlank(message = "关系类型（1代表关注 2代表订阅 3代表拉黑）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String relationshipType;


}
