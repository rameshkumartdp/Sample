package com.aktway.services.core.bl;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;

public interface QueryService {
    ServiceResponse query(ServiceRequest serviceRequest) throws Exception;
    ServiceResponse queryAF(ServiceRequest serviceRequest) throws Exception;
}
