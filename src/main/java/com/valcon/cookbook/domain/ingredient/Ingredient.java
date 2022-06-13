package com.valcon.cookbook.domain.ingredient;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.valcon.cookbook.domain.recipe.Recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    private static final String ID_SEQUENCE = "ingredient_id_sequence";
    private static final String ID_SEQUENCE_GENERATOR = "ingredient_id_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR, sequenceName = ID_SEQUENCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "ingredient name must be specified")
    @EqualsAndHashCode.Include
    private String name;

    @NotBlank(message = "ingredient quantity must be specified")
    @EqualsAndHashCode.Include
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
