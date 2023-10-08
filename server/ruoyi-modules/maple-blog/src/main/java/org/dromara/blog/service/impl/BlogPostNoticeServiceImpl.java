package org.dromara.blog.service.impl;

import org.dromara.blog.domain.BlogPostNotice;
import org.dromara.blog.domain.bo.BlogPostNoticeBo;
import org.dromara.blog.domain.vo.BlogPostNoticeVo;
import org.dromara.blog.mapper.BlogPostNoticeMapper;
import org.dromara.blog.service.IBlogPostNoticeService;
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
 * 文章通知Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogPostNoticeServiceImpl implements IBlogPostNoticeService {

    private final BlogPostNoticeMapper baseMapper;

    /**
     * 查询文章通知
     */
    @Override
    public BlogPostNoticeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询文章通知列表
     */
    @Override
    public TableDataInfo<BlogPostNoticeVo> queryPageList(BlogPostNoticeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogPostNotice> lqw = buildQueryWrapper(bo);
        Page<BlogPostNoticeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询文章通知列表
     */
    @Override
    public List<BlogPostNoticeVo> queryList(BlogPostNoticeBo bo) {
        LambdaQueryWrapper<BlogPostNotice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogPostNotice> buildQueryWrapper(BlogPostNoticeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogPostNotice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPostId() != null, BlogPostNotice::getPostId, bo.getPostId());
        lqw.eq(StringUtils.isNotBlank(bo.getNoticeType()), BlogPostNotice::getNoticeType, bo.getNoticeType());
        lqw.eq(bo.getNoticeId() != null, BlogPostNotice::getNoticeId, bo.getNoticeId());
        lqw.eq(StringUtils.isNotBlank(bo.getReadFlag()), BlogPostNotice::getReadFlag, bo.getReadFlag());
        return lqw;
    }

    /**
     * 新增文章通知
     */
    @Override
    public Boolean insertByBo(BlogPostNoticeBo bo) {
        BlogPostNotice add = MapstructUtils.convert(bo, BlogPostNotice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改文章通知
     */
    @Override
    public Boolean updateByBo(BlogPostNoticeBo bo) {
        BlogPostNotice update = MapstructUtils.convert(bo, BlogPostNotice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogPostNotice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除文章通知
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
