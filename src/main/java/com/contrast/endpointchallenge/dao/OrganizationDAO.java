package com.contrast.endpointchallenge.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationDAO {

    private UUID id;
    private String name;
    private String industry;

}
