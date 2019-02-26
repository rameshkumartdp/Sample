package com.oracle.services.spellcheck.api.response;

import com.oracle.services.spellcheck.api.request.ServiceRequest;

import java.io.Serializable;
import java.util.List;

public class Debug implements Serializable {
    private String gitInformation;
    private List<String> queries;
    private int round;
    private ServiceRequest serviceRequest;

    public String getGitInformation() {
        return gitInformation;
    }

    public void setGitInformation(String gitInformation) {
        this.gitInformation = gitInformation;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }
}
