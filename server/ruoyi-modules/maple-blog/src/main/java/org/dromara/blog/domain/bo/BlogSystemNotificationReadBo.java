package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogSystemNotificationRead;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 系统通知查看业务对象 blog_system_notification_read
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogSystemNotificationRead.class, reverseConvertGenerate = false)
public class BlogSystemNotificationReadBo extends BaseEntity {

    /**
     * 系统通知id
     */
    @NotNull(message = "系统通知id不能为空", groups = { EditGroup.class })
    private Long systemNotificationId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { EditGroup.class })
    private Long userId;


}
