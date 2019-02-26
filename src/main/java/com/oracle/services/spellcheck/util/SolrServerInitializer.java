package com.oracle.services.spellcheck.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.inject.Named;

@Named
public class SolrServerInitializer {
    private SolrClient solrClient;


    /*@Value("${service.zkEnsembleDestination}")
    private String zkEnsembleDestination;

    @Value("${service.collectionDestination}")
    private String collectionDestination;

    @Value("${service.zkTimeoutDestination}")
    private int zkTimeoutDestination;*/

    SolrServerInitializer() { }

    @Bean
    public SolrClient getSolrClient() {
        /*CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder()
                .withZkHost(zkEnsembleDestination)
                .build();
        cloudSolrClient.setDefaultCollection(collectionDestination);
        cloudSolrClient.setZkConnectTimeout(zkTimeoutDestination);
        this.solrClient = cloudSolrClient;*/

        SolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/collection2");

        this.solrClient = solrClient;
        return solrClient;
    }
}