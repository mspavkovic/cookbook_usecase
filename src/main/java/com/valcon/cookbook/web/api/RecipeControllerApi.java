package com.valcon.cookbook.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.valcon.cookbook.web.dto.RecipeDto;
import com.valcon.cookbook.web.dto.UpdateRecipeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RecipeControllerApi {

    @Operation(summary = "Retrieve all recipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All recipes retrieved")
    })
    ResponseEntity<List<RecipeDto>> findAll();

    @Operation(summary = "Update recipe", description = "Update recipe with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe updated")
    })
    ResponseEntity<RecipeDto> update(@RequestBody @Valid UpdateRecipeDto updated);

    @Operation(summary = "Create recipe", description = "Create recipe with given instructions and ingredients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe created")
    })
    ResponseEntity<RecipeDto> create(@RequestBody @Valid RecipeDto dto);

    @Operation(summary = "Delete recipe", description = "Delete recipe with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe deleted")
    })
    ResponseEntity<Void> delete (@PathVariable Long id);


}
