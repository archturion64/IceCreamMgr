package com.github.archturion64.CodingChallengeIceCream.configuration;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MaxIngredientsValidator.class)
public @interface MaxIngredientsConstraint {
    String message() default "invalid ingredients count";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}