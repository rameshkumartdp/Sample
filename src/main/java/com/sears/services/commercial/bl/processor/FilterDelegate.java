package com.sears.services.commercial.bl.processor;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@Named("filterDelegate")
public class FilterDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(FilterDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        if(StringUtils.isNotEmpty(serviceRequest.getFq())) {
            solrQuery.setFilterQueries(serviceRequest.getFq());
            logger.info("Set Filter Queries");
        }
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}

