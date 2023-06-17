package com.github.archturion64.CodingChallengeIceCream.entity;

import com.github.archturion64.CodingChallengeIceCream.control.Category;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "flavor")
@Table(name = "flavors")
public class IceCreamFlavorEntity {

    public IceCreamFlavorEntity() {
        // for JPA
    }

    public IceCreamFlavorEntity(String name, Category category, Set<IngredientEntity> ingredients, String foodIntolerance, Integer nutritionalValue, Double price) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.foodIntolerance = foodIntolerance;
        this.nutritionalValue = nutritionalValue;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "flavor_id",
            updatable = false)
    private Long id;

    @Column(
            columnDefinition="TEXT",
            name = "flavor_name")
    private String name;

    @Column(
            name = "flavor_category"
    )
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "flavor_ingredients",
            joinColumns = { @JoinColumn(name = "flavor_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    private Set<IngredientEntity> ingredients = new HashSet<>();

    @Column(
            columnDefinition="TEXT",
            name = "flavor_intolerance")
    private String foodIntolerance;

    @Column( name = "flavor_energy")
    private Integer nutritionalValue;

    @Column(
            precision = 10,
            name = "flavor_price"
    )
    private Double price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Set<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public String getFoodIntolerance() {
        return foodIntolerance;
    }

    public Integer getNutritionalValue() {
        return nutritionalValue;
    }

    public Double getPrice() {
        return price;
    }
}
