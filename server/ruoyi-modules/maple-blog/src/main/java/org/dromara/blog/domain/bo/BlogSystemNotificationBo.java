package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.BlogSystemNotification;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 系统通知业务对象 blog_system_notification
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BlogSystemNotification.class, reverseConvertGenerate = false)
public class BlogSystemNotificationBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 可点击标志（0代表未读 1代表可点击）
     */
    @NotBlank(message = "可点击标志（0代表未读 1代表可点击）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String click;


}
