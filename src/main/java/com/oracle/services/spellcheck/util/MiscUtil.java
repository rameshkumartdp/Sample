package com.oracle.services.spellcheck.util;

import com.oracle.services.spellcheck.api.response.ServiceResponse;

public class MiscUtil {
    public static boolean isValidResponse(ServiceResponse serviceResponse) {
        return serviceResponse != null && serviceResponse.getProducts() != null && serviceResponse.getProducts().size() > 0;
    }
}
