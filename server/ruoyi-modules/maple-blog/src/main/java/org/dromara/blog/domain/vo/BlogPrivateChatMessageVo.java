package org.dromara.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.blog.domain.BlogPrivateChatMessage;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 私聊消息视图对象 blog_private_chat_message
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BlogPrivateChatMessage.class)
public class BlogPrivateChatMessageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 私聊id
     */
    @ExcelProperty(value = "私聊id")
    private Long chatId;

    /**
     * 发送者id
     */
    @ExcelProperty(value = "发送者id")
    private Long senderId;

    /**
     * 接收者id
     */
    @ExcelProperty(value = "接收者id")
    private Long receiverId;

    /**
     * 消息内容
     */
    @ExcelProperty(value = "消息内容")
    private String content;

    /**
     * 已读标志（0代表未读 1代表已读）
     */
    @ExcelProperty(value = "已读标志", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=代表未读,1=代表已读")
    private String readFlag;


}
