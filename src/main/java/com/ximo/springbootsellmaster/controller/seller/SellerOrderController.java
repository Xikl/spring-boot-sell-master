package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 卖家操作类
 * Created by 朱文赵
 * 2017/9/19
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     *
     * @param page 第几页 从第一页开始
     * @param size 每页显示多少数据
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        model.addAttribute("orderDTOPage", orderDTOPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "order/list";
    }

    /**
     * 卖家取消订单的操作
     *
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId,
                         Model model) {
        //查找该orderDTO
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【买家取消订单】 查询不到该订单");
            model.addAttribute("msg", ResultEnums.ORDER_NOT_EXIST.getMsg());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }
        orderService.cancel(orderDTO);
        return "sth";
    }


}
