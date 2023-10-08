export interface PrivateChatVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 用户Aid
   */
  userAId: string | number;

  /**
   * 用户Bid
   */
  userBId: string | number;

  /**
   * 用户A未读消息数
   */
  userAUnreadCount: number;

  /**
   * 用户B未读消息数
   */
  userBUnreadCount: number;

}

export interface PrivateChatForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 用户Aid
   */
  userAId?: string | number;

  /**
   * 用户Bid
   */
  userBId?: string | number;

  /**
   * 用户A未读消息数
   */
  userAUnreadCount?: number;

  /**
   * 用户B未读消息数
   */
  userBUnreadCount?: number;

}

export interface PrivateChatQuery extends PageQuery {

  /**
   * 用户Aid
   */
  userAId?: string | number;

  /**
   * 用户Bid
   */
  userBId?: string | number;

  /**
   * 用户A未读消息数
   */
  userAUnreadCount?: number;

  /**
   * 用户B未读消息数
   */
  userBUnreadCount?: number;

    /**
     * 日期范围参数
     */
    params?: any;
}



