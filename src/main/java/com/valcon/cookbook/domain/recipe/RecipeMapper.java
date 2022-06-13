package com.valcon.cookbook.domain.recipe;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.valcon.cookbook.domain.ingredient.IngredientMapper;
import com.valcon.cookbook.web.dto.RecipeDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeMapper {

    public final IngredientMapper ingredientMapper;

    public Recipe map(final RecipeDto recipeDto) {
        return Recipe.builder()
                     .name(recipeDto.name())
                     .numberOfServings(recipeDto.numberOfServings())
                     .creationTime(LocalDateTime.now())
                     .instructions(recipeDto.instructions())
                     .isVegetarian(recipeDto.isVegetarian())
                     .ingredientsList(Optional.ofNullable(recipeDto.ingredientsList())
                                              .map(Collection::stream)
                                              .orElseGet(Stream::empty)
                                              .map(ingredientMapper::map)
                                              .collect(Collectors.toSet()))

                     .build();
    }

    public RecipeDto map(final Recipe recipe) {
        return RecipeDto.builder()
                 .name(recipe.getName())
                 .numberOfServings(recipe.getNumberOfServings())
                 .creationTime(recipe.getCreationTime())
                 .instructions(recipe.getInstructions())
                 .isVegetarian(recipe.getIsVegetarian())
                 .ingredientsList(recipe.getIngredientsList()
                                           .stream()
                                           .map(ingredient -> ingredientMapper.map(ingredient))
                                           .collect(Collectors.toSet()))
                 .build();
    }
}
