package com.oracle.services.spellcheck.bl;


import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;

public interface QueryService {
    ServiceResponse query(ServiceRequest serviceRequest) throws Exception;
    ServiceResponse queryAF(ServiceRequest serviceRequest) throws Exception;
}
