package com.github.archturion64.CodingChallengeIceCream.control;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class IceCreamFlavor {

    private final String name;

    private final Category category;

    private final Set<String> ingredients;

    private final String foodIntolerance;

    private final Integer nutritionalValue;

    private final Double price;
}
