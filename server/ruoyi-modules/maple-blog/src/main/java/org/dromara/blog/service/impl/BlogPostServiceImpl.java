package org.dromara.blog.service.impl;

import org.dromara.blog.domain.BlogPost;
import org.dromara.blog.domain.bo.BlogPostBo;
import org.dromara.blog.domain.vo.BlogPostVo;
import org.dromara.blog.mapper.BlogPostMapper;
import org.dromara.blog.service.IBlogPostService;
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
 * 文章Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogPostServiceImpl implements IBlogPostService {

    private final BlogPostMapper baseMapper;

    /**
     * 查询文章
     */
    @Override
    public BlogPostVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询文章列表
     */
    @Override
    public TableDataInfo<BlogPostVo> queryPageList(BlogPostBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogPost> lqw = buildQueryWrapper(bo);
        Page<BlogPostVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询文章列表
     */
    @Override
    public List<BlogPostVo> queryList(BlogPostBo bo) {
        LambdaQueryWrapper<BlogPost> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogPost> buildQueryWrapper(BlogPostBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogPost> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), BlogPost::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getContentShort()), BlogPost::getContentShort, bo.getContentShort());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), BlogPost::getContent, bo.getContent());
        lqw.eq(StringUtils.isNotBlank(bo.getAuthorSignature()), BlogPost::getAuthorSignature, bo.getAuthorSignature());
        lqw.eq(StringUtils.isNotBlank(bo.getLevelFlag()), BlogPost::getLevelFlag, bo.getLevelFlag());
        return lqw;
    }

    /**
     * 新增文章
     */
    @Override
    public Boolean insertByBo(BlogPostBo bo) {
        BlogPost add = MapstructUtils.convert(bo, BlogPost.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改文章
     */
    @Override
    public Boolean updateByBo(BlogPostBo bo) {
        BlogPost update = MapstructUtils.convert(bo, BlogPost.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogPost entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除文章
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
