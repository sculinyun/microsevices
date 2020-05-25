package com.damowang.storage.provider.controller;

import com.damowang.storage.provider.service.StorageService;
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
@RequestMapping("storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 减库存
     * @param commodityCode 商品代码
     * @param count 数量
     * @return
     */
    @RequestMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        storageService.deduct(commodityCode, count);
        return true;
    }

}
