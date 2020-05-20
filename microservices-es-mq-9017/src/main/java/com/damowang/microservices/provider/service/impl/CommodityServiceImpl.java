package com.damowang.microservices.provider.service.impl;

import com.damowang.microservices.provider.dao.CommodityRepository;
import com.damowang.microservices.provider.dao.SgAppRepository;
import com.damowang.microservices.provider.domain.Commodity;
import com.damowang.microservices.provider.domain.SgApp;
import com.damowang.microservices.provider.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.WeightBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述:
 * es商品相关实现
 *
 * @author sculi
 * @create 2020-05-14 1:28
 */
@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private SgAppRepository sgAppRepository;
    @Autowired
    private ElasticsearchTemplate template;

    @Override
    public long count() {
        return commodityRepository.count();
    }

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    @Override
    public void delete(Commodity commodity) {

    }

    @Override
    public Iterable<Commodity> getAll() {
        return null;
    }

    @Override
    public List<Commodity> getByName(String name) {
        MatchQueryBuilder queryBuilder= QueryBuilders.matchQuery("commodityName",name);
        log.info("commodity query string:{}",queryBuilder.toString());
        Page<Commodity> commoditys = (Page<Commodity>) commodityRepository.search(queryBuilder);
//        Iterator<Commodity> iterator = commoditys.iterator();
//        List<Commodity> list = new ArrayList<>();
//        while (iterator.hasNext()){
//            list.add(iterator.next());
//      }
        return commoditys.getContent();
    }

    @Override
    public Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //搜索必须包含的字段kw
        queryBuilder.must(QueryBuilders.matchQuery("commodityName", kw));

        //几个过滤条件
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[2];
        //过滤条件1：分类电器最重要 -- 权重查询Weight
        ScoreFunctionBuilder<WeightBuilder> scoreFunctionBuilder = new WeightBuilder();
        scoreFunctionBuilder.setWeight(2);
        QueryBuilder termQuery = QueryBuilders.termQuery("category", "电器");
        FunctionScoreQueryBuilder.FilterFunctionBuilder category = new FunctionScoreQueryBuilder.FilterFunctionBuilder(termQuery, scoreFunctionBuilder);
        filterFunctionBuilders[0] = category;

        // 过滤条件2：销量越高越排前 --计分查询 FieldValueFactor
        ScoreFunctionBuilder<FieldValueFactorFunctionBuilder> fieldValueScoreFunction = new FieldValueFactorFunctionBuilder("stock");
        ((FieldValueFactorFunctionBuilder) fieldValueScoreFunction).factor(0.1f);
        FunctionScoreQueryBuilder.FilterFunctionBuilder salesStock = new FunctionScoreQueryBuilder.FilterFunctionBuilder(fieldValueScoreFunction);
        filterFunctionBuilders[1] = salesStock;

        FunctionScoreQueryBuilder finalQuery = QueryBuilders.functionScoreQuery(queryBuilder, filterFunctionBuilders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
        Sort sort = Sort.by(Sort.Direction.DESC,"_score");
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Commodity> commoditys = (Page<Commodity>) commodityRepository.search(finalQuery,pageable);
        return commoditys;
    }

    @Override
    public SgApp saveApp(SgApp sgApp) {
        return sgAppRepository.save(sgApp);
    }

    @Override
    public List<String> getBySgName(String name) {
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        CompletionSuggestionBuilder prefix= SuggestBuilders.completionSuggestion("commodityName").prefix(name).skipDuplicates(true);
        suggestBuilder.addSuggestion("sg_suggestion",prefix);
        SearchResponse response = template.suggest(suggestBuilder,SgApp.class);
        Suggest suggest = response.getSuggest();//suggest实体

        Set<String> suggestSet = new HashSet<>();
        int suggestMaxCount = 10;
        int maxSuggest=0;
        if (suggest != null) {
            Suggest.Suggestion result = suggest.getSuggestion("student_suggest");//获取suggest,name任意string
            for (Object term : result.getEntries()) {
                if (term instanceof CompletionSuggestion.Entry) {
                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                    if (!item.getOptions().isEmpty()) {
                        //若item的option不为空,循环遍历
                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                            String tip = option.getText().toString();
                            if (!suggestSet.contains(tip)) {
                                suggestSet.add(tip);
                                ++maxSuggest;
                            }
                        }
                    }
                }
                if (maxSuggest >= suggestMaxCount) {
                    break;
                }
            }
        }
        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));
        return suggests;
    }
}
