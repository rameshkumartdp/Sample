package com.sears.services.commercial.bl.processor;


import com.sears.services.commercial.config.GlobalConstants;
import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
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
