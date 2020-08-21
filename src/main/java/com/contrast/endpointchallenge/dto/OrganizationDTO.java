package com.contrast.endpointchallenge.dto;


import lombok.*;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizationDTO {

    private UUID orgId;
    private String orgName;
    private String orgIndustry;

}
