package com.damowang.seata.provider.controller;

import com.damowang.seata.provider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 存储controller
 *
 * @author sculi
 * @create 2020-05-24 17:05
 */

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/placeOrder/commit")
    public Boolean placeOrderCommit() {
        orderService.placeOrder("1", "product-1", 1);
        return true;
    }

    @RequestMapping("/placeOrder/rollback")
    public Boolean placeOrderRollback() {
        // product-2 扣库存时模拟了一个业务异常,
        orderService.placeOrder("1", "product-2", 1);
        return true;
    }
}
