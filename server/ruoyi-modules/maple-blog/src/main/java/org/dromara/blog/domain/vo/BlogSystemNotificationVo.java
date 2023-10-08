package org.dromara.blog.domain.vo;

import org.dromara.blog.domain.BlogSystemNotification;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 系统通知视图对象 blog_system_notification
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogSystemNotification.class)
public class BlogSystemNotificationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 可点击标志（0代表未读 1代表可点击）
     */
    @ExcelProperty(value = "可点击标志", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表未读,1=代表可点击")
    private String click;


}
