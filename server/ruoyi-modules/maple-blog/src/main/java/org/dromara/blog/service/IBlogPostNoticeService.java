package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogPostNoticeBo;
import org.dromara.blog.domain.vo.BlogPostNoticeVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 文章通知Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogPostNoticeService {

    /**
     * 查询文章通知
     */
    BlogPostNoticeVo queryById(Long id);

    /**
     * 查询文章通知列表
     */
    TableDataInfo<BlogPostNoticeVo> queryPageList(BlogPostNoticeBo bo, PageQuery pageQuery);

    /**
     * 查询文章通知列表
     */
    List<BlogPostNoticeVo> queryList(BlogPostNoticeBo bo);

    /**
     * 新增文章通知
     */
    Boolean insertByBo(BlogPostNoticeBo bo);

    /**
     * 修改文章通知
     */
    Boolean updateByBo(BlogPostNoticeBo bo);

    /**
     * 校验并批量删除文章通知信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
