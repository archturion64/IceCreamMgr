package com.github.archturion64.CodingChallengeIceCream.control;


import java.util.Set;

public record IceCreamFlavor(String name, Category category, Set<String> ingredients, String foodIntolerance,
                             Integer nutritionalValue, Double price) {

}
