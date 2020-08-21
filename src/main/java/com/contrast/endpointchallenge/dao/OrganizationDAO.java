package com.contrast.endpointchallenge.dao;


import lombok.*;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizationDAO {

    UUID id;
    String name;
    String industry;

}
