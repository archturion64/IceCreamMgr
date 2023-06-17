package com.github.archturion64.CodingChallengeIceCream.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumConstraint, CharSequence> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnumConstraint constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return false;
        }
        return acceptedValues.contains(charSequence.toString());
    }
}
