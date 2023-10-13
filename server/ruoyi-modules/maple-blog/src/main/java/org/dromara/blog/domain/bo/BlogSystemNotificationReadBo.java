package org.dromara.blog.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.blog.domain.BlogSystemNotificationRead;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 系统通知查看业务对象 blog_system_notification_read
 *
 * @author Lion Li
 * @date 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogSystemNotificationRead.class, reverseConvertGenerate = false)
public class BlogSystemNotificationReadBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 系统通知id
     */
    @NotNull(message = "系统通知id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long systemNotificationId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;


}
