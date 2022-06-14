package com.valcon.cookbook.domain.ingredient;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Set<Ingredient> mapList(final Set<IngredientDto> ingredientsList) {
        return  Optional.ofNullable(ingredientsList)
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(this::map)
                        .collect(Collectors.toSet());
    }
}
