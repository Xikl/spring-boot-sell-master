package com.ximo.springbootsellmaster.controller.seller;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import com.ximo.springbootsellmaster.service.OrderService;
import com.ximo.springbootsellmaster.util.ModelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        /*排序 降序*/
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(page - 1, size, sort);
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
    @GetMapping("/cancel/{orderId}")
    public String cancel(@PathVariable("orderId") String orderId,
                         Model model) {
        //查找该orderDTO
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【买家取消订单】 发生异常{}", e);
            //采用自己封装的util类
            return ModelUtil.error(model, e);
        }
        return ModelUtil.success(model, ResultEnums.ORDER_CANCEL_SUCCESS.getMsg());
    }

    /**
     * 订单详情
     * 采用RESTful的url形式
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") String orderId,
                         Model model) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【买家查询订单详情】 发生异常{}", e);
            return ModelUtil.error(model, e);
        }
        model.addAttribute("orderDTO", orderDTO);
        return "order/detail";
    }

    /**
     * 完结订单
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/finish/{orderId}")
    public String finish(@PathVariable("orderId") String orderId,
                         Model model){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【买家完结订单详情】 发生异常{}", e);
            return ModelUtil.error(model, e);
        }
        return ModelUtil.success(model, ResultEnums.ORDER_FINISH_SUCCESS.getMsg());
    }


}
