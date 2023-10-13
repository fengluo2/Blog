package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.BlogCollection;
import org.dromara.blog.domain.bo.BlogCollectionBo;
import org.dromara.blog.domain.vo.BlogCollectionVo;
import org.dromara.blog.mapper.BlogCollectionMapper;
import org.dromara.blog.service.IBlogCollectionService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 合集Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogCollectionServiceImpl implements IBlogCollectionService {

    private final BlogCollectionMapper baseMapper;

    /**
     * 查询合集
     */
    @Override
    public BlogCollectionVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询合集列表
     */
    @Override
    public TableDataInfo<BlogCollectionVo> queryPageList(BlogCollectionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogCollection> lqw = buildQueryWrapper(bo);
        Page<BlogCollectionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询合集列表
     */
    @Override
    public List<BlogCollectionVo> queryList(BlogCollectionBo bo) {
        LambdaQueryWrapper<BlogCollection> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogCollection> buildQueryWrapper(BlogCollectionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogCollection> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BlogCollection::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescribe()), BlogCollection::getDescribe, bo.getDescribe());
        lqw.eq(StringUtils.isNotBlank(bo.getPayFlag()), BlogCollection::getPayFlag, bo.getPayFlag());
        lqw.eq(bo.getPayIndex() != null, BlogCollection::getPayIndex, bo.getPayIndex());
        return lqw;
    }

    /**
     * 新增合集
     */
    @Override
    public Boolean insertByBo(BlogCollectionBo bo) {
        BlogCollection add = MapstructUtils.convert(bo, BlogCollection.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改合集
     */
    @Override
    public Boolean updateByBo(BlogCollectionBo bo) {
        BlogCollection update = MapstructUtils.convert(bo, BlogCollection.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogCollection entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合集
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
