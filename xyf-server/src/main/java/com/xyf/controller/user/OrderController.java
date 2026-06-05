package com.xyf.controller.user;

import com.xyf.context.BaseContext;
import com.xyf.dto.OrdersPageQueryDTO;
import com.xyf.dto.OrdersSubmitDTO;
import com.xyf.result.PageResult;
import com.xyf.result.Result;
import com.xyf.service.OrderService;
import com.xyf.vo.OrderSubmitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 用户下单
     *
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户下单：{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 查询历史订单
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("/historyOrders")
    public Result<PageResult> historyOrders(int page, int pageSize, Integer status) {
        log.info("查询历史订单：page={}, pageSize={}, status={}", page, pageSize, status);
        
        OrdersPageQueryDTO ordersPageQueryDTO = new OrdersPageQueryDTO();
        ordersPageQueryDTO.setPage(page);
        ordersPageQueryDTO.setPageSize(pageSize);
        ordersPageQueryDTO.setStatus(status);
        // 设置当前用户ID
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    // 注意：由于下单时已直接设置为已支付状态，此接口仅用于兼容前端调用
    // 实际不做任何操作，直接返回成功
    @PutMapping("/payment")
    public Result payment() {
        log.info("前端调用支付接口（已废弃，下单时已自动支付）");
        return Result.success();
    }
}
