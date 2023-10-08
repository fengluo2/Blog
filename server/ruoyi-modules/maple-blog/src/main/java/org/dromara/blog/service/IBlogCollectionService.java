package org.dromara.blog.service;

import org.dromara.blog.domain.vo.BlogCollectionVo;
import org.dromara.blog.domain.bo.BlogCollectionBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 合集Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogCollectionService {

    /**
     * 查询合集
     */
    BlogCollectionVo queryById(Long id);

    /**
     * 查询合集列表
     */
    TableDataInfo<BlogCollectionVo> queryPageList(BlogCollectionBo bo, PageQuery pageQuery);

    /**
     * 查询合集列表
     */
    List<BlogCollectionVo> queryList(BlogCollectionBo bo);

    /**
     * 新增合集
     */
    Boolean insertByBo(BlogCollectionBo bo);

    /**
     * 修改合集
     */
    Boolean updateByBo(BlogCollectionBo bo);

    /**
     * 校验并批量删除合集信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
