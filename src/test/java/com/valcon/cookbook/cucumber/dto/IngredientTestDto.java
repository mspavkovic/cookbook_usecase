package com.valcon.cookbook.cucumber.dto;

import com.valcon.cookbook.web.dto.IngredientDto;

import lombok.Builder;

public record IngredientTestDto(String name, String quantity) {

    @Builder
    public IngredientTestDto {
    }

    public static IngredientDto map(final IngredientTestDto ingredientTestDto) {
        return IngredientDto.builder()
                            .name(ingredientTestDto.name)
                            .quantity(ingredientTestDto.quantity)
                           // .recipeId(ingredientTestDto.recipeId)
                            .build();
    }

    public static IngredientTestDto map(final IngredientDto ingredientDto) {
        return IngredientTestDto.builder()
                            .name(ingredientDto.name())
                            .quantity(ingredientDto.quantity())
                            .build();
    }
}
