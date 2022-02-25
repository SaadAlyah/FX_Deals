package com.progresssoft.fx_deals.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsoCodeValidator.class)
public @interface IsoCode {

    String message() default "Wrong entry, please enter a valid ISO Code.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
