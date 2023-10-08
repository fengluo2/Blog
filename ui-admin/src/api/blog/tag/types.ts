export interface TagVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 标签名
   */
  name: string;

}

export interface TagForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 标签名
   */
  name?: string;

}

export interface TagQuery extends PageQuery {

  /**
   * 标签名
   */
  name?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



