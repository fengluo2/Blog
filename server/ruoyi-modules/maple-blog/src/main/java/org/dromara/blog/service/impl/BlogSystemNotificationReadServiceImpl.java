package org.dromara.blog.service.impl;

import org.dromara.blog.domain.bo.BlogSystemNotificationReadBo;
import org.dromara.blog.domain.vo.BlogSystemNotificationReadVo;
import org.dromara.blog.mapper.BlogSystemNotificationReadMapper;
import org.dromara.blog.service.IBlogSystemNotificationReadService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.BlogSystemNotificationRead;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统通知查看Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogSystemNotificationReadServiceImpl implements IBlogSystemNotificationReadService {

    private final BlogSystemNotificationReadMapper baseMapper;

    /**
     * 查询系统通知查看
     */
    @Override
    public BlogSystemNotificationReadVo queryById(Long systemNotificationId){
        return baseMapper.selectVoById(systemNotificationId);
    }

    /**
     * 查询系统通知查看列表
     */
    @Override
    public TableDataInfo<BlogSystemNotificationReadVo> queryPageList(BlogSystemNotificationReadBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogSystemNotificationRead> lqw = buildQueryWrapper(bo);
        Page<BlogSystemNotificationReadVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询系统通知查看列表
     */
    @Override
    public List<BlogSystemNotificationReadVo> queryList(BlogSystemNotificationReadBo bo) {
        LambdaQueryWrapper<BlogSystemNotificationRead> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogSystemNotificationRead> buildQueryWrapper(BlogSystemNotificationReadBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogSystemNotificationRead> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增系统通知查看
     */
    @Override
    public Boolean insertByBo(BlogSystemNotificationReadBo bo) {
        BlogSystemNotificationRead add = MapstructUtils.convert(bo, BlogSystemNotificationRead.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSystemNotificationId(add.getSystemNotificationId());
        }
        return flag;
    }

    /**
     * 修改系统通知查看
     */
    @Override
    public Boolean updateByBo(BlogSystemNotificationReadBo bo) {
        BlogSystemNotificationRead update = MapstructUtils.convert(bo, BlogSystemNotificationRead.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogSystemNotificationRead entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统通知查看
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
