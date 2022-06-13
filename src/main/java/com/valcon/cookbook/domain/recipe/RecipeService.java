package com.valcon.cookbook.domain.recipe;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

public interface RecipeService {

    Recipe findById(Long id) throws EntityNotFoundException;

    Recipe save(RecipeDto recipeDto);

    List<RecipeDto> findAll();

    Recipe update(UpdateRecipeDto recipeDto);

    void deleteById(Long id);
}
