package org.dromara.blog.service.impl;

import org.dromara.blog.domain.BlogSystemNotification;
import org.dromara.blog.domain.bo.BlogSystemNotificationBo;
import org.dromara.blog.domain.vo.BlogSystemNotificationVo;
import org.dromara.blog.mapper.BlogSystemNotificationMapper;
import org.dromara.blog.service.IBlogSystemNotificationService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统通知Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogSystemNotificationServiceImpl implements IBlogSystemNotificationService {

    private final BlogSystemNotificationMapper baseMapper;

    /**
     * 查询系统通知
     */
    @Override
    public BlogSystemNotificationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询系统通知列表
     */
    @Override
    public TableDataInfo<BlogSystemNotificationVo> queryPageList(BlogSystemNotificationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogSystemNotification> lqw = buildQueryWrapper(bo);
        Page<BlogSystemNotificationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询系统通知列表
     */
    @Override
    public List<BlogSystemNotificationVo> queryList(BlogSystemNotificationBo bo) {
        LambdaQueryWrapper<BlogSystemNotification> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogSystemNotification> buildQueryWrapper(BlogSystemNotificationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogSystemNotification> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), BlogSystemNotification::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), BlogSystemNotification::getContent, bo.getContent());
        lqw.eq(StringUtils.isNotBlank(bo.getClick()), BlogSystemNotification::getClick, bo.getClick());
        return lqw;
    }

    /**
     * 新增系统通知
     */
    @Override
    public Boolean insertByBo(BlogSystemNotificationBo bo) {
        BlogSystemNotification add = MapstructUtils.convert(bo, BlogSystemNotification.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改系统通知
     */
    @Override
    public Boolean updateByBo(BlogSystemNotificationBo bo) {
        BlogSystemNotification update = MapstructUtils.convert(bo, BlogSystemNotification.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogSystemNotification entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统通知
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
