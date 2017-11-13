package com.ximo.springbootsellmaster.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/13
 */
@Data
@Entity
@NoArgsConstructor
@DynamicUpdate
public class SellerInfo {

    /** 卖家id*/
    @Id
    private String sellerId;

    /** 用户名*/
    private String username;

    /** 密码*/
    private String password;

    /** 微信id*/
    private String openid;

    /** 创建时间*/
    private Date createTime;

    /**修改时间*/
    private Date updateTime;

}
