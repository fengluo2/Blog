package org.dromara.blog.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.blog.domain.BlogPrivateChat;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 私聊业务对象 blog_private_chat
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogPrivateChat.class, reverseConvertGenerate = false)
public class BlogPrivateChatBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 用户Aid
     */
    @NotNull(message = "用户Aid不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userAId;

    /**
     * 用户Bid
     */
    @NotNull(message = "用户Bid不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userBId;

    /**
     * 用户A未读消息数
     */
    @NotNull(message = "用户A未读消息数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userAUnreadCount;

    /**
     * 用户B未读消息数
     */
    @NotNull(message = "用户B未读消息数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userBUnreadCount;


}
