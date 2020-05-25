package com.damowang.storage.provider.service;

import com.damowang.storage.provider.dao.StorageMapper;
import com.damowang.storage.provider.domain.Storage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 描述:
 * 存储业务类
 *
 * @author sculi
 * @create 2020-05-25 11:42
 */
@Service
public class StorageService {
    @Resource
    private StorageMapper storageMapper;

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:Storage branch exception");
        }
        Storage  storage=new Storage();
        storage.setCommodityCode(commodityCode);
        storage=storageMapper.selectOne(storage);
        storage.setCount(storage.getCount()-count);
        storageMapper.updateByPrimaryKeySelective(storage);
    }
}
