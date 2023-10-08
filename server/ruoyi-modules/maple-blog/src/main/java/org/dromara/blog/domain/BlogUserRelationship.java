package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户关系对象 blog_user_relationship
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_user_relationship")
public class BlogUserRelationship extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关联用户
     */
    private Long associatedUserId;

    /**
     * 关系类型（1代表关注 2代表订阅 3代表拉黑）
     */
    private String relationshipType;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;


}
