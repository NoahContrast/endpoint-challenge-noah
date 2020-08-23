package com.contrast.endpointchallenge.constraint;

import com.contrast.endpointchallenge.service.OrganizationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;


public class OrgExistsValidator implements ConstraintValidator<OrgExists, UUID> {

    private final OrganizationService service;

    public OrgExistsValidator(final OrganizationService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        boolean exists = service.organizationExists(uuid);

        if(!exists) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format("Organization with ID %s does not exist", uuid)).addConstraintViolation();
            return false;
        }

        return true;
    }
}
