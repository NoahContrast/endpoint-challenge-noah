package com.contrast.endpointchallenge.controller;

import com.contrast.endpointchallenge.service.OrganizationService;
import com.contrast.endpointchallenge.util.EndpointConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Controller
public class OrganizationController {

    private final OrganizationService service;

    public OrganizationController(final OrganizationService service) {
        this.service = checkNotNull(service, "OrganizationService was null");
    }

    @RequestMapping(path = EndpointConstants.ORGANIZATIONS, method = RequestMethod.GET)
    public ResponseEntity<?> getOrganizations() {
        service.getAllOrganizations();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = EndpointConstants.ORGANIZATION, method = RequestMethod.GET)
    public ResponseEntity<?> getOrganizationById(@PathVariable(EndpointConstants.ID) final UUID id) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = EndpointConstants.ORGANIZATION_APPLICATIONS, method = RequestMethod.GET)
    public ResponseEntity<?> getApplicationsByOrgId(@PathVariable(EndpointConstants.ID) final UUID id) {
        return ResponseEntity.ok().build();
    }
}
