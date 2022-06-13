package com.valcon.cookbook.domain.recipe;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.valcon.cookbook.domain.ingredient.IngredientMapper;
import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

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

    public void map(final UpdateRecipeDto recipeDto, final Recipe recipe) {
        recipe.setName(recipeDto.name());
        recipe.setInstructions(recipeDto.instructions());
        recipe.setNumberOfServings(recipeDto.numberOfServings());
        recipe.setIsVegetarian(recipeDto.isVegetarian());
        recipe.setCreationTime(LocalDateTime.now());
    }
}
