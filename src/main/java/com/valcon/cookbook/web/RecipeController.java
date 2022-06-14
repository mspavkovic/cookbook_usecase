package com.valcon.cookbook.web;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valcon.cookbook.domain.recipe.Recipe;
import com.valcon.cookbook.domain.recipe.RecipeMapper;
import com.valcon.cookbook.domain.recipe.RecipeService;
import com.valcon.cookbook.web.api.RecipeControllerApi;
import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/recipes")
public class RecipeController implements RecipeControllerApi {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<RecipeDto>> findAll() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    @Override
    @PutMapping
    public ResponseEntity<RecipeDto> update(@Valid @RequestBody UpdateRecipeDto recipeDto) {
        return ResponseEntity.ok(recipeMapper.map(recipeService.update(recipeDto)));
    }

    @Override
    @PostMapping
    public ResponseEntity<RecipeDto> create(@Valid @RequestBody RecipeDto dto) {
        final Recipe savedRecipe = recipeService.save(dto);
        final URI getLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                                   .path("/{id}")
                                   .buildAndExpand(savedRecipe.getId())
                                   .toUri();
        return ResponseEntity.created(getLocation).body(recipeMapper.map(savedRecipe));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

