package com.aktway.services.core.util;

import com.aktway.services.core.api.response.ServiceResponse;

public class MiscUtil {
    public static boolean isValidResponse(ServiceResponse serviceResponse) {
        return serviceResponse != null && serviceResponse.getProducts() != null && serviceResponse.getProducts().size() > 0;
    }
}