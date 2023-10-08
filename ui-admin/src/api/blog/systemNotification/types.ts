export interface SystemNotificationVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 标题
   */
  title: string;

  /**
   * 内容
   */
  content: string;

  /**
   * 可点击标志（0代表未读 1代表可点击）
   */
  click: string;

}

export interface SystemNotificationForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 标题
   */
  title?: string;

  /**
   * 内容
   */
  content?: string;

  /**
   * 可点击标志（0代表未读 1代表可点击）
   */
  click?: string;

}

export interface SystemNotificationQuery extends PageQuery {

  /**
   * 标题
   */
  title?: string;

  /**
   * 内容
   */
  content?: string;

  /**
   * 可点击标志（0代表未读 1代表可点击）
   */
  click?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



