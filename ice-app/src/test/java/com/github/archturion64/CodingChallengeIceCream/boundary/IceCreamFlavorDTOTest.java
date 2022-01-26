package com.github.archturion64.CodingChallengeIceCream.boundary;

import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.archturion64.CodingChallengeIceCream.configuration.UserInputLimits.MAX_NUTRITIONAL_VALUE_KCAL;
import static com.github.archturion64.CodingChallengeIceCream.configuration.UserInputLimits.MIN_NUTRITIONAL_VALUE_KCAL;
import static org.assertj.core.api.Assertions.assertThat;

class IceCreamFlavorDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("validation should pass with the example values from openAPI doc")
    public void noViolationsWithExampleValues() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                219,
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("validation should fail with wrong category")
    public void violationCategory() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "banana", // invalid
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                219,
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("category") &&
                        e.getMessage().equals("must be any of class com.github.archturion64.CodingChallengeIceCream.control.Category")))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with missing ingredients")
    public void violationMissingIngredients() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                null, // invalid
                "Glutenintoleranz",
                219,
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("ingredients") &&
                        e.getMessage().equals("invalid ingredients count")))
                .isTrue();
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("ingredients") &&
                        e.getMessage().equals("ingredient list must not be empty")))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with empty ingredients list")
    public void violationIngredientsEmpty() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                new ArrayList<>(), // invalid
                "Glutenintoleranz",
                219,
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("ingredients") &&
                        e.getMessage().equals("ingredient list must not be empty")))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with missing food intolerance")
    public void violationMissingFoodIntolerance() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                null, // invalid
                219,
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("foodIntolerance") &&
                        e.getMessage().equals("food intolerance not specified")))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with nutrition value too low")
    public void violationNutritionValueTooLow() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                MIN_NUTRITIONAL_VALUE_KCAL - 1, // invalid
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("nutritionalValue") &&
                        e.getMessage().equals("must be greater than or equal to " + MIN_NUTRITIONAL_VALUE_KCAL)))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with nutrition value too high")
    public void violationNutritionValueTooHigh() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                MAX_NUTRITIONAL_VALUE_KCAL + 1, // invalid
                "3.35");
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("nutritionalValue") &&
                        e.getMessage().equals("must be less than or equal to " + MAX_NUTRITIONAL_VALUE_KCAL)))
                .isTrue();
    }

    @Test
    @DisplayName("validation should fail with price in wrong format")
    public void violationPriceWrongFormat() {
        // arrange
        IceCreamFlavorDTO sut = new IceCreamFlavorDTO("Bloody Mary",
                "Wasser-Eis",
                List.of("Tomatensaft", "Tabasco"),
                "Glutenintoleranz",
                219,
                "3.355"); // invalid
        // act
        Set<ConstraintViolation<IceCreamFlavorDTO>> violations = validator.validate(sut);
        // assert
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations
                .stream()
                .anyMatch(e -> e.getPropertyPath().toString().equals("price") &&
                        e.getMessage().equals("must match \"^((0?\\.((0[1-9])|[1-9]\\d))|([1-9]\\d*(\\.\\d{2})?))|((0?,((0[1-9])|[1-9]\\d))|([1-9]\\d*(,\\d{2})?))$\"")))
                .isTrue();
    }

}