package com.valcon.cookbook.web.dto;

import com.valcon.cookbook.domain.recipe.Recipe;

import lombok.Builder;

public record IngredientDto(String name, String quantity) {
    @Builder
    public IngredientDto {
    }

};

