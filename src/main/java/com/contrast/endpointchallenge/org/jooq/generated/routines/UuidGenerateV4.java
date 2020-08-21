/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.routines;


import java.util.UUID;

import org.jooq.Parameter;
import org.jooq.generated.Public;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UuidGenerateV4 extends AbstractRoutine<UUID> {

    private static final long serialVersionUID = 218472108;

    /**
     * The parameter <code>public.uuid_generate_v4.RETURN_VALUE</code>.
     */
    public static final Parameter<UUID> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.UUID, false, false);

    /**
     * Create a new routine call instance
     */
    public UuidGenerateV4() {
        super("uuid_generate_v4", Public.PUBLIC, org.jooq.impl.SQLDataType.UUID);

        setReturnParameter(RETURN_VALUE);
    }
}