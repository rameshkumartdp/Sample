package com.aktway.services.core.bl.processor;

import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
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
