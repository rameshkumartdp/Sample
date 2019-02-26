package com.oracle.services.spellcheck.bl.processor;

import com.oracle.services.spellcheck.api.response.ServiceResponse;
import com.oracle.services.spellcheck.api.request.ServiceRequest;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("rowsDelegate")
public class RowsDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(RowsDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        if (serviceRequest.getRows() != 0) {
            solrQuery.setRows(serviceRequest.getRows());
        }
        logger.info("Rows Added");
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}