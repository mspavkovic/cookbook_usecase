package com.valcon.cookbook.cucumber.dto;

import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import lombok.Builder;

public record UpdateRecipeTestDto(Long id,
                                  String name,
                                  Integer numberOfServings,
                                  Boolean isVegetarian,
                                  String instructions) {

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
