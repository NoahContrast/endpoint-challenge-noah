package com.contrast.endpointchallenge.givens.util;

import org.apache.commons.lang3.RandomStringUtils;

public class AnyJavaLang {

    public static String anyString() {
        return anyString(20);
    }

    public static String anyString(int length) {
        return RandomStringUtils.random(length, true, false);
    }

}
