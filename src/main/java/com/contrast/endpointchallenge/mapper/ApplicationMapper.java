package com.contrast.endpointchallenge.mapper;

import com.contrast.endpointchallenge.dao.ApplicationDAO;
import com.contrast.endpointchallenge.dto.ApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mappings({
            @Mapping(target="appId", source="id"),
            @Mapping(target="appName", source="name"),
            @Mapping(target="appDescription", source="description"),
            @Mapping(target="appPlatform.id", source="platform.id"),
            @Mapping(target="appPlatform.name", source="platform.name")
    })
    ApplicationDTO applicationDaoToDTO(ApplicationDAO dao);

}
