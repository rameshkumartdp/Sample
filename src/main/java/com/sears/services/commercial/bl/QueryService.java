package com.sears.services.commercial.bl;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;

public interface QueryService {
    ServiceResponse query(ServiceRequest serviceRequest) throws Exception;
    ServiceResponse queryAF(ServiceRequest serviceRequest) throws Exception;
}
