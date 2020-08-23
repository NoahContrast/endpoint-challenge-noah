package com.contrast.endpointchallenge.service;

import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.OrganizationDTO;
import com.contrast.endpointchallenge.exception.ResourceNotFoundException;
import com.contrast.endpointchallenge.mapper.ApplicationMapper;
import com.contrast.endpointchallenge.mapper.OrganizationMapper;
import com.contrast.endpointchallenge.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Service responsible for mapping Organization/Application DAOs to DTOs
 */
@Service
public class OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper organizationMapper;
    private final ApplicationMapper applicationMapper;

    public OrganizationService(final OrganizationRepository repository,
                               final OrganizationMapper organizationMapper,
                               final ApplicationMapper applicationMapper) {
        this.repository = repository;
        this.organizationMapper = organizationMapper;
        this.applicationMapper = applicationMapper;
    }

    /**
     * Get All Organizations
     * @param dtoConsumer The DTO Consumer
     */
    public void getAllOrganizations(final Consumer<OrganizationDTO> dtoConsumer) {
        repository.getAllOrganizations(dao -> dtoConsumer.accept(organizationMapper.oganizationDaoToDto(dao)));
    }

    /**
     * Get Organization By Id
     * @param id the ID of the organization
     * @return the OrganizationDTO
     */
    public OrganizationDTO getOrganizationById(final UUID id) {
        return organizationMapper.oganizationDaoToDto(repository.getOrganizationById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    /**
     * Get all Applications by Organization ID
     * @param dtoConsumer The DTO Consumer
     * @param id The ID of the Organization
     * @param query The Query string to match against name
     * @param order the column and order to ascend by
     */
    public void getApplicationsByOrgId(final Consumer<ApplicationDTO> dtoConsumer,
                                       final UUID id,
                                       final String query,
                                       final String order) {
        repository.getApplicationsByOrgId(dao -> dtoConsumer.accept(applicationMapper.applicationDaoToDTO(dao)), id, query, order);
    }

    /**
     * Checks if the Organization Exists
     * @param uuid OrgID to check
     * @return true if exists, false if not
     */
    public boolean organizationExists(final UUID uuid) {
        return repository.organizationExists(uuid);
    }
}
