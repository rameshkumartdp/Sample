package com.aktway.services.core.bl.processor;

import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("timeAllowedDelegate")
public class TimeAllowedDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(TimeAllowedDelegate.class.getName());

    @Value("${service.queryTimeAllowed}")
    private int queryTimeAllowed;

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        solrQuery.setTimeAllowed(queryTimeAllowed);
        logger.info("Added Time Allowed");
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}