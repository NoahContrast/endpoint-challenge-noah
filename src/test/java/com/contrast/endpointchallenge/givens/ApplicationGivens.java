package com.contrast.endpointchallenge.givens;

import com.contrast.endpointchallenge.dao.ApplicationDAO;
import com.contrast.endpointchallenge.dao.PlatformDAO;
import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.PlatformDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.contrast.endpointchallenge.util.AnyJavaLang.anyString;

public class ApplicationGivens {

    /**
     * DTOs
     */

    public static List<ApplicationDTO> anyApplicationDTOList() {
        return Stream.generate(ApplicationGivens::anyApplicationDTO).limit(10).collect(Collectors.toList());
    }

    public static ApplicationDTO anyApplicationDTO() {
        return ApplicationDTO.builder()
                .appId(UUID.randomUUID())
                .appName(anyString())
                .appDescription(anyString())
                .appPlatform(anyPlatformDTO())
                .build();
    }

    public static PlatformDTO anyPlatformDTO() {
        return PlatformDTO.builder()
                .id(UUID.randomUUID())
                .name(anyString())
                .build();
    }

    /**
     * DAOs
     */

    public static List<ApplicationDAO> anyApplicationDaoList() {
        return Stream.generate(ApplicationGivens::anyApplicationDao).limit(10).collect(Collectors.toList());
    }

    public static ApplicationDAO anyApplicationDao() {
        return ApplicationDAO.builder()
                .name(anyString())
                .id(UUID.randomUUID())
                .platform(anyPlatformDao())
                .description(anyString())
                .build();
    }

    public static PlatformDAO anyPlatformDao() {
        return PlatformDAO.builder()
                .id(UUID.randomUUID())
                .name(anyString())
                .build();
    }

}
