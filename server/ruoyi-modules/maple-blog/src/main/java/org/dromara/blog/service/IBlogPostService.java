package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogPostBo;
import org.dromara.blog.domain.vo.BlogPostVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 文章Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogPostService {

    /**
     * 查询文章
     */
    BlogPostVo queryById(Long id);

    /**
     * 查询文章列表
     */
    TableDataInfo<BlogPostVo> queryPageList(BlogPostBo bo, PageQuery pageQuery);

    /**
     * 查询文章列表
     */
    List<BlogPostVo> queryList(BlogPostBo bo);

    /**
     * 新增文章
     */
    Boolean insertByBo(BlogPostBo bo);

    /**
     * 修改文章
     */
    Boolean updateByBo(BlogPostBo bo);

    /**
     * 校验并批量删除文章信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
