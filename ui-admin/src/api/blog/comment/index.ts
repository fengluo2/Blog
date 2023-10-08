import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { CommentVO, CommentForm, CommentQuery } from '@/api/blog/comment/types';

/**
 * 查询评论列表
 * @param query
 * @returns {*}
 */

export const listComment = (query?: CommentQuery): AxiosPromise<CommentVO[]> => {
  return request({
    url: '/blog/comment/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询评论详细
 * @param id
 */
export const getComment = (id: string | number): AxiosPromise<CommentVO> => {
  return request({
    url: '/blog/comment/' + id,
    method: 'get'
  });
};

/**
 * 新增评论
 * @param data
 */
export const addComment = (data: CommentForm) => {
  return request({
    url: '/blog/comment',
    method: 'post',
    data: data
  });
};

/**
 * 修改评论
 * @param data
 */
export const updateComment = (data: CommentForm) => {
  return request({
    url: '/blog/comment',
    method: 'put',
    data: data
  });
};

/**
 * 删除评论
 * @param id
 */
export const delComment = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/comment/' + id,
    method: 'delete'
  });
};
