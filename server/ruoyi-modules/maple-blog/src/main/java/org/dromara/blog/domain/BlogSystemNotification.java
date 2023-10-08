package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 系统通知对象 blog_system_notification
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_system_notification")
public class BlogSystemNotification extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 可点击标志（0代表未读 1代表可点击）
     */
    private String click;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;


}
