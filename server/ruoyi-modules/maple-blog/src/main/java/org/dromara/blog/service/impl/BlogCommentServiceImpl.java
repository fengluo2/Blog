package org.dromara.blog.service.impl;

import org.dromara.blog.domain.bo.BlogCommentBo;
import org.dromara.blog.mapper.BlogCommentMapper;
import org.dromara.blog.service.IBlogCommentService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.vo.BlogCommentVo;
import org.dromara.blog.domain.BlogComment;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 评论Service业务层处理
 *
 * @author Lion Li
 * @date 2023-10-07
 */
@RequiredArgsConstructor
@Service
public class BlogCommentServiceImpl implements IBlogCommentService {

    private final BlogCommentMapper baseMapper;

    /**
     * 查询评论
     */
    @Override
    public BlogCommentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询评论列表
     */
    @Override
    public TableDataInfo<BlogCommentVo> queryPageList(BlogCommentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BlogComment> lqw = buildQueryWrapper(bo);
        Page<BlogCommentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询评论列表
     */
    @Override
    public List<BlogCommentVo> queryList(BlogCommentBo bo) {
        LambdaQueryWrapper<BlogComment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BlogComment> buildQueryWrapper(BlogCommentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BlogComment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getReplyId() != null, BlogComment::getReplyId, bo.getReplyId());
        lqw.eq(bo.getLevel() != null, BlogComment::getLevel, bo.getLevel());
        lqw.eq(bo.getPostId() != null, BlogComment::getPostId, bo.getPostId());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), BlogComment::getContent, bo.getContent());
        lqw.eq(StringUtils.isNotBlank(bo.getMasterFlag()), BlogComment::getMasterFlag, bo.getMasterFlag());
        return lqw;
    }

    /**
     * 新增评论
     */
    @Override
    public Boolean insertByBo(BlogCommentBo bo) {
        BlogComment add = MapstructUtils.convert(bo, BlogComment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改评论
     */
    @Override
    public Boolean updateByBo(BlogCommentBo bo) {
        BlogComment update = MapstructUtils.convert(bo, BlogComment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BlogComment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除评论
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
