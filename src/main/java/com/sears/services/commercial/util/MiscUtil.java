package com.sears.services.commercial.util;

import com.sears.services.commercial.api.response.ServiceResponse;

public class MiscUtil {
    public static boolean isValidResponse(ServiceResponse serviceResponse) {
        return serviceResponse != null && serviceResponse.getProducts() != null && serviceResponse.getProducts().size() > 0;
    }
}
