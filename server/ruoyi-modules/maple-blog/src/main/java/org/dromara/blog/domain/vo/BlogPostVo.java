package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogPost;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 文章视图对象 blog_post
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogPost.class)
public class BlogPostVo implements Serializable {

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
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String contentShort;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 作者署名
     */
    @ExcelProperty(value = "作者署名")
    private String authorSignature;

    /**
     * 权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）
     */
    @ExcelProperty(value = "权限级别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表公开，1代表订阅权限，2代表仅自己可见")
    private String levelFlag;


}
