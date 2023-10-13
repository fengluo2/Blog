package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogCommentBo;
import org.dromara.blog.domain.vo.BlogCommentVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 评论Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogCommentService {

    /**
     * 查询评论
     */
    BlogCommentVo queryById(Long id);

    /**
     * 查询评论列表
     */
    TableDataInfo<BlogCommentVo> queryPageList(BlogCommentBo bo, PageQuery pageQuery);

    /**
     * 查询评论列表
     */
    List<BlogCommentVo> queryList(BlogCommentBo bo);

    /**
     * 新增评论
     */
    Boolean insertByBo(BlogCommentBo bo);

    /**
     * 修改评论
     */
    Boolean updateByBo(BlogCommentBo bo);

    /**
     * 校验并批量删除评论信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
