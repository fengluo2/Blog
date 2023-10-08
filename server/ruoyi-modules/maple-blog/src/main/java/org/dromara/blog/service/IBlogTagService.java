package org.dromara.blog.service;

import org.dromara.blog.domain.vo.BlogTagVo;
import org.dromara.blog.domain.bo.BlogTagBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 标签Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogTagService {

    /**
     * 查询标签
     */
    BlogTagVo queryById(Long id);

    /**
     * 查询标签列表
     */
    TableDataInfo<BlogTagVo> queryPageList(BlogTagBo bo, PageQuery pageQuery);

    /**
     * 查询标签列表
     */
    List<BlogTagVo> queryList(BlogTagBo bo);

    /**
     * 新增标签
     */
    Boolean insertByBo(BlogTagBo bo);

    /**
     * 修改标签
     */
    Boolean updateByBo(BlogTagBo bo);

    /**
     * 校验并批量删除标签信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
