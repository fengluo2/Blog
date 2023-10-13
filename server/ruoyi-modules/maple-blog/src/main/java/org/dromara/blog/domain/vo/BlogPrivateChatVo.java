package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogPrivateChat;

import java.io.Serial;
import java.io.Serializable;


/**
 * 私聊视图对象 blog_private_chat
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogPrivateChat.class)
public class BlogPrivateChatVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 用户Aid
     */
    @ExcelProperty(value = "用户Aid")
    private Long userAId;

    /**
     * 用户Bid
     */
    @ExcelProperty(value = "用户Bid")
    private Long userBId;

    /**
     * 用户A未读消息数
     */
    @ExcelProperty(value = "用户A未读消息数")
    private Long userAUnreadCount;

    /**
     * 用户B未读消息数
     */
    @ExcelProperty(value = "用户B未读消息数")
    private Long userBUnreadCount;


}
