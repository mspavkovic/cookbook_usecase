package com.valcon.cookbook.web.dto;

import lombok.Builder;

public record IngredientDto(String name, String quantity) {
    @Builder
    public IngredientDto {
    }

};

