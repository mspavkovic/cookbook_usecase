package com.valcon.cookbook.domain.recipe;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    @Transactional(readOnly = true)
    public Recipe findById (final Long id) throws EntityNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            throw new EntityNotFoundException(String.format("Recipe with id %s not found", id));
        }
        return recipe.get();
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
        return recipeRepository.save(recipeMapper.map(recipeDto));
    }

    @Override
    @Transactional
    public Recipe update(final UpdateRecipeDto recipeDto) {
        return recipeRepository.findById(recipeDto.id())
                               .map(recipe -> {
                                   mapFromDto(recipeDto, recipe);
                                   return recipeRepository.save(recipe);
        }).orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        final Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }

    private void mapFromDto(final UpdateRecipeDto recipeDto, final Recipe recipe) {
        recipe.setName(recipeDto.name());
        recipe.setInstructions(recipeDto.instructions());
        recipe.setNumberOfServings(recipeDto.numberOfServings());
        recipe.setIsVegetarian(recipeDto.isVegetarian());
        recipe.setCreationTime(LocalDateTime.now());
        recipe.getIngredientsList().clear();
        Optional.ofNullable(recipeDto.ingredientsList())
                .ifPresent(recipe.getIngredientsList()::addAll);
    }
}
