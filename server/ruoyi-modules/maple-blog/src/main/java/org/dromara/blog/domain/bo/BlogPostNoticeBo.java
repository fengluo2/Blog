package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogPostNotice;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 文章通知业务对象 blog_post_notice
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogPostNotice.class, reverseConvertGenerate = false)
public class BlogPostNoticeBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long postId;

    /**
     * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
     */
    @NotBlank(message = "通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String noticeType;

    /**
     * 通知id（点赞id、收藏id、评论id、回复id）
     */
    @NotNull(message = "通知id（点赞id、收藏id、评论id、回复id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long noticeId;

    /**
     * 已读标志（0代表未读 1代表已读）
     */
    @NotBlank(message = "已读标志（0代表未读 1代表已读）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String readFlag;


}
