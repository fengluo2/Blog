package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文章标签关联对象 blog_post_tag
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_post_tag")
public class BlogPostTag extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标签id
     */
    private Long tagId;


}
