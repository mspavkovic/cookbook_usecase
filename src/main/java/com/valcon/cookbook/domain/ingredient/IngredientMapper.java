package com.valcon.cookbook.domain.ingredient;

import org.springframework.stereotype.Component;

import com.valcon.cookbook.web.dto.IngredientDto;

@Component
public class IngredientMapper {

    public Ingredient map(final IngredientDto ingredientDto) {
        return Ingredient.builder()
                         .name(ingredientDto.name())
                         .quantity(ingredientDto.quantity())
                         .build();
    }

    public IngredientDto map(final Ingredient ingredient) {
        return IngredientDto.builder()
                            .name(ingredient.getName())
                            .quantity(ingredient.getQuantity())
                            .build();
    }
}
