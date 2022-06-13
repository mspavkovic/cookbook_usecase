package com.valcon.cookbook.domain.ingredient;


import java.util.List;

import com.valcon.cookbook.web.dto.IngredientDto;

public interface IngredientService {

    Ingredient save(final IngredientDto ingredientDto);
    List<IngredientDto> findAll();
}
