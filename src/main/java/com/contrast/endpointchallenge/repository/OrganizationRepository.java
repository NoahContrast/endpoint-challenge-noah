package com.contrast.endpointchallenge.repository;

import com.contrast.endpointchallenge.dao.ApplicationDAO;
import com.contrast.endpointchallenge.dao.OrganizationDAO;
import com.google.common.collect.ImmutableMap;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.TableField;
import org.simpleflatmapper.jooq.SelectQueryMapper;
import org.simpleflatmapper.jooq.SelectQueryMapperFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.trueCondition;

/**
 * Repository responsible for Organizations and Applications by Organizations
 */
@Repository
public class OrganizationRepository {

    private final DSLContext dslContext;

    private static final SelectQueryMapper<OrganizationDAO> orgMapper
            = SelectQueryMapperFactory.newInstance().newMapper(OrganizationDAO.class);

    private static final SelectQueryMapper<ApplicationDAO> appMapper
            = SelectQueryMapperFactory.newInstance().newMapper(ApplicationDAO.class);

    public ImmutableMap<String, TableField<?, ?>> columnMap = ImmutableMap.of(
            "name", APPLICATION.NAME,
            "description", APPLICATION.DESCRIPTION
    );

    public OrganizationRepository(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Get All Organizations
     *
     * @param daoConsumer an OrganizationDao consumer
     */
    public void getAllOrganizations(final Consumer<OrganizationDAO> daoConsumer) {
        orgMapper.stream(dslContext.selectFrom(ORGANIZATION)).forEach(daoConsumer);
    }

    /**
     * Get Organization by ID
     *
     * @param id The ID of the Org to fetch
     * @return OrganizationDAO
     */
    public Optional<OrganizationDAO> getOrganizationById(final UUID id) {
        return Optional.ofNullable(dslContext.select()
                .from(ORGANIZATION)
                .where(ORGANIZATION.ID.eq(id))
                .fetchOneInto(OrganizationDAO.class));
    }

    /**
     * Gets all Applications by Org ID
     *
     * @param daoConsumer the Dao Consumer
     * @param id          The ID of the org to fetch all apps by
     * @param query       the
     * @param order       column name to order by asc/desc
     */
    public void getApplicationsByOrgId(final Consumer<ApplicationDAO> daoConsumer,
                                       final UUID id,
                                       final String query,
                                       final String order) {
        appMapper.stream(
                dslContext.select(
                        APPLICATION.ID.as("id"),
                        APPLICATION.NAME.as("name"),
                        APPLICATION.DESCRIPTION.as("description"),
                        PLATFORM.ID.as("platform.id"),
                        PLATFORM.NAME.as("platform.name")
                )
                        .from(ORGANIZATION_APPLICATION)
                        .join(ORGANIZATION)
                        .on(ORGANIZATION.ID.eq(ORGANIZATION_APPLICATION.ORG_ID))
                        .join(APPLICATION).on(APPLICATION.ID.eq(ORGANIZATION_APPLICATION.APP_ID))
                        .join(PLATFORM).on(PLATFORM.ID.eq(APPLICATION.PLATFORM_ID))
                        .where(ORGANIZATION.ID.eq(id)
                                .and(conditionQueryFilter(query)))
                        .orderBy(queryOrder(order))
        ).forEach(daoConsumer);
    }

    /**
     * Takes a query and returns appropriate jooq Condition to filter by
     * Ignores case sensitivity
     *
     * @param query the query string
     * @return Condition The JOOQ condition
     */
    private Condition conditionQueryFilter(final String query) {
        Condition condition = trueCondition();

        if (!StringUtils.isEmpty(query)) {
            condition = condition.and(APPLICATION.NAME.likeIgnoreCase("%" + query + "%"));
        }

        return condition;
    }

    /**
     * Takes an order query, sanitises and splits the string to build JOOQ SortField
     *
     * @param orderCondition the column and order to filter by
     * @return SortField the converted jooq condition
     */
    private SortField<?> queryOrder(final String orderCondition) {
        if (!StringUtils.isEmpty(orderCondition)) {
            String strippedOrderCondition = orderCondition.replaceAll("'", "");
            String[] columnAndOrder = strippedOrderCondition.split("\\s+");

            SortField<?> orderBy;

            if (columnAndOrder.length == 2) {
                String column = columnAndOrder[0];
                String order = columnAndOrder[1];

                TableField<?, ?> tableField = columnMap.get(column);

                if(tableField == null) {
                    return APPLICATION.NAME.desc(); //default behavior;
                }

                if (!StringUtils.isEmpty(order)) {
                    if (order.equalsIgnoreCase("asc")) {
                        orderBy = tableField.asc();
                    } else {
                        orderBy = tableField.desc();
                    }
                } else {
                    orderBy = tableField.desc();
                }

                return orderBy;
            }
        }
        return APPLICATION.NAME.desc(); //default behavior
    }
}
