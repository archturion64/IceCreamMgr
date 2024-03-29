package com.github.archturion64.CodingChallengeIceCream.entity;

import com.github.archturion64.CodingChallengeIceCream.control.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        return category.toString();
    }

    @Override
    public Category convertToEntityAttribute(String code) {
        return Category.fromString(code);
    }
}
