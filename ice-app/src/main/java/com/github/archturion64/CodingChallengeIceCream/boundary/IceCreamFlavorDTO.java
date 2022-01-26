package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.github.archturion64.CodingChallengeIceCream.configuration.MaxIngredientsConstraint;
import com.github.archturion64.CodingChallengeIceCream.configuration.ValueOfEnumConstraint;
import com.github.archturion64.CodingChallengeIceCream.control.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

import static com.github.archturion64.CodingChallengeIceCream.configuration.UserInputLimits.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("IceCreamFlavor")
public class IceCreamFlavorDTO implements Serializable {

    @NotBlank(message = "flavor name must be specified")
    @Size(max = MAX_LENGTH_FLAVOR)
    @ApiModelProperty(value = "given name of the ice cream flavor", required = true, example = "Bloody Mary")
    private String name;

    @NotBlank(message = "category must not be empty")
    @ValueOfEnumConstraint(enumClass = Category.class)
    @ApiModelProperty(value = "ice cream category", required = true, example = "Wasser-Eis")
    private String category;

    @NotEmpty(message = "ingredient list must not be empty")
    @MaxIngredientsConstraint
    @ApiModelProperty(value = "list of ingredients", required = true, example = "[\"Tomatensaft\",\"Tabasco\"]")
    private List<String> ingredients;

    @NotNull(message = "food intolerance not specified")
    @ApiModelProperty(value = "any known food intolerance", required = true, example = "Glutenintoleranz")
    private String foodIntolerance;

    @Min(MIN_NUTRITIONAL_VALUE_KCAL)
    @Max(MAX_NUTRITIONAL_VALUE_KCAL)
    @ApiModelProperty(value = "number of kcal per 100 grams", required = true, example = "219")
    private int nutritionalValue;

    @NotBlank
    @Pattern(regexp = "^((0?\\.((0[1-9])|[1-9]\\d))|([1-9]\\d*(\\.\\d{2})?))|((0?,((0[1-9])|[1-9]\\d))|([1-9]\\d*(,\\d{2})?))$")
    @ApiModelProperty(value = "amount in EUR with two digits after decimal point", required = true, example = "\"3.35\"")
    private String price;

}
