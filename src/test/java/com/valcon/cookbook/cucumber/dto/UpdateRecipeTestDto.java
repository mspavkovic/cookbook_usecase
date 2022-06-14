package com.valcon.cookbook.cucumber.dto;

import java.util.List;

import com.valcon.cookbook.domain.ingredient.Ingredient;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import lombok.Builder;

public record UpdateRecipeTestDto(Long id,
                                  String name,
                                  Integer numberOfServings,
                                  Boolean isVegetarian,
                                  String instructions, List<Ingredient> ingredients) {

    @Builder
    public UpdateRecipeTestDto {}

    public static UpdateRecipeDto map(UpdateRecipeTestDto updateRecipeTestDto) {
        return UpdateRecipeDto.builder()
                .id(updateRecipeTestDto.id)
                .name(updateRecipeTestDto.name)
                .numberOfServings(updateRecipeTestDto.numberOfServings)
                .isVegetarian(updateRecipeTestDto.isVegetarian)
                .instructions(updateRecipeTestDto.instructions)
                .build();

    }

}
