package com.contrast.endpointchallenge.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrgExistsValidator.class)
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OrgExists {
    String message() default "Organization does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
