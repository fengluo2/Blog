import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { CollectionVO, CollectionForm, CollectionQuery } from '@/api/blog/collection/types';

/**
 * 查询合集列表
 * @param query
 * @returns {*}
 */

export const listCollection = (query?: CollectionQuery): AxiosPromise<CollectionVO[]> => {
  return request({
    url: '/blog/collection/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询合集详细
 * @param id
 */
export const getCollection = (id: string | number): AxiosPromise<CollectionVO> => {
  return request({
    url: '/blog/collection/' + id,
    method: 'get'
  });
};

/**
 * 新增合集
 * @param data
 */
export const addCollection = (data: CollectionForm) => {
  return request({
    url: '/blog/collection',
    method: 'post',
    data: data
  });
};

/**
 * 修改合集
 * @param data
 */
export const updateCollection = (data: CollectionForm) => {
  return request({
    url: '/blog/collection',
    method: 'put',
    data: data
  });
};

/**
 * 删除合集
 * @param id
 */
export const delCollection = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/collection/' + id,
    method: 'delete'
  });
};
