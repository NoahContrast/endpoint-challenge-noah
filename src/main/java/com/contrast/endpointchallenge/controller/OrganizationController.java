package com.contrast.endpointchallenge.controller;

import com.contrast.endpointchallenge.constraint.OrgExists;
import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.OrganizationDTO;
import com.contrast.endpointchallenge.repository.OrganizationRepository;
import com.contrast.endpointchallenge.service.OrganizationService;
import com.contrast.endpointchallenge.util.EndpointConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Controller to handle GET requests for Organizations and Applications
 */
@Validated
@Controller
public class OrganizationController {

    private final OrganizationService service;
    private final OrganizationRepository repository;

    public OrganizationController(final OrganizationService service, OrganizationRepository repository) {
        this.service = checkNotNull(service, "OrganizationService was null");
        this.repository = repository;
    }

    public void dummySqlInjection(String userId)
            throws SQLException {
        String sql = "select "
                + "first_name,last_name,username "
                + "from users where userid = '"
                + userId
                + "'";
        System.out.println(sql);

//        repository.
    //        Connection c = dataSource.getConnection();
//        ResultSet rs = c.createStatement().executeQuery(sql);
    }

    /**
     * Get All Organizations
     *
     * @return List of Organization DTOs
     */
    @RequestMapping(path = EndpointConstants.ORGANIZATIONS, method = RequestMethod.GET)
    public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
        List<OrganizationDTO> dtoList = new ArrayList<>();
        service.getAllOrganizations(dtoList::add);
        return ResponseEntity.ok(dtoList);
    }

    /**
     * Get Organization By ID
     *
     * @param id The ID of the Organization
     * @return The Organization DTO
     */
    @RequestMapping(path = EndpointConstants.ORGANIZATION, method = RequestMethod.GET)
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable(EndpointConstants.ID) final UUID id) {
        return ResponseEntity.ok(service.getOrganizationById(id));
    }

    /**
     * Get Applications By Organization ID
     *
     * @param id    The ID of the Organization
     * @param query The Query to filter by on name
     * @param order The column and order of the results
     * @return List of Application DTOs that corresponds with Organization ID
     */
    @RequestMapping(path = EndpointConstants.ORGANIZATION_APPLICATIONS, method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByOrgId(@PathVariable(EndpointConstants.ID) @OrgExists final UUID id,
                                                                       @RequestParam(name = "query", required = false) final String query,
                                                                       @RequestParam(name = "order", required = false) final String order) {
        List<ApplicationDTO> dtoList = new ArrayList<>();
        service.getApplicationsByOrgId(dtoList::add, id, query, order);
        return ResponseEntity.ok(dtoList);
    }
}
