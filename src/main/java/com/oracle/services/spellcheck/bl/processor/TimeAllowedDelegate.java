package com.oracle.services.spellcheck.bl.processor;

import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;
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