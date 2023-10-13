package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogCollection;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 合集视图对象 blog_collection
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogCollection.class)
public class BlogCollectionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 合集名称
     */
    @ExcelProperty(value = "合集名称")
    private String name;

    /**
     * 合集描述
     */
    @ExcelProperty(value = "合集描述")
    private String describe;

    /**
     * 订阅标志（0为免费 1为订阅）
     */
    @ExcelProperty(value = "订阅标志", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=为免费,1=为订阅")
    private String payFlag;

    /**
     * 从第节开始付费
     */
    @ExcelProperty(value = "从第节开始付费")
    private Long payIndex;


}
