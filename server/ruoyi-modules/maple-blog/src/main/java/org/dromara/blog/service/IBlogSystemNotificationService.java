package org.dromara.blog.service;

import org.dromara.blog.domain.bo.BlogSystemNotificationBo;
import org.dromara.blog.domain.vo.BlogSystemNotificationVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 系统通知Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogSystemNotificationService {

    /**
     * 查询系统通知
     */
    BlogSystemNotificationVo queryById(Long id);

    /**
     * 查询系统通知列表
     */
    TableDataInfo<BlogSystemNotificationVo> queryPageList(BlogSystemNotificationBo bo, PageQuery pageQuery);

    /**
     * 查询系统通知列表
     */
    List<BlogSystemNotificationVo> queryList(BlogSystemNotificationBo bo);

    /**
     * 新增系统通知
     */
    Boolean insertByBo(BlogSystemNotificationBo bo);

    /**
     * 修改系统通知
     */
    Boolean updateByBo(BlogSystemNotificationBo bo);

    /**
     * 校验并批量删除系统通知信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
