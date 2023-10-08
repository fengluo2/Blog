import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PrivateChatMessageVO, PrivateChatMessageForm, PrivateChatMessageQuery } from '@/api/blog/privateChatMessage/types';

/**
 * 查询私聊消息列表
 * @param query
 * @returns {*}
 */

export const listPrivateChatMessage = (query?: PrivateChatMessageQuery): AxiosPromise<PrivateChatMessageVO[]> => {
  return request({
    url: '/blog/privateChatMessage/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询私聊消息详细
 * @param id
 */
export const getPrivateChatMessage = (id: string | number): AxiosPromise<PrivateChatMessageVO> => {
  return request({
    url: '/blog/privateChatMessage/' + id,
    method: 'get'
  });
};

/**
 * 新增私聊消息
 * @param data
 */
export const addPrivateChatMessage = (data: PrivateChatMessageForm) => {
  return request({
    url: '/blog/privateChatMessage',
    method: 'post',
    data: data
  });
};

/**
 * 修改私聊消息
 * @param data
 */
export const updatePrivateChatMessage = (data: PrivateChatMessageForm) => {
  return request({
    url: '/blog/privateChatMessage',
    method: 'put',
    data: data
  });
};

/**
 * 删除私聊消息
 * @param id
 */
export const delPrivateChatMessage = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/privateChatMessage/' + id,
    method: 'delete'
  });
};
