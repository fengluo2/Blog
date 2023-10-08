import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PostVO, PostForm, PostQuery } from '@/api/blog/post/types';

/**
 * 查询文章列表
 * @param query
 * @returns {*}
 */

export const listPost = (query?: PostQuery): AxiosPromise<PostVO[]> => {
  return request({
    url: '/blog/post/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询文章详细
 * @param id
 */
export const getPost = (id: string | number): AxiosPromise<PostVO> => {
  return request({
    url: '/blog/post/' + id,
    method: 'get'
  });
};

/**
 * 新增文章
 * @param data
 */
export const addPost = (data: PostForm) => {
  return request({
    url: '/blog/post',
    method: 'post',
    data: data
  });
};

/**
 * 修改文章
 * @param data
 */
export const updatePost = (data: PostForm) => {
  return request({
    url: '/blog/post',
    method: 'put',
    data: data
  });
};

/**
 * 删除文章
 * @param id
 */
export const delPost = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/post/' + id,
    method: 'delete'
  });
};
