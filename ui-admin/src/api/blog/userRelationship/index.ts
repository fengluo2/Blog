import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { UserRelationshipVO, UserRelationshipForm, UserRelationshipQuery } from '@/api/blog/userRelationship/types';

/**
 * 查询用户关系列表
 * @param query
 * @returns {*}
 */

export const listUserRelationship = (query?: UserRelationshipQuery): AxiosPromise<UserRelationshipVO[]> => {
  return request({
    url: '/blog/userRelationship/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询用户关系详细
 * @param id
 */
export const getUserRelationship = (id: string | number): AxiosPromise<UserRelationshipVO> => {
  return request({
    url: '/blog/userRelationship/' + id,
    method: 'get'
  });
};

/**
 * 新增用户关系
 * @param data
 */
export const addUserRelationship = (data: UserRelationshipForm) => {
  return request({
    url: '/blog/userRelationship',
    method: 'post',
    data: data
  });
};

/**
 * 修改用户关系
 * @param data
 */
export const updateUserRelationship = (data: UserRelationshipForm) => {
  return request({
    url: '/blog/userRelationship',
    method: 'put',
    data: data
  });
};

/**
 * 删除用户关系
 * @param id
 */
export const delUserRelationship = (id: string | number | Array<string | number>) => {
  return request({
    url: '/blog/userRelationship/' + id,
    method: 'delete'
  });
};
