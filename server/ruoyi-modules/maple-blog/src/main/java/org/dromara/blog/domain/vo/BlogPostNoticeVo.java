package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogPostNotice;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 文章通知视图对象 blog_post_notice
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogPostNotice.class)
public class BlogPostNoticeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 文章id
     */
    @ExcelProperty(value = "文章id")
    private Long postId;

    /**
     * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
     */
    @ExcelProperty(value = "通知类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=代表点赞,2=代表收藏,3=代表评论,4=代表回复")
    private String noticeType;

    /**
     * 通知id（点赞id、收藏id、评论id、回复id）
     */
    @ExcelProperty(value = "通知id", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "点=赞id、收藏id、评论id、回复id")
    private Long noticeId;

    /**
     * 已读标志（0代表未读 1代表已读）
     */
    @ExcelProperty(value = "已读标志", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表未读,1=代表已读")
    private String readFlag;


}
