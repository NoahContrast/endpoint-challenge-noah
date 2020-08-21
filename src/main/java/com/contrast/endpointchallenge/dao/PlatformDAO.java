package com.contrast.endpointchallenge.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlatformDAO {

    private UUID id;
    private String name;

}
