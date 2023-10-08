package org.dromara.blog.service.impl;

import org.dromara.blog.domain.bo.BlogPrivateChatMessageBo;
import org.dromara.blog.domain.vo.BlogPrivateChatMessageVo;
import org.dromara.blog.mapper.BlogPrivateChatMessageMapper;
import org.dromara.blog.service.IBlogPrivateChatMessageService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.BlogPrivateChatMessage;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 私聊消息Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogPrivateChatMessageServiceImpl implements IBlogPrivateChatMessageService {

    private final BlogPrivateChatMessageMapper baseMapper;

    /**
     * 查询私聊消息
     */
    @Override
    public BlogPrivateChatMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询私聊消息列表
     */
    @Override
    public TableDataInfo<BlogPrivateChatMessageVo> queryPageList(BlogPrivateChatMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogPrivateChatMessage> lqw = buildQueryWrapper(bo);
        Page<BlogPrivateChatMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询私聊消息列表
     */
    @Override
    public List<BlogPrivateChatMessageVo> queryList(BlogPrivateChatMessageBo bo) {
        LambdaQueryWrapper<BlogPrivateChatMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogPrivateChatMessage> buildQueryWrapper(BlogPrivateChatMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogPrivateChatMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChatId() != null, BlogPrivateChatMessage::getChatId, bo.getChatId());
        lqw.eq(bo.getSenderId() != null, BlogPrivateChatMessage::getSenderId, bo.getSenderId());
        lqw.eq(bo.getReceiverId() != null, BlogPrivateChatMessage::getReceiverId, bo.getReceiverId());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), BlogPrivateChatMessage::getContent, bo.getContent());
        lqw.eq(StringUtils.isNotBlank(bo.getReadFlag()), BlogPrivateChatMessage::getReadFlag, bo.getReadFlag());
        return lqw;
    }

    /**
     * 新增私聊消息
     */
    @Override
    public Boolean insertByBo(BlogPrivateChatMessageBo bo) {
        BlogPrivateChatMessage add = MapstructUtils.convert(bo, BlogPrivateChatMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改私聊消息
     */
    @Override
    public Boolean updateByBo(BlogPrivateChatMessageBo bo) {
        BlogPrivateChatMessage update = MapstructUtils.convert(bo, BlogPrivateChatMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogPrivateChatMessage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除私聊消息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
