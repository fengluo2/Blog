package org.dromara.blog.domain.vo;

import org.dromara.blog.domain.BlogComment;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 评论视图对象 blog_comment
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogComment.class)
public class BlogCommentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 回复id（0代表1级评论）
     */
    @ExcelProperty(value = "回复id", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表1级评论")
    private Long replyId;

    /**
     * 评论级别
     */
    @ExcelProperty(value = "评论级别")
    private Long level;

    /**
     * 文章id
     */
    @ExcelProperty(value = "文章id")
    private Long postId;

    /**
     * 评论内容
     */
    @ExcelProperty(value = "评论内容")
    private String content;

    /**
     * 主评论标志（0代表不是 1代表是）
     */
    @ExcelProperty(value = "主评论标志", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表不是,1=代表是")
    private String masterFlag;


}
