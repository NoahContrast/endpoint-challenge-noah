package com.contrast.endpointchallenge.service;

import com.contrast.endpointchallenge.dao.ApplicationDAO;
import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.OrganizationDTO;
import com.contrast.endpointchallenge.mapper.ApplicationMapper;
import com.contrast.endpointchallenge.mapper.OrganizationMapper;
import com.contrast.endpointchallenge.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

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

    public void getAllOrganizations(final Consumer<OrganizationDTO> dtoConsumer) {
        repository.getAllOrganizations(dao -> dtoConsumer.accept(organizationMapper.oganizationDaoToDto(dao)));
    }

    public OrganizationDTO getOrganizationById(final UUID id) {
        return organizationMapper.oganizationDaoToDto(repository.getOrganizationById(id));
    }

    public void getApplicationsByOrgId(final Consumer<ApplicationDTO> dtoConsumer,
                                       final UUID id,
                                       final String query,
                                       final String order) {
        repository.getApplicationsByOrgId(dao -> dtoConsumer.accept(applicationMapper.applicationDaoToDTO(dao)), id, query, order);
    }
}
