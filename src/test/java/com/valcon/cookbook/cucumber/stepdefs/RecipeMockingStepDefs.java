package com.valcon.cookbook.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.valcon.cookbook.cucumber.dto.IngredientTestDto;
import com.valcon.cookbook.cucumber.dto.RecipeTestDto;
import com.valcon.cookbook.cucumber.dto.UpdateRecipeTestDto;
import com.valcon.cookbook.domain.ingredient.IngredientService;
import com.valcon.cookbook.domain.recipe.RecipeService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RecipeMockingStepDefs {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @When("New recipe is created")
    public void recipeIsCreated(final RecipeTestDto recipeTestDto) {
        recipeService.save(RecipeTestDto.map(recipeTestDto));
    }

    @When("recipe is updated")
    public void recipeIsUpdated(final UpdateRecipeTestDto updateRecipeTestDto) {
        recipeService.update(UpdateRecipeTestDto.map(updateRecipeTestDto));
    }

    @And("ingredients are added to the recipe")
    public void ingredientsAdded(final List<IngredientTestDto> ingredientTestDtos) {
        ingredientTestDtos.forEach(ingredientTestDto -> ingredientService.save(IngredientTestDto.map(ingredientTestDto)));
    }

    @Then("Recipe should look like this:")
    public void recipeShouldLookLikeThis(final List<RecipeTestDto> recipeTestDtos) {
        final List<RecipeTestDto> recipeFromDatabase = recipeService.findAll()
                                                                                       .stream()
                                                                                       .map(RecipeTestDto::map)
                                                                                       .collect(Collectors.toList());
        assertThat(recipeFromDatabase)
                .containsExactlyInAnyOrderElementsOf(recipeTestDtos);
    }

    @Then("Ingredients should look like this:")
    public void ingredientsShouldLookLikeThis(final List<IngredientTestDto> ingredientTestDtos) {
        final List<IngredientTestDto> ingredientFromDatabase = ingredientService.findAll()
                                                                    .stream()
                                                                    .map(IngredientTestDto::map)
                                                                    .collect(Collectors.toList());
        assertThat(ingredientFromDatabase)
                .containsExactlyInAnyOrderElementsOf(ingredientTestDtos);
    }
//    @Given("Payment methods {string} are configured for merchant of seller {string}")
//    public void paymentMethodsAreConfiguredForLabel(final String paymentMethods, final String sellerUuid) {
//        recipeService.save()
//        for (String method : paymentMethods.trim().split(",")) {
//            final SellerPaymentMethod sellerPaymentMethod = sellerPaymentMethodService.findBySellerUuidAndPaymentMethod(UUID.fromString(sellerUuid),
//                                                                                                                        BasicPaymentMethod.PaymentMethod.valueOf(method))
//                                                                                      .get();
//            AdyenCheckoutMockService.configuredPaymentMethods.add(sellerPaymentMethod.getProviderPaymentMethod().getProviderName());
//        }
//    }
}
