package com.contrast.endpointchallenge.util;

public class EndpointConstants {

    public static final String ID = "id";
    public static final String UUID_PLACEHOLDER = "/{" + ID + "}";
    public static final String ORGANIZATIONS = "/organizations";
    public static final String APPLICATIONS = "/applications";
    public static final String ORGANIZATION = ORGANIZATIONS + UUID_PLACEHOLDER;
    public static final String ORGANIZATION_APPLICATIONS = ORGANIZATION + APPLICATIONS;

}
