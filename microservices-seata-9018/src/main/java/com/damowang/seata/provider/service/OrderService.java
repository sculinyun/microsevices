package com.damowang.seata.provider.service;

import com.damowang.seata.provider.dao.OrderMapper;
import com.damowang.seata.provider.domain.Order;
import com.damowang.seata.provider.fegin.StorageFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 描述:
 * 存储业务类
 *
 * @author sculi
 * @create 2020-05-25 11:42
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignClient storageFeignClient;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.insert(order);
        storageFeignClient.deduct(commodityCode, count);
    }

}
