package com.ximo.springbootsellmaster.converter;

import com.ximo.springbootsellmaster.domain.OrderMaster;
import com.ximo.springbootsellmaster.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单主表转化为orderDTO的转换器
 * Created by 朱文赵
 * 2017/9/11
 */
public class OrderMaster2OrderDTOConverter {

    /**
     * 将orderMaster转化为orderDTO对象
     * @param orderMaster 订单主表对象
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * list转换器
     * @param orderMasterList 订单主表list对象
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream()
                .map(OrderMaster2OrderDTOConverter::convert)
                .collect(Collectors.toList());
    }


}
