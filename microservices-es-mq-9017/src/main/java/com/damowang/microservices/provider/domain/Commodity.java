package com.damowang.microservices.provider.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述:
 * 商品domain
 *
 * @author sculi
 * @create 2020-05-14 1:19
 */
@Data
@Document(indexName = "app0522",type = "commodity",refreshInterval = "-1")
@Mapping(mappingPath = "/config/mapping.json")
@Setting(settingPath = "/config/setting.json")
public class Commodity implements Serializable {
    @Id
    private Long skuId;

    //测试text
    private String commodityName;

    private String category;

    //测试浮点型数据
    private BigDecimal price;

    //测试keyword
    private String brand;

    //测试时间
    private String createDate;

    //测试整形数据
    private Integer stock;
}
