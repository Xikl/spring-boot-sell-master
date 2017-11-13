package com.ximo.springbootsellmaster.form;

import lombok.Data;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@Data
public class CategoryForm {

    /** 类目id*/
    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目类型 */
    private Integer categoryType;

}
