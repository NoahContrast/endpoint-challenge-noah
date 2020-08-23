package com.contrast.endpointchallenge.service;

import com.contrast.endpointchallenge.dao.ApplicationDAO;
import com.contrast.endpointchallenge.dao.OrganizationDAO;
import com.contrast.endpointchallenge.dto.ApplicationDTO;
import com.contrast.endpointchallenge.dto.OrganizationDTO;
import com.contrast.endpointchallenge.exception.ResourceNotFoundException;
import com.contrast.endpointchallenge.mapper.ApplicationMapper;
import com.contrast.endpointchallenge.mapper.OrganizationMapper;
import com.contrast.endpointchallenge.repository.OrganizationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.contrast.endpointchallenge.givens.ApplicationGivens.anyApplicationDaoList;
import static com.contrast.endpointchallenge.givens.OrganizationGivens.anyOrganizationDAO;
import static com.contrast.endpointchallenge.givens.OrganizationGivens.anyOrganizationDAOSList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource( properties = { "spring.flyway.enabled=false" })
public class OrganizationServiceTest {

    private OrganizationService service;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Mock
    private OrganizationRepository repository;

    @Before
    public void setUp() {
        service = new OrganizationService(repository, organizationMapper, applicationMapper);
    }

    @Test
    public void getAllOrganizations_getsCorrectlyMappedOrganizations() {

        List<OrganizationDAO> expectedDaos = anyOrganizationDAOSList();
        List<OrganizationDTO> expectedDtos = expectedDaos.stream()
                .map(organizationMapper::oganizationDaoToDto)
                .collect(Collectors.toList());

        List<OrganizationDTO> actualDtos = new ArrayList<>();

        doAnswer(ans -> {
                Consumer<OrganizationDAO> consumer = ans.getArgument(0);
                expectedDaos.forEach(consumer);
            return null;
        }
        ).when(repository).getAllOrganizations(any());

        service.getAllOrganizations(actualDtos::add);

        assertEquals(expectedDtos, actualDtos);
    }

    @Test
    public void getOrganizationById_getsCorrectlyMappedOrganizationById() {
        OrganizationDAO expectedDao = anyOrganizationDAO();
        OrganizationDTO expectedDto = organizationMapper.oganizationDaoToDto(expectedDao);

        when(repository.getOrganizationById(any())).thenReturn(Optional.of(expectedDao));

        OrganizationDTO actualDto = service.getOrganizationById(UUID.randomUUID());

        assertEquals(expectedDto, actualDto);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getOrganizationById_withNonExistentId_throwsCorrectly() {
        when(repository.getOrganizationById(any())).thenReturn(Optional.ofNullable(null));
        service.getOrganizationById(UUID.randomUUID());
    }

    @Test
    public void getApplicationsByOrgId_getCorrectlyMappedApplications() {
        List<ApplicationDAO> expectedDaos = anyApplicationDaoList();
        List<ApplicationDTO> expectedDtos = expectedDaos.stream()
                .map(applicationMapper::applicationDaoToDTO)
                .collect(Collectors.toList());

        List<ApplicationDTO> actualDtos = new ArrayList<>();

        doAnswer(ans -> {
                    Consumer<ApplicationDAO> consumer = ans.getArgument(0);
                    expectedDaos.forEach(consumer);
                    return null;
                }
        ).when(repository).getApplicationsByOrgId(any(), any(), any(), any());

        service.getApplicationsByOrgId(actualDtos::add, UUID.randomUUID(), null, null);

        assertEquals(expectedDtos, actualDtos);
    }

}
