package com.contrast.endpointchallenge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationDTO {

    UUID appId;
    String appName;
    String appDescription;
    PlatformDTO appPlatform;

}
