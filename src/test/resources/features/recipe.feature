Feature: Recipe tests

  Scenario: Create recipe
    When New recipe is created
      | name      | numberOfServings  | isVegetarian | instructions    |
      | Recipe1   | 10                | false        | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 40 minutes.|
    And ingredients are added to the recipe
      | name      | quantity | recipeId |
      | salt      | 10g      |  1       |
      | chicken   | 300g     |  1       |
    Then Recipe should look like this:
      | name      | numberOfServings  | isVegetarian | instructions    |
      | Recipe1   | 10                | false        | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 40 minutes.|
    Then Ingredients should look like this:
      | name      | quantity | recipeId |
      | salt      | 10g      |  1       |
      | chicken   | 300g     |  1       |

  Scenario: Update existing recipe
    When New recipe is created
      | name      | numberOfServings  | isVegetarian | instructions    |
      | Recipe1   | 10                | false        | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 40 minutes.|
    And ingredients are added to the recipe
      | name      | quantity | recipeId |
      | salt      | 10g      |  1       |
      | chicken   | 300g     |  1       |
    Then Recipe should look like this:
      | name      | numberOfServings  | isVegetarian | instructions    |
      | Recipe1   | 10                | false        | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 40 minutes.|
    When recipe is updated
      | id | name            | numberOfServings  | isVegetarian | instructions    |
      | 1  | RecipeUpdated1  | 8                 | true         | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 50 minutes.|
    Then Recipe should look like this:
      | name            | numberOfServings  | isVegetarian | instructions    |
      | RecipeUpdated1  | 8                 | true         | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 50 minutes.|
