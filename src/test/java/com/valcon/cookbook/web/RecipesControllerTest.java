package com.valcon.cookbook.web;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valcon.cookbook.domain.ingredient.Ingredient;
import com.valcon.cookbook.domain.recipe.Recipe;
import com.valcon.cookbook.domain.recipe.RecipeMapper;
import com.valcon.cookbook.domain.recipe.RecipeService;
import com.valcon.cookbook.web.dto.IngredientDto;
import com.valcon.cookbook.web.dto.RecipeDto;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(controllers = RecipeController.class)
public class RecipesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private RecipeMapper recipeMapper;

    @TestConfiguration
    static class DefaultConfigWithoutCsrf extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            super.configure(http);
            http.csrf().disable();
        }
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void findAllRecipes_success() throws Exception {
        Mockito.when(this.recipeService.findAll()).thenReturn(Arrays.asList(buildRecipeDto()));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void findAllRecipes_unauthorized() throws Exception {
        Mockito.when(this.recipeService.findAll()).thenReturn(Arrays.asList(buildRecipeDto()));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "USER")
    void createRecipe_success() throws Exception{
        Mockito.when(this.recipeService.save(Mockito.any(RecipeDto.class))).thenReturn(buildRecipe());
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(buildRecipeDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/api/recipes/1"));
    }

    private RecipeDto buildRecipeDto() {
        return RecipeDto.builder()
                        .creationTime(LocalDateTime.now(ZoneOffset.UTC))
                        .isVegetarian(true).numberOfServings(3)
                        .name("Recipe 1")
                        .instructions("Some simple meat dish, no need for any preparation")
                        .ingredientsList(new HashSet<>(Collections.singletonList(IngredientDto.builder().name("Meat").build()))).build();
    }

    private Recipe buildRecipe() {
        return Recipe.builder()
                     .id(1L)
                     .creationTime(LocalDateTime.now(ZoneOffset.UTC))
                     .isVegetarian(true).numberOfServings(3)
                     .instructions("Some simple meat dish, no need for any preparation")
                     .ingredientsList(new HashSet<>(Collections.singletonList(Ingredient.builder().name("Meat").build()))).build();
    }
}
