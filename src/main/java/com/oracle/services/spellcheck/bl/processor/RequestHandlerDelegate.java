package com.oracle.services.spellcheck.bl.processor;


import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;
import com.oracle.services.spellcheck.config.GlobalConstants;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("requestHandlerDelegate")
public class RequestHandlerDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(RequestHandlerDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        solrQuery.setRequestHandler(GlobalConstants.SEARCH_HANDLER);
        logger.info("Set Request Handler");
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}
