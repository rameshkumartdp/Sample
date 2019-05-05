package com.aktway.services.core.bl.impl;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import com.aktway.services.core.bl.QueryService;
import com.aktway.services.core.bl.processor.Delegate;
import com.aktway.services.core.bl.task.QueryCommand;
import com.aktway.services.core.util.LogUtil;
import com.aktway.services.core.util.SolrUtil;
import com.aktway.services.core.bl.DelegateInitializer;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Named("queryService")
public class QueryServiceImpl implements QueryService {
    private static Logger logger = Logger.getLogger(QueryServiceImpl.class.getName());
    @Inject
    DelegateInitializer delegateInitializer;
    @Value("${service.solrQueryTimeout}")
    private long solrQueryTimeout;
    @Inject
    private QueryCommand queryCommand;

    @Cacheable(cacheNames = "default", key = "#serviceRequest.cacheKey", condition = "#serviceRequest.from != T(com.sears.search.service.api.response.From).INDEX", unless = "T(com.aktway.services.core.util.MiscUtil).isValidResponse(#result) == false")
    public ServiceResponse query(ServiceRequest serviceRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info(serviceRequest);
        serviceRequest.setRound(serviceRequest.getRound() + 1);

        Map<String, List<Delegate>> delegateMapList = delegateInitializer.buildDelegateMapList(serviceRequest);
        return getServiceResponse(serviceRequest, startTime, delegateMapList);

    }

    @Override
    @Cacheable(cacheNames = "default", key = "#serviceRequest.cacheKey", condition = "#serviceRequest.from != T(com.sears.search.service.api.response.From).INDEX", unless = "T(com.aktway.services.core.util.MiscUtil).isValidResponse(#result) == false")
    public ServiceResponse queryAF(ServiceRequest serviceRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info(serviceRequest);

        Map<String, List<Delegate>> delegateMapList = delegateInitializer.buildAFDelegateMapList(serviceRequest);
        return getServiceResponse(serviceRequest, startTime, delegateMapList);
    }

    private ServiceResponse getServiceResponse(ServiceRequest serviceRequest, long startTime, Map<String, List<Delegate>> delegateMapList) throws Exception {
        Map<String, SolrQuery> solrQueryMap = new HashMap<>();
        for (String key : delegateMapList.keySet()) {
            SolrQuery solrQuery = new SolrQuery();
            for (Delegate delegate : delegateMapList.get(key)) {
                delegate.preProcessQuery(solrQuery, serviceRequest);
            }
            solrQueryMap.put(key, solrQuery);
        }
        Map<String, Future<QueryResponse>> futureMap = submitQueries(solrQueryMap);

        ServiceResponse serviceResponse = new ServiceResponse();
        for (String key : delegateMapList.keySet()) {
            QueryResponse queryResponse = SolrUtil.getQueryResponse(futureMap, key, solrQueryTimeout);
            if (queryResponse == null) {
                continue;
            }
            for (Delegate delegate : delegateMapList.get(key)) {
                delegate.postProcessResult(serviceRequest, queryResponse, serviceResponse);
            }
        }
        LogUtil.logTotalTimeTaken(logger, "QueryService", startTime);
        return serviceResponse;
    }


    public Map<String, Future<QueryResponse>> submitQueries(Map<String, SolrQuery> solrQueryMap) throws Exception {
        Map<String, Future<QueryResponse>> toReturn = new HashMap<>();
        for (String key : solrQueryMap.keySet()) {
            toReturn.put(key, queryCommand.run(solrQueryMap.get(key)));
        }
        return toReturn;
    }


}
