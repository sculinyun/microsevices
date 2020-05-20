package com.damowang.microservices.provider.dao;

import com.damowang.microservices.provider.domain.SgApp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SgAppRepository extends ElasticsearchRepository<SgApp, Long> {

}
