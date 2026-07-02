package com.xyf.task;

import com.xyf.entity.Orders;
import com.xyf.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    @Scheduled(cron = "0 * * * * ?")
    //测试5秒一次
    //@Scheduled(fixedRate = 5000)
    public void removeTimeoutOrders(){
        log.info("定时处理超时订单:{}", LocalDateTime.now());
        LocalDateTime time=LocalDateTime.now().plusMinutes(-15);
        List<Orders> orderList = orderMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT, time);
        if(!orderList.isEmpty()&&orderList.size()>0){
            for(Orders order : orderList){
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    //@Scheduled(fixedRate = 5000)
    public void processDeliveryOrder(){
        log.info("定时处理派送中的订单:{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> orderList = orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time);
        if(!orderList.isEmpty()&&orderList.size()>0){
            for(Orders order : orderList){
                order.setStatus(Orders.COMPLETED);
                order.setCancelReason("订单超时");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }

    }


}
