package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 私聊对象 blog_private_chat
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_private_chat")
public class BlogPrivateChat extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户Aid
     */
    private Long userAId;

    /**
     * 用户Bid
     */
    private Long userBId;

    /**
     * 用户A未读消息数
     */
    private Long userAUnreadCount;

    /**
     * 用户B未读消息数
     */
    private Long userBUnreadCount;


}
