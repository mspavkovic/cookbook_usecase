package com.valcon.cookbook.domain.recipe;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valcon.cookbook.domain.ingredient.Ingredient;
import com.valcon.cookbook.domain.ingredient.IngredientMapper;
import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    @Transactional(readOnly = true)
    public Recipe findById (final Long id) throws EntityNotFoundException {
        return recipeRepository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException(format("Recipe with id %s does not exists", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return recipeRepository.findAll()
                               .stream()
                               .map(recipeMapper::map)
                               .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Recipe save(final RecipeDto recipeDto) {
        Recipe newRecipe = recipeRepository.save(recipeMapper.map(recipeDto));
        newRecipe.addIngredients(ingredientMapper.mapList(recipeDto.ingredientsList()));
        return newRecipe;
    }

    @Override
    @Transactional
    public Recipe update(final UpdateRecipeDto recipeDto) {
        return recipeRepository.findById(recipeDto.id())
                               .map(recipe -> {
                                   recipeMapper.map(recipeDto, recipe);
                                   recipe.addIngredients(recipeDto.ingredientsList());
                                   return recipeRepository.save(recipe);
        }).orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        final Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }

}
