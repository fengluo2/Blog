export interface SystemNotificationReadVO {
  /**
   * 系统通知id
   */
  systemNotificationId: string | number;

  /**
   * 用户id
   */
  userId: string | number;

}

export interface SystemNotificationReadForm extends BaseEntity {
  /**
   * 系统通知id
   */
  systemNotificationId?: string | number;

  /**
   * 用户id
   */
  userId?: string | number;

}

export interface SystemNotificationReadQuery extends PageQuery {

    /**
     * 日期范围参数
     */
    params?: any;
}



