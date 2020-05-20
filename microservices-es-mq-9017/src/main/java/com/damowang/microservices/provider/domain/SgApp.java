package com.damowang.microservices.provider.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * 描述:
 * 商品domain
 *
 * @author sculi
 * @create 2020-05-14 1:19
 */
@Data
@Document(indexName = "sgapp",type = "commodity",refreshInterval = "-1")
@Mapping(mappingPath = "/config/sg_mapping.json")
@Setting(settingPath = "/config/setting.json")
public class SgApp implements Serializable {
    @Id
    private Long skuId;

    //测试text
    private String commodityName;
}
