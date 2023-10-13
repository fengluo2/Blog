package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogUserRelationshipBo;
import org.dromara.blog.domain.vo.BlogUserRelationshipVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 用户关系Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogUserRelationshipService {

    /**
     * 查询用户关系
     */
    BlogUserRelationshipVo queryById(Long id);

    /**
     * 查询用户关系列表
     */
    TableDataInfo<BlogUserRelationshipVo> queryPageList(BlogUserRelationshipBo bo, PageQuery pageQuery);

    /**
     * 查询用户关系列表
     */
    List<BlogUserRelationshipVo> queryList(BlogUserRelationshipBo bo);

    /**
     * 新增用户关系
     */
    Boolean insertByBo(BlogUserRelationshipBo bo);

    /**
     * 修改用户关系
     */
    Boolean updateByBo(BlogUserRelationshipBo bo);

    /**
     * 校验并批量删除用户关系信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
