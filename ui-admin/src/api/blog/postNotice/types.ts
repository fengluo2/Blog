export interface PostNoticeVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 文章id
   */
  postId: string | number;

  /**
   * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
   */
  noticeType: string;

  /**
   * 通知id（点赞id、收藏id、评论id、回复id）
   */
  noticeId: string | number;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag: string;

}

export interface PostNoticeForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 文章id
   */
  postId?: string | number;

  /**
   * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
   */
  noticeType?: string;

  /**
   * 通知id（点赞id、收藏id、评论id、回复id）
   */
  noticeId?: string | number;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag?: string;

}

export interface PostNoticeQuery extends PageQuery {

  /**
   * 文章id
   */
  postId?: string | number;

  /**
   * 通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）
   */
  noticeType?: string;

  /**
   * 通知id（点赞id、收藏id、评论id、回复id）
   */
  noticeId?: string | number;

  /**
   * 已读标志（0代表未读 1代表已读）
   */
  readFlag?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



