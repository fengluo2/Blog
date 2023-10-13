package org.dromara.blog.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 系统通知查看对象 blog_system_notification_read
 *
 * @author Lion Li
 * @date 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_system_notification_read")
public class BlogSystemNotificationRead extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 系统通知id
     */
    private Long systemNotificationId;

    /**
     * 用户id
     */
    private Long userId;


}
