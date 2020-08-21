package com.contrast.endpointchallenge.repository;

import com.contrast.endpointchallenge.dao.OrganizationDAO;
import org.jooq.DSLContext;
import org.simpleflatmapper.jooq.SelectQueryMapper;
import org.simpleflatmapper.jooq.SelectQueryMapperFactory;
import org.springframework.stereotype.Repository;

import java.util.function.Consumer;

import static org.jooq.generated.Tables.*;

@Repository
public class OrganizationRepository {

    private final DSLContext dslContext;

    private static final SelectQueryMapper<OrganizationDAO> orgMapper
            = SelectQueryMapperFactory.newInstance().newMapper(OrganizationDAO.class);

    public OrganizationRepository(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void getAllOrganizations(final Consumer<OrganizationDAO> daoConsumer) {
        orgMapper.stream(dslContext.selectFrom(ORGANIZATION)).forEach(daoConsumer);
    }

}
