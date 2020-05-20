package com.damowang.microservices.provider.dao;

import com.damowang.microservices.provider.domain.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, Long> {

}
