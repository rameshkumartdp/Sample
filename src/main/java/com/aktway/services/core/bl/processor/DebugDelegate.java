package com.aktway.services.core.bl.processor;

import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import com.aktway.services.core.api.response.Debug;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("debugDelegate")
public class DebugDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(DebugDelegate.class.getName());

    private SolrQuery solrQuery;
    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        logger.info("Debug enabled");
        this.solrQuery = solrQuery;
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        Debug debug = serviceResponse.getDebug();
        if(debug == null) {
            debug = new Debug();
        }
        List<String> queries = debug.getQueries();
        if(queries == null) {
            queries = new ArrayList<>();
            debug.setQueries(queries);
        }
        queries.add(solrQuery.toString());
        debug.setRound(serviceRequest.getRound());
        debug.setServiceRequest(serviceRequest);
        serviceResponse.setDebug(debug);
        return serviceResponse;
    }
}
