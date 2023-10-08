package org.dromara.blog.service.impl;

import org.dromara.blog.domain.BlogTag;
import org.dromara.blog.domain.bo.BlogTagBo;
import org.dromara.blog.domain.vo.BlogTagVo;
import org.dromara.blog.mapper.BlogTagMapper;
import org.dromara.blog.service.IBlogTagService;
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
 * 标签Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogTagServiceImpl implements IBlogTagService {

    private final BlogTagMapper baseMapper;

    /**
     * 查询标签
     */
    @Override
    public BlogTagVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询标签列表
     */
    @Override
    public TableDataInfo<BlogTagVo> queryPageList(BlogTagBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogTag> lqw = buildQueryWrapper(bo);
        Page<BlogTagVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询标签列表
     */
    @Override
    public List<BlogTagVo> queryList(BlogTagBo bo) {
        LambdaQueryWrapper<BlogTag> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogTag> buildQueryWrapper(BlogTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogTag> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BlogTag::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增标签
     */
    @Override
    public Boolean insertByBo(BlogTagBo bo) {
        BlogTag add = MapstructUtils.convert(bo, BlogTag.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改标签
     */
    @Override
    public Boolean updateByBo(BlogTagBo bo) {
        BlogTag update = MapstructUtils.convert(bo, BlogTag.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogTag entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除标签
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
