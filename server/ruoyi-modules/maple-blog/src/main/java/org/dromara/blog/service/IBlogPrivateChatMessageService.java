package org.dromara.blog.service;

import org.dromara.blog.domain.vo.BlogPrivateChatMessageVo;
import org.dromara.blog.domain.bo.BlogPrivateChatMessageBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 私聊消息Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogPrivateChatMessageService {

    /**
     * 查询私聊消息
     */
    BlogPrivateChatMessageVo queryById(Long id);

    /**
     * 查询私聊消息列表
     */
    TableDataInfo<BlogPrivateChatMessageVo> queryPageList(BlogPrivateChatMessageBo bo, PageQuery pageQuery);

    /**
     * 查询私聊消息列表
     */
    List<BlogPrivateChatMessageVo> queryList(BlogPrivateChatMessageBo bo);

    /**
     * 新增私聊消息
     */
    Boolean insertByBo(BlogPrivateChatMessageBo bo);

    /**
     * 修改私聊消息
     */
    Boolean updateByBo(BlogPrivateChatMessageBo bo);

    /**
     * 校验并批量删除私聊消息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
