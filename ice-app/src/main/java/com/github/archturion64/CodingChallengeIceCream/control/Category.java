package com.github.archturion64.CodingChallengeIceCream.control;

public enum Category {

    CREAM_BASED("Sahne-Eis"),
    FRUIT_BASED("Frucht-Eis"),
    WATER_BASED("Wasser-Eis");

    private final String text;

    Category(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static Category fromString(final String text) throws IllegalArgumentException{
        for (Category e : Category.values()) {
            if (e.text.equalsIgnoreCase(text)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Category ENUM parsing failed");
    }
}
