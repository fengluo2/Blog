import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SystemNotificationVO, SystemNotificationForm, SystemNotificationQuery } from '@/api/blog/systemNotification/types';

/**
 * 查询系统通知列表
 * @param query
 * @returns {*}
 */

export const listSystemNotification = (query?: SystemNotificationQuery): AxiosPromise<SystemNotificationVO[]> => {
  return request({
    url: '/blog/systemNotification/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询系统通知详细
 * @param id
 */
export const getSystemNotification = (id: string | number): AxiosPromise<SystemNotificationVO> => {
  return request({
    url: '/blog/systemNotification/' + id,
    method: 'get'
  });
};

/**
 * 新增系统通知
 * @param data
 */
export const addSystemNotification = (data: SystemNotificationForm) => {
  return request({
    url: '/blog/systemNotification',
    method: 'post',
    data: data
  });
};

/**
 * 修改系统通知
 * @param data
 */
export const updateSystemNotification = (data: SystemNotificationForm) => {
  return request({
    url: '/blog/systemNotification',
    method: 'put',
    data: data
  });
};

/**
 * 删除系统通知
 * @param id
 */
export const delSystemNotification = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/systemNotification/' + id,
    method: 'delete'
  });
};
