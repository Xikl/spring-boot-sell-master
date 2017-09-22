package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表的仓库类
 * Created by 朱文赵
 * 2017/9/10
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 按照卖家的微信id来查询出他的所有的订单
     * @param buyerOpenid 微信id
     * @param pageable 分页对象
     * @return 该用户的所有的订单
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
