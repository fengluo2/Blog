export interface PostVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 标题
   */
  title: string;

  /**
   * 简介
   */
  contentShort: string;

  /**
   * 内容
   */
  content: string;

  /**
   * 作者署名
   */
  authorSignature: string;

  /**
   * 权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）
   */
  levelFlag: string;

}

export interface PostForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 标题
   */
  title?: string;

  /**
   * 简介
   */
  contentShort?: string;

  /**
   * 内容
   */
  content?: string;

  /**
   * 作者署名
   */
  authorSignature?: string;

  /**
   * 权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）
   */
  levelFlag?: string;

}

export interface PostQuery extends PageQuery {

  /**
   * 标题
   */
  title?: string;

  /**
   * 简介
   */
  contentShort?: string;

  /**
   * 内容
   */
  content?: string;

  /**
   * 作者署名
   */
  authorSignature?: string;

  /**
   * 权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）
   */
  levelFlag?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



