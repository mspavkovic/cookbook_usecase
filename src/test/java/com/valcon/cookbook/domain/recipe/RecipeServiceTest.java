package com.valcon.cookbook.domain.recipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.valcon.cookbook.domain.ingredient.Ingredient;
import com.valcon.cookbook.web.dto.IngredientDto;
import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;


@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @Test
    void updateTest() {
        Recipe recipe = Recipe.builder()
                              .name("Recipe1")
                .isVegetarian(true)
                .instructions("Instructions for preparation")
                .numberOfServings(5)
                .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0))
                .ingredientsList(createIngredientList())
                .build();


        UpdateRecipeDto recipeUpdated = UpdateRecipeDto.builder()
                                                       .id(1L)
                                                       .name("Recipe2")
                                                       .isVegetarian(true)
                                                       .instructions("Instructions for preparation")
                                                       .numberOfServings(7)
                                                       .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0))
                                                       .ingredientsList(createIngredientList())
                                                       .build();

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        recipeService.update(recipeUpdated);
        assertThat(recipeUpdated.name()).isEqualTo("Recipe2");
        assertThat(recipeUpdated.isVegetarian()).isEqualTo(true);
        assertThat(recipeUpdated.instructions()).isEqualTo("Instructions for preparation");
        assertThat(recipeUpdated.numberOfServings()).isEqualTo(7);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void saveTest() {
        Recipe recipe = Recipe.builder()
                              .name("Recipe1")
                              .isVegetarian(true)
                              .instructions("Instructions for preparation")
                              .numberOfServings(7)
                              .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0))
                              .ingredientsList(createIngredientList())
                              .build();


        RecipeDto recipeDTO = RecipeDto.builder()

                                                 .name("Recipe1")
                                                 .isVegetarian(true)
                                                 .instructions("Instructions for preparation")
                                                 .numberOfServings(7)
                                                 .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0))
                                                 .ingredientsList(createIngredientDtoList())
                                                 .build();

        when(recipeMapper.map(recipeDTO)).thenReturn(recipe);
        recipeService.save(recipeDTO);
        assertThat(recipe.getName()).isEqualTo("Recipe1");
        assertThat(recipe.getIsVegetarian()).isEqualTo(true);
        assertThat(recipe.getInstructions()).isEqualTo("Instructions for preparation");
        assertThat(recipe.getNumberOfServings()).isEqualTo(7);
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    private Set<IngredientDto> createIngredientDtoList() {
        return Sets.set(IngredientDto.builder()
                                          .name("Ingredient1")
                                          .quantity("100g")
                                          .build(),
                        IngredientDto.builder()
                                          .name("Ingredient2")
                                          .quantity("200g")
                                          .build());
    }

    private Set<Ingredient> createIngredientList() {
        return Sets.set(Ingredient.builder()
                                     .name("Ingredient1")
                                     .quantity("100g")
                                     .build(),
                        Ingredient.builder()
                                     .name("Ingredient2")
                                     .quantity("200g")
                                     .build());
    }
}
