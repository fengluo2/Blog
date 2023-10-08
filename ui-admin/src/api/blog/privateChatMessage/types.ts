export interface PrivateChatMessageVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 私聊id
   */
  chatId: string | number;

  /**
   * 发送者id
   */
  senderId: string | number;

  /**
   * 接收者id
   */
  receiverId: string | number;

  /**
   * 消息内容
   */
  content: string;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag: string;

}

export interface PrivateChatMessageForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 私聊id
   */
  chatId?: string | number;

  /**
   * 发送者id
   */
  senderId?: string | number;

  /**
   * 接收者id
   */
  receiverId?: string | number;

  /**
   * 消息内容
   */
  content?: string;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag?: string;

}

export interface PrivateChatMessageQuery extends PageQuery {

  /**
   * 私聊id
   */
  chatId?: string | number;

  /**
   * 发送者id
   */
  senderId?: string | number;

  /**
   * 接收者id
   */
  receiverId?: string | number;

  /**
   * 消息内容
   */
  content?: string;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



