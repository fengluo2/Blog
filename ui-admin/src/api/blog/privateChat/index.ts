import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PrivateChatVO, PrivateChatForm, PrivateChatQuery } from '@/api/blog/privateChat/types';

/**
 * 查询私聊列表
 * @param query
 * @returns {*}
 */

export const listPrivateChat = (query?: PrivateChatQuery): AxiosPromise<PrivateChatVO[]> => {
  return request({
    url: '/blog/privateChat/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询私聊详细
 * @param id
 */
export const getPrivateChat = (id: string | number): AxiosPromise<PrivateChatVO> => {
  return request({
    url: '/blog/privateChat/' + id,
    method: 'get'
  });
};

/**
 * 新增私聊
 * @param data
 */
export const addPrivateChat = (data: PrivateChatForm) => {
  return request({
    url: '/blog/privateChat',
    method: 'post',
    data: data
  });
};

/**
 * 修改私聊
 * @param data
 */
export const updatePrivateChat = (data: PrivateChatForm) => {
  return request({
    url: '/blog/privateChat',
    method: 'put',
    data: data
  });
};

/**
 * 删除私聊
 * @param id
 */
export const delPrivateChat = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/privateChat/' + id,
    method: 'delete'
  });
};
