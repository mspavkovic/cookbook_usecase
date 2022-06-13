package com.valcon.cookbook.domain.recipe;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valcon.cookbook.common.Constants;
import com.valcon.cookbook.domain.ingredient.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper=false)
public class Recipe implements Serializable {

    private static final String ID_SEQUENCE = "recipe_id_sequence";
    private static final String ID_SEQUENCE_GENERATOR = "recipe_id_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR, sequenceName = ID_SEQUENCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "recipe name must be specified")
    private String name;

    @NotNull(message = "number of people the dish is suitable for must be specified")
    private Integer numberOfServings;

    private Boolean isVegetarian;

    @Column(columnDefinition = "LONGTEXT")
    @NotBlank(message = "recipe instructions must be specified")
    private String instructions;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN)
    private LocalDateTime creationTime = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "recipe")
    //@NotNull(message = "recipe must have at least one ingredient")
    @OrderBy("name DESC")
    @Builder.Default
    private Set<Ingredient> ingredientsList = new HashSet<>();

    public void addIngredients(Set<Ingredient> ingredients){
        ingredients.stream().forEach(ingredient -> {
            ingredientsList.add(ingredient);
            ingredient.setRecipe(this);
        });
    }
}

