package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.BlogUserRelationship;
import org.dromara.blog.domain.bo.BlogUserRelationshipBo;
import org.dromara.blog.domain.vo.BlogUserRelationshipVo;
import org.dromara.blog.mapper.BlogUserRelationshipMapper;
import org.dromara.blog.service.IBlogUserRelationshipService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户关系Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogUserRelationshipServiceImpl implements IBlogUserRelationshipService {

    private final BlogUserRelationshipMapper baseMapper;

    /**
     * 查询用户关系
     */
    @Override
    public BlogUserRelationshipVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询用户关系列表
     */
    @Override
    public TableDataInfo<BlogUserRelationshipVo> queryPageList(BlogUserRelationshipBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogUserRelationship> lqw = buildQueryWrapper(bo);
        Page<BlogUserRelationshipVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户关系列表
     */
    @Override
    public List<BlogUserRelationshipVo> queryList(BlogUserRelationshipBo bo) {
        LambdaQueryWrapper<BlogUserRelationship> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogUserRelationship> buildQueryWrapper(BlogUserRelationshipBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogUserRelationship> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, BlogUserRelationship::getUserId, bo.getUserId());
        lqw.eq(bo.getAssociatedUserId() != null, BlogUserRelationship::getAssociatedUserId, bo.getAssociatedUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getRelationshipType()), BlogUserRelationship::getRelationshipType, bo.getRelationshipType());
        return lqw;
    }

    /**
     * 新增用户关系
     */
    @Override
    public Boolean insertByBo(BlogUserRelationshipBo bo) {
        BlogUserRelationship add = MapstructUtils.convert(bo, BlogUserRelationship.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户关系
     */
    @Override
    public Boolean updateByBo(BlogUserRelationshipBo bo) {
        BlogUserRelationship update = MapstructUtils.convert(bo, BlogUserRelationship.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogUserRelationship entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户关系
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
