package org.dromara.blog.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 文章标签关联对象 blog_post_tag
 *
 * @author Lion Li
 * @date 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_post_tag")
public class BlogPostTag extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标签id
     */
    private Long tagId;


}
