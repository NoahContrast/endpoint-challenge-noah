package com.contrast.endpointchallenge.service;

import com.contrast.endpointchallenge.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(final OrganizationRepository repository) {
        this.repository = repository;
    }

    public void getAllOrganizations() {
        repository.getAllOrganizations(dao -> {

        });
    }
}
