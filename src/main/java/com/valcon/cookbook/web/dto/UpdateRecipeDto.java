package com.valcon.cookbook.web.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.valcon.cookbook.domain.ingredient.Ingredient;

import lombok.Builder;

public record UpdateRecipeDto(@NotNull Long id,
                              @NotNull String name,
                              @NotNull Integer numberOfServings,
                              @NotNull Boolean isVegetarian,
                              @NotEmpty String instructions,
                              LocalDateTime creationTime,
                              Set<Ingredient> ingredientsList) {
    @Builder
    public UpdateRecipeDto {
    }
}

