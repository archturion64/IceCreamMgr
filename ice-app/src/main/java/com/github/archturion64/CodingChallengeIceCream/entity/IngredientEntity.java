package com.github.archturion64.CodingChallengeIceCream.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ingredient")
@Table(name = "ingredients")
public class IngredientEntity {

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

    public IngredientEntity(String name) {
        this.name = name;
    }
}