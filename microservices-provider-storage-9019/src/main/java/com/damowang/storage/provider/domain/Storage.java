package com.damowang.storage.provider.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述:
 * 实体存储类
 *
 * @author sculi
 * @create 2020-05-24 17:13
 */
@Entity
@Table(name = "storage_tbl")
@Data
public class Storage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commodity_code")
    private String commodityCode;

    private Integer count;
}
