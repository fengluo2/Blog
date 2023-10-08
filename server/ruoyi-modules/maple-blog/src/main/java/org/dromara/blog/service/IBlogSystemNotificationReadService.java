package org.dromara.blog.service;

import org.dromara.blog.domain.vo.BlogSystemNotificationReadVo;
import org.dromara.blog.domain.bo.BlogSystemNotificationReadBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 系统通知查看Service接口
 *
 * @author Lion Li
 * @date 2023-10-07
 */
public interface IBlogSystemNotificationReadService {

    /**
     * 查询系统通知查看
     */
    BlogSystemNotificationReadVo queryById(Long systemNotificationId);

    /**
     * 查询系统通知查看列表
     */
    TableDataInfo<BlogSystemNotificationReadVo> queryPageList(BlogSystemNotificationReadBo bo, PageQuery pageQuery);

    /**
     * 查询系统通知查看列表
     */
    List<BlogSystemNotificationReadVo> queryList(BlogSystemNotificationReadBo bo);

    /**
     * 新增系统通知查看
     */
    Boolean insertByBo(BlogSystemNotificationReadBo bo);

    /**
     * 修改系统通知查看
     */
    Boolean updateByBo(BlogSystemNotificationReadBo bo);

    /**
     * 校验并批量删除系统通知查看信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
