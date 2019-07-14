package com.aktway.services.core.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
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

        //SolrClient solrClient = new HttpSolrClient("http://ec2-18-222-165-251.us-east-2.compute.amazonaws.com:8983/solr/activity");
        SolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/activity");

        this.solrClient = solrClient;
        return solrClient;
    }
}