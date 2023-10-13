package org.dromara.blog.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 合集文章关联对象 blog_collection_post
 *
 * @author Lion Li
 * @date 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_collection_post")
public class BlogCollectionPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 合集id
     */
    private Long collectionId;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 排序
     */
    private Long order;


}
