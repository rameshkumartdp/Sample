package com.aktway.services.core.util;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by rames on 26-02-2019.
 */
public class SolrUtil {
    private static Logger logger = Logger.getLogger(SolrUtil.class.getName());

    public SolrUtil() {
    }

    public static QueryResponse runSolrCommand(SolrClient solrClient, SolrQuery solrQuery) throws RuntimeException {
        QueryResponse queryResponse = null;

        try {
            long solrServerException = System.currentTimeMillis();
            queryResponse = solrClient.query(solrQuery, SolrRequest.METHOD.POST);
            long endTime = System.currentTimeMillis();
            logger.info("For query " + solrQuery + " Total Query Time " + (endTime - solrServerException) + " milli seconds");
            return queryResponse;
        } catch (IOException | SolrServerException var7) {
            logger.error("Could not execute solr query " + solrQuery);
            logger.error(var7);
            throw new RuntimeException(var7);
        }
    }

    public static QueryResponse getFallback() {
        logger.error("Going to fallback. Empty query response ");
        return new QueryResponse();
    }

    public static QueryResponse getQueryResponse(Map<String, Future<QueryResponse>> futureMap, String key, long timeout) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            QueryResponse queryResponse = (QueryResponse)((Future)futureMap.get(key)).get(timeout, TimeUnit.MILLISECONDS);
            return queryResponse;
        } catch (TimeoutException var6) {
            ((Future)futureMap.get(key)).cancel(true);
            throw var6;
        }
    }
}