package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.BlogPrivateChat;
import org.dromara.blog.domain.bo.BlogPrivateChatBo;
import org.dromara.blog.domain.vo.BlogPrivateChatVo;
import org.dromara.blog.mapper.BlogPrivateChatMapper;
import org.dromara.blog.service.IBlogPrivateChatService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 私聊Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogPrivateChatServiceImpl implements IBlogPrivateChatService {

    private final BlogPrivateChatMapper baseMapper;

    /**
     * 查询私聊
     */
    @Override
    public BlogPrivateChatVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询私聊列表
     */
    @Override
    public TableDataInfo<BlogPrivateChatVo> queryPageList(BlogPrivateChatBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogPrivateChat> lqw = buildQueryWrapper(bo);
        Page<BlogPrivateChatVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询私聊列表
     */
    @Override
    public List<BlogPrivateChatVo> queryList(BlogPrivateChatBo bo) {
        LambdaQueryWrapper<BlogPrivateChat> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogPrivateChat> buildQueryWrapper(BlogPrivateChatBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogPrivateChat> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserAId() != null, BlogPrivateChat::getUserAId, bo.getUserAId());
        lqw.eq(bo.getUserBId() != null, BlogPrivateChat::getUserBId, bo.getUserBId());
        lqw.eq(bo.getUserAUnreadCount() != null, BlogPrivateChat::getUserAUnreadCount, bo.getUserAUnreadCount());
        lqw.eq(bo.getUserBUnreadCount() != null, BlogPrivateChat::getUserBUnreadCount, bo.getUserBUnreadCount());
        return lqw;
    }

    /**
     * 新增私聊
     */
    @Override
    public Boolean insertByBo(BlogPrivateChatBo bo) {
        BlogPrivateChat add = MapstructUtils.convert(bo, BlogPrivateChat.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改私聊
     */
    @Override
    public Boolean updateByBo(BlogPrivateChatBo bo) {
        BlogPrivateChat update = MapstructUtils.convert(bo, BlogPrivateChat.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogPrivateChat entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除私聊
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
