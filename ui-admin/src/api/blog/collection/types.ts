export interface CollectionVO {
  /**
   * 
   */
  id: string | number;

  /**
   * 合集名称
   */
  name: string;

  /**
   * 合集描述
   */
  describe: string;

  /**
   * 订阅标志（0为免费 1为订阅）
   */
  payFlag: string;

  /**
   * 从第节开始付费
   */
  payIndex: number;

}

export interface CollectionForm extends BaseEntity {
  /**
   * 
   */
  id?: string | number;

  /**
   * 合集名称
   */
  name?: string;

  /**
   * 合集描述
   */
  describe?: string;

  /**
   * 订阅标志（0为免费 1为订阅）
   */
  payFlag?: string;

  /**
   * 从第节开始付费
   */
  payIndex?: number;

}

export interface CollectionQuery extends PageQuery {

  /**
   * 合集名称
   */
  name?: string;

  /**
   * 合集描述
   */
  describe?: string;

  /**
   * 订阅标志（0为免费 1为订阅）
   */
  payFlag?: string;

  /**
   * 从第节开始付费
   */
  payIndex?: number;

    /**
     * 日期范围参数
     */
    params?: any;
}



