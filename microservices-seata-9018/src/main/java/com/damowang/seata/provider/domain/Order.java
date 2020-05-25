package com.damowang.seata.provider.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 描述:
 * 订单实体类
 *
 * @author sculi
 * @create 2020-05-25 13:52
 */
@Entity
@Table(name = "order_tbl")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "commodity_code")
    private String commodityCode;

    private Integer count;

    private BigDecimal money;
}
