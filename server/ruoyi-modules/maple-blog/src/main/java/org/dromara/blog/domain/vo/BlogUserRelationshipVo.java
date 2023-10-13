package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogUserRelationship;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 用户关系视图对象 blog_user_relationship
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogUserRelationship.class)
public class BlogUserRelationshipVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 关联用户
     */
    @ExcelProperty(value = "关联用户")
    private Long associatedUserId;

    /**
     * 关系类型（1代表关注 2代表订阅 3代表拉黑）
     */
    @ExcelProperty(value = "关系类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=代表关注,2=代表订阅,3=代表拉黑")
    private String relationshipType;


}
