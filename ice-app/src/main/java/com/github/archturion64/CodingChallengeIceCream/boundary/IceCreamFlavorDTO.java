package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.github.archturion64.CodingChallengeIceCream.configuration.MaxIngredientsConstraint;
import com.github.archturion64.CodingChallengeIceCream.configuration.ValueOfEnumConstraint;
import com.github.archturion64.CodingChallengeIceCream.control.Category;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

import static com.github.archturion64.CodingChallengeIceCream.configuration.UserInputLimits.*;

public class IceCreamFlavorDTO implements Serializable {

    public IceCreamFlavorDTO() {
        // for jackson
    }

    public IceCreamFlavorDTO(String name, String category, List<String> ingredients, @NotNull(message = "food intolerance not specified") String foodIntolerance, int nutritionalValue, String price) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.foodIntolerance = foodIntolerance;
        this.nutritionalValue = nutritionalValue;
        this.price = price;
    }

    @NotBlank(message = "flavor name must be specified")
    @Size(max = MAX_LENGTH_FLAVOR)
    private String name;

    @NotBlank(message = "category must not be empty")
    @ValueOfEnumConstraint(enumClass = Category.class)
    private String category;

    @NotEmpty(message = "ingredient list must not be empty")
    @MaxIngredientsConstraint
    private List<String> ingredients;

    @NotNull(message = "food intolerance not specified")
    private String foodIntolerance;

    @Min(MIN_NUTRITIONAL_VALUE_KCAL)
    @Max(MAX_NUTRITIONAL_VALUE_KCAL)
    private int nutritionalValue;

    @NotBlank
    @Pattern(regexp = "^((0?\\.((0[1-9])|[1-9]\\d))|([1-9]\\d*(\\.\\d{2})?))|((0?,((0[1-9])|[1-9]\\d))|([1-9]\\d*(,\\d{2})?))$")
    private String price;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getFoodIntolerance() {
        return foodIntolerance;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public String getPrice() {
        return price;
    }
}
