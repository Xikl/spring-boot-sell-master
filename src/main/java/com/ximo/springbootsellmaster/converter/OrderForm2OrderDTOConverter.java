package com.ximo.springbootsellmaster.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ximo.springbootsellmaster.domain.OrderDetail;
import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱文赵
 * 2017/9/12
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    /**
     * 将orderForm的数据转为OrderDTO的
     * @param orderForm 前端表单数据
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm){
        //使用google的json数据转换
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】json格式转换错误， String={}", orderForm.getItems());
            throw new SellException(ResultEnums.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
