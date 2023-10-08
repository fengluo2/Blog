package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 合集文章关联对象 blog_collection_post
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_collection_post")
public class BlogCollectionPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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
