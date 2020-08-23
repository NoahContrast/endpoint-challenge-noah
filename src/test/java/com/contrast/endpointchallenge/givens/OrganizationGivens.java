package com.contrast.endpointchallenge.givens;

import com.contrast.endpointchallenge.dto.OrganizationDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.contrast.endpointchallenge.util.AnyJavaLang.anyString;

public class OrganizationGivens {

    public static OrganizationDTO anyOrganizationDTO() {
        return OrganizationDTO
                .builder()
                .orgId(UUID.randomUUID())
                .orgIndustry(anyString())
                .orgName(anyString())
                .build();
    }

    public static List<OrganizationDTO> anyOrganizationDTOList() {
        return Stream.generate(OrganizationGivens::anyOrganizationDTO).limit(10).collect(Collectors.toList());
    }

}
