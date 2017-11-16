package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.dto.OrderDTO;
import com.ximo.springbootsellmaster.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/15
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService{

    @Autowired
    private WxMpService wxMpService;

    /**
     * 订单状态变更消息
     *
     * @param orderDTO
     */
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        /*模板id*/
        wxMpTemplateMessage.setTemplateId("m8KKv7vc48u2TPm5jTBRISboFY4nDt4CWfNjCoovFNI");
        /*接受者的id*/
        wxMpTemplateMessage.setToUser("oc8RZ0Wc_rt6kUd14IkKsw6UsbsA");

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，记得收货"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "15088664612"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnums().getMsg()),
                new WxMpTemplateData("keyword5", String.valueOf("￥" + orderDTO.getBuyerAmount())),
                new WxMpTemplateData("remark", "欢迎下次购买QAQ")
        );
        wxMpTemplateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，e={}", e);

        }
    }
}
