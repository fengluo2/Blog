import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { TagVO, TagForm, TagQuery } from '@/api/blog/tag/types';

/**
 * 查询标签列表
 * @param query
 * @returns {*}
 */

export const listTag = (query?: TagQuery): AxiosPromise<TagVO[]> => {
  return request({
    url: '/blog/tag/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询标签详细
 * @param id
 */
export const getTag = (id: string | number): AxiosPromise<TagVO> => {
  return request({
    url: '/blog/tag/' + id,
    method: 'get'
  });
};

/**
 * 新增标签
 * @param data
 */
export const addTag = (data: TagForm) => {
  return request({
    url: '/blog/tag',
    method: 'post',
    data: data
  });
};

/**
 * 修改标签
 * @param data
 */
export const updateTag = (data: TagForm) => {
  return request({
    url: '/blog/tag',
    method: 'put',
    data: data
  });
};

/**
 * 删除标签
 * @param id
 */
export const delTag = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/tag/' + id,
    method: 'delete'
  });
};
