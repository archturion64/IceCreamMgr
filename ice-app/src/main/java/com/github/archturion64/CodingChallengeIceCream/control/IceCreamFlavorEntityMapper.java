package com.github.archturion64.CodingChallengeIceCream.control;

import com.github.archturion64.CodingChallengeIceCream.entity.IceCreamFlavorEntity;
import com.github.archturion64.CodingChallengeIceCream.entity.IngredientEntity;


import java.util.stream.Collectors;

public class IceCreamFlavorEntityMapper {

    public static IceCreamFlavorEntity map(final IceCreamFlavor obj) {
        return new IceCreamFlavorEntity(obj.name(),
                obj.category(),
                obj.ingredients()
                        .stream()
                        .map(IngredientEntity::new)
                        .collect(Collectors.toSet()),
                obj.foodIntolerance(),
                obj.nutritionalValue(),
                obj.price());
    }

    public static IceCreamFlavor map(final IceCreamFlavorEntity obj) {
        return new IceCreamFlavor(obj.getName(),
                obj.getCategory(),
                obj.getIngredients()
                        .stream()
                        .map(IngredientEntity::getName)
                        .collect(Collectors.toSet()),
                obj.getFoodIntolerance(),
                obj.getNutritionalValue(),
                obj.getPrice());
    }
}
