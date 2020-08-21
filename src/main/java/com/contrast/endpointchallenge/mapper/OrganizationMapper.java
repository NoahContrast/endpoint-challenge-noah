package com.contrast.endpointchallenge.mapper;

import com.contrast.endpointchallenge.dao.OrganizationDAO;
import com.contrast.endpointchallenge.dto.OrganizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mappings({
            @Mapping(target="orgId", source="id"),
            @Mapping(target="orgName", source="name"),
            @Mapping(target="orgIndustry", source="industry")
    })
    OrganizationDTO oganizationDaoToDto(OrganizationDAO dao);

}
