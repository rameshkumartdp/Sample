package com.sears.services.commercial.bl.processor;

import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("queryTermDelegate")
public class QueryTermDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(QueryTermDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        solrQuery.setQuery(serviceRequest.getQ());
        logger.info("Set Query");
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}

