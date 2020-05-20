package com.damowang.microservices.provider.controller;

import com.damowang.microservices.provider.domain.Commodity;
import com.damowang.microservices.provider.domain.SgApp;
import com.damowang.microservices.provider.service.CommodityService;
import lykj.base.BaseController;
import lykj.base.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 * es电商操作
 *
 * @author sculi
 * @create 2020-05-15 12:13
 */
@RestController
public class EsController extends BaseController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result count() {
        long count = commodityService.count();
        return bulidSuccessResult(count);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Result save() {
//        Commodity commodity1=new Commodity();
//        commodity1.setSkuId(1L);
//        commodity1.setBrand("美的");
//        commodity1.setCategory("电器");
//        commodity1.setCommodityName("银离子抗菌】美的一级节能变频空调智能家用大1.5匹挂机壁挂式MHA");
//        commodity1.setCreateDate("2017-01-07");
//        commodity1.setPrice(new BigDecimal("3599.12"));
//        commodity1.setStock(6672);
//        commodityService.save(commodity1);
//        Commodity commodity2=new Commodity();
//        commodity2.setSkuId(2L);
//        commodity2.setBrand("罗技");
//        commodity2.setCategory("鼠标");
//        commodity2.setCommodityName("罗技M330静音无线鼠标办公游戏笔记本电脑");
//        commodity2.setCreateDate("2019-05-07");
//        commodity2.setPrice(new BigDecimal("180.00"));
//        commodity2.setStock(73177);
//        commodityService.save(commodity2);
//        Commodity commodity3=new Commodity();
//        commodity3.setSkuId(4L);
//        commodity3.setBrand("罗技4");
//        commodity3.setCategory("键盘4");
//        commodity3.setCommodityName("罗技44MK235无线键盘鼠标键鼠套装办公笔记本电脑USB");
//        commodity3.setCreateDate("2019-06-20");
//        commodity3.setPrice(new BigDecimal("321.00"));
//        commodity3.setStock(3100);
//        commodityService.save(commodity3);

        SgApp sgApp=new SgApp();
        sgApp.setSkuId(1L);
        sgApp.setCommodityName("罗技44MK235无线键盘鼠标键鼠套装办公笔记本电脑USB");
        commodityService.saveApp(sgApp);
        return bulidSuccessResult(null);
    }

    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public Result queryByName(@RequestParam(value = "name") String name) {
        List<Commodity> commodityList=commodityService.getByName(name);
        return bulidSuccessResult(commodityList);
    }

    @RequestMapping(value = "/queryBySorce", method = RequestMethod.GET)
    public Result queryByScore(@RequestParam(value = "name") String name) {
        int pageNo=1;
        int pageSize=10;
        Page<Commodity> commodityList=commodityService.pageQuery(pageNo,pageSize,name);
        return bulidSuccessResult(commodityList);
    }
}
