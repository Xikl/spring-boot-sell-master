package com.ximo.springbootsellmaster.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目表
 * Created by 朱文赵
 * 2017/9/9
 */
@Entity
@Data
@DynamicUpdate//动态更新
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    /** 类目id */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目类型 */
    private Integer categoryType;

    /** 类目创建时间 */
    private Date createTime;

    /** 类目更新时间 */
    private Date updateTime;
}
