package com.damowang.microservices.provider.service;

import com.damowang.microservices.provider.domain.Commodity;
import com.damowang.microservices.provider.domain.SgApp;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface CommodityService {
    long count();

    Commodity save(Commodity commodity);

    SgApp saveApp(SgApp sgApp);

    List<String> getBySgName(String name);

    void delete(Commodity commodity);

    Iterable<Commodity> getAll();

    List<Commodity> getByName(String name);

    //复杂查询
    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
