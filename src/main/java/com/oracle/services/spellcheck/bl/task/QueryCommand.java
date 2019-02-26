package com.oracle.services.spellcheck.bl.task;


import com.codahale.metrics.annotation.Timed;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.oracle.services.spellcheck.util.SolrUtil;
import com.oracle.services.spellcheck.config.GlobalConstants;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.Future;

@Named
public class QueryCommand {
    private static Logger logger = Logger.getLogger(QueryCommand.class.getName());
    private static final String SOLR_REQUEST = "commercial.solr." + GlobalConstants.REQUEST;
    public static final QueryResponse FALLBACK_QUERY_RESPONSE = SolrUtil.getFallback();
    @Inject
    private SolrClient solrClient;

    public QueryCommand() {
        logger.info("Empty Constructor");
    }

    @Timed(absolute = true, name = SOLR_REQUEST)
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "solrCommandKey", threadPoolKey = "solrThreadPoolKey", fallbackMethod = "getFallback")
    public Future<QueryResponse> run(SolrQuery solrQuery) throws Exception {
        return
                new AsyncResult<QueryResponse>() {
                    @Override
                    public QueryResponse invoke() {
                        return SolrUtil.runSolrCommand(solrClient, solrQuery);
                    }
                };
    }

    @SuppressWarnings("ALL")
    public QueryResponse getFallback(SolrQuery solrQuery) {
        return FALLBACK_QUERY_RESPONSE;
    }


}
