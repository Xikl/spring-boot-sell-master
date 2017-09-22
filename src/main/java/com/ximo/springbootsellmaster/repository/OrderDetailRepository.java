package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 朱文赵
 * 2017/9/10
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

    /**
     * 根据订单id来查询
     * @param orderId 订单id
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
