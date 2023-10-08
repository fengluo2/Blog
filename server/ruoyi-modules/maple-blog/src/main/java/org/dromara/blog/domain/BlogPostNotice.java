package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文章通知对象 blog_post_notice
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_post_notice")
public class BlogPostNotice extends BaseEntity {

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
     * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
     */
    private String noticeType;

    /**
     * 通知id（点赞id、收藏id、评论id、回复id）
     */
    private Long noticeId;

    /**
     * 已读标志（0代表未读 1代表已读）
     */
    private String readFlag;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;


}
