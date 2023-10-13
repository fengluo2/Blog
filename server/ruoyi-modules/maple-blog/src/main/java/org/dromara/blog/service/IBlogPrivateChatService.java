package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogPrivateChatBo;
import org.dromara.blog.domain.vo.BlogPrivateChatVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 私聊Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogPrivateChatService {

    /**
     * 查询私聊
     */
    BlogPrivateChatVo queryById(Long id);

    /**
     * 查询私聊列表
     */
    TableDataInfo<BlogPrivateChatVo> queryPageList(BlogPrivateChatBo bo, PageQuery pageQuery);

    /**
     * 查询私聊列表
     */
    List<BlogPrivateChatVo> queryList(BlogPrivateChatBo bo);

    /**
     * 新增私聊
     */
    Boolean insertByBo(BlogPrivateChatBo bo);

    /**
     * 修改私聊
     */
    Boolean updateByBo(BlogPrivateChatBo bo);

    /**
     * 校验并批量删除私聊信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
