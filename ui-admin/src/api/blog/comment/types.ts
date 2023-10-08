export interface CommentVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 回复id（0代表1级评论）
   */
  replyId: string | number;

  /**
   * 评论级别
   */
  level: number;

  /**
   * 文章id
   */
  postId: string | number;

  /**
   * 评论内容
   */
  content: string;

  /**
   * 主评论标志（0代表不是 1代表是）
   */
  masterFlag: string;

}

export interface CommentForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 回复id（0代表1级评论）
   */
  replyId?: string | number;

  /**
   * 评论级别
   */
  level?: number;

  /**
   * 文章id
   */
  postId?: string | number;

  /**
   * 评论内容
   */
  content?: string;

  /**
   * 主评论标志（0代表不是 1代表是）
   */
  masterFlag?: string;

}

export interface CommentQuery extends PageQuery {

  /**
   * 回复id（0代表1级评论）
   */
  replyId?: string | number;

  /**
   * 评论级别
   */
  level?: number;

  /**
   * 文章id
   */
  postId?: string | number;

  /**
   * 评论内容
   */
  content?: string;

  /**
   * 主评论标志（0代表不是 1代表是）
   */
  masterFlag?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



