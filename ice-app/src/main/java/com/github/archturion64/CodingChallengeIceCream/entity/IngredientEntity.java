package com.github.archturion64.CodingChallengeIceCream.entity;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ingredient")
@Table(name = "ingredients")
public class IngredientEntity {

    public IngredientEntity() {
        // for JPA
    }

    public IngredientEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "ingredient_id",
            updatable = false)
    private Long id;

    @Column(
            name = "ingredient_name",
            columnDefinition="TEXT",
            nullable = false)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<IceCreamFlavorEntity> flavors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<IceCreamFlavorEntity> getFlavors() {
        return flavors;
    }
}