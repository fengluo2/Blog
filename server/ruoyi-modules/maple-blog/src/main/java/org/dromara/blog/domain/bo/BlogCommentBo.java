package org.dromara.blog.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.blog.domain.BlogComment;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 评论业务对象 blog_comment
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogComment.class, reverseConvertGenerate = false)
public class BlogCommentBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 回复id（0代表1级评论）
     */
    @NotNull(message = "回复id（0代表1级评论）不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long replyId;

    /**
     * 评论级别
     */
    @NotNull(message = "评论级别不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long level;

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long postId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String content;

    /**
     * 主评论标志（0代表不是 1代表是）
     */
    @NotBlank(message = "主评论标志（0代表不是 1代表是）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String masterFlag;


}
