package org.dromara.blog.domain.vo;

import org.dromara.blog.domain.BlogTag;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 标签视图对象 blog_tag
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogTag.class)
public class BlogTagVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 标签名
     */
    @ExcelProperty(value = "标签名")
    private String name;


}
