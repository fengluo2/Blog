export interface UserRelationshipVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 用户id
   */
  userId: string | number;

  /**
   * 关联用户
   */
  associatedUserId: string | number;

  /**
   * 关系类型（1代表关注 2代表订阅 3代表拉黑）
   */
  relationshipType: string;

}

export interface UserRelationshipForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 用户id
   */
  userId?: string | number;

  /**
   * 关联用户
   */
  associatedUserId?: string | number;

  /**
   * 关系类型（1代表关注 2代表订阅 3代表拉黑）
   */
  relationshipType?: string;

}

export interface UserRelationshipQuery extends PageQuery {

  /**
   * 用户id
   */
  userId?: string | number;

  /**
   * 关联用户
   */
  associatedUserId?: string | number;

  /**
   * 关系类型（1代表关注 2代表订阅 3代表拉黑）
   */
  relationshipType?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



