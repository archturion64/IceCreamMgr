package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.github.archturion64.CodingChallengeIceCream.configuration.exceptions.IceCreamMappingException;
import com.github.archturion64.CodingChallengeIceCream.control.Category;
import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavor;

import java.util.HashSet;
import java.util.List;

public class IceCreamFlavorDTOMapper {

    public static IceCreamFlavor map(final IceCreamFlavorDTO obj) throws  IceCreamMappingException {
        try {
            return new IceCreamFlavor(obj.getName(),
                    Category.fromString(obj.getCategory()),
                    new HashSet<>(obj.getIngredients()),
                    obj.getFoodIntolerance(),
                    obj.getNutritionalValue(),
                    Double.parseDouble(obj.getPrice().replace(',', '.')));
        } catch (NumberFormatException nfe) {
            throw new IceCreamMappingException("price", "unexpected format");
        } catch (NullPointerException npe) {
            throw new IceCreamMappingException("price | ingredients", "null was passed");
        } catch (IllegalArgumentException iae) {
            throw new IceCreamMappingException("category", "enum parsing failed");
        } catch (Exception e) {
            throw new IceCreamMappingException("unexpected", "unexpected");
        }
    }

    public static IceCreamFlavorDTO map(final IceCreamFlavor obj) {
        return new IceCreamFlavorDTO(obj.getName(),
                obj.getCategory().toString(),
                List.copyOf(obj.getIngredients()),
                obj.getFoodIntolerance(),
                obj.getNutritionalValue(),
                obj.getPrice().toString());
    }
}
