package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogSystemNotificationRead;

import java.io.Serial;
import java.io.Serializable;


/**
 * 系统通知查看视图对象 blog_system_notification_read
 *
 * @author Lion Li
 * @date 2023-10-13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogSystemNotificationRead.class)
public class BlogSystemNotificationReadVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 系统通知id
     */
    @ExcelProperty(value = "系统通知id")
    private Long systemNotificationId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;


}
