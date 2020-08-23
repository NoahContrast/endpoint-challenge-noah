package com.contrast.endpointchallenge.givens;

import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.PlatformDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.contrast.endpointchallenge.util.AnyJavaLang.anyString;

public class ApplicationGivens {

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
}
