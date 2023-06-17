package com.github.archturion64.CodingChallengeIceCream.configuration;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

import static com.github.archturion64.CodingChallengeIceCream.configuration.UserInputLimits.MAX_NUMBER_INGREDIENTS;

public class MaxIngredientsValidator implements ConstraintValidator<MaxIngredientsConstraint, List<String>> {
    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        if (values == null) {
            return false;
        }
        return values.size() <= MAX_NUMBER_INGREDIENTS;
    }
}