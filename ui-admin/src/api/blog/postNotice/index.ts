import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PostNoticeVO, PostNoticeForm, PostNoticeQuery } from '@/api/blog/postNotice/types';

/**
 * 查询文章通知列表
 * @param query
 * @returns {*}
 */

export const listPostNotice = (query?: PostNoticeQuery): AxiosPromise<PostNoticeVO[]> => {
  return request({
    url: '/blog/postNotice/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询文章通知详细
 * @param id
 */
export const getPostNotice = (id: string | number): AxiosPromise<PostNoticeVO> => {
  return request({
    url: '/blog/postNotice/' + id,
    method: 'get'
  });
};

/**
 * 新增文章通知
 * @param data
 */
export const addPostNotice = (data: PostNoticeForm) => {
  return request({
    url: '/blog/postNotice',
    method: 'post',
    data: data
  });
};

/**
 * 修改文章通知
 * @param data
 */
export const updatePostNotice = (data: PostNoticeForm) => {
  return request({
    url: '/blog/postNotice',
    method: 'put',
    data: data
  });
};

/**
 * 删除文章通知
 * @param id
 */
export const delPostNotice = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/postNotice/' + id,
    method: 'delete'
  });
};
