package org.dromara.blog.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.blog.domain.BlogPrivateChatMessage;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 私聊消息业务对象 blog_private_chat_message
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogPrivateChatMessage.class, reverseConvertGenerate = false)
public class BlogPrivateChatMessageBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 私聊id
     */
    @NotNull(message = "私聊id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long chatId;

    /**
     * 发送者id
     */
    @NotNull(message = "发送者id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long senderId;

    /**
     * 接收者id
     */
    @NotNull(message = "接收者id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long receiverId;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String content;

    /**
     * 已读标志（0代表未读 1代表已读）
     */
    @NotBlank(message = "已读标志（0代表未读 1代表已读）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String readFlag;


}
