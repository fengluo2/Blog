import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SystemNotificationReadVO, SystemNotificationReadForm, SystemNotificationReadQuery } from '@/api/blog/systemNotificationRead/types';

/**
 * 查询系统通知查看列表
 * @param query
 * @returns {*}
 */

export const listSystemNotificationRead = (query?: SystemNotificationReadQuery): AxiosPromise<SystemNotificationReadVO[]> => {
  return request({
    url: '/blog/systemNotificationRead/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询系统通知查看详细
 * @param systemNotificationId
 */
export const getSystemNotificationRead = (systemNotificationId: string | number): AxiosPromise<SystemNotificationReadVO> => {
  return request({
    url: '/blog/systemNotificationRead/' + systemNotificationId,
    method: 'get'
  });
};

/**
 * 新增系统通知查看
 * @param data
 */
export const addSystemNotificationRead = (data: SystemNotificationReadForm) => {
  return request({
    url: '/blog/systemNotificationRead',
    method: 'post',
    data: data
  });
};

/**
 * 修改系统通知查看
 * @param data
 */
export const updateSystemNotificationRead = (data: SystemNotificationReadForm) => {
  return request({
    url: '/blog/systemNotificationRead',
    method: 'put',
    data: data
  });
};

/**
 * 删除系统通知查看
 * @param systemNotificationId
 */
export const delSystemNotificationRead = (systemNotificationId: string | number | Array<string | number>) => {
  return request({
    url: '/blog/systemNotificationRead/' + systemNotificationId,
    method: 'delete'
  });
};
