package com.oracle.services.spellcheck.response;

import java.io.Serializable;
import com.oracle.services.spellcheck.config.GlobalConstants;

/**
 * Created by rames on 26-02-2019.
 */
public enum From implements Serializable {

    INDEX,
    CACHE,
    DEFAULT;
}
