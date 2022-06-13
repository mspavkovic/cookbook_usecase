package com.valcon.cookbook.cucumber.dto;

import com.valcon.cookbook.web.dto.RecipeDto;

import lombok.Builder;

public record RecipeTestDto (String name,
                             Integer numberOfServings,
                             Boolean isVegetarian,
                             String instructions) {

    @Builder
    public RecipeTestDto {}

    public static RecipeDto map(RecipeTestDto recipeTestDto) {
        return RecipeDto.builder()
                .name(recipeTestDto.name)
                .numberOfServings(recipeTestDto.numberOfServings)
                .isVegetarian(recipeTestDto.isVegetarian)
                .instructions(recipeTestDto.instructions)
                .build();

    }

    public static RecipeTestDto map(RecipeDto recipeDto) {
        return RecipeTestDto.builder()
                        .name(recipeDto.name())
                        .numberOfServings(recipeDto.numberOfServings())
                        .isVegetarian(recipeDto.isVegetarian())
                        .instructions(recipeDto.instructions())
                        .build();

    }

}
