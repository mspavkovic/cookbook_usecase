Feature: Recipe tests

  Scenario: Create recipe
    Given following ingredients
      | name      | quantity |
      | salt      | 10g      |
      | chicken   | 300g     |
    When New recipe is created
      | name      | numberOfServings  | isVegetarian | instructions    |
      | Recipe1   | 10                | false        | Put the chicken in a baking dish and rub with a little olive oil. Tuck the thyme around the chicken, season well with sea salt and black pepper and roast for 40 minutes.|
    And ingredients are added to the recipe
        | id      | recipeId |
        | 1       |  1       |
        | 2       |  1       |
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

#  Scenario: No payment methods configured and payment link creation fails
#    When Payment link is created it fails
#      | sellerUuid                           | relationUuid                         | amount | description    | flow    | paymentMethods               |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | 100.00 | Payment link 1 | SESSION | IDEAL,CREDIT_CARD,GOOGLE_PAY |
#
#  Scenario: Paid payment link
#    Given Payment methods "IDEAL,CREDIT_CARD" are configured for merchant of seller "ce7835a9-68fc-4406-9e6e-7d073af12457"
#    And Payment link is created
#      | sellerUuid                           | relationUuid                         | amount | description    | flow    | paymentMethods    | successRedirectUrl     | failureRedirectUrl     | paymentWebhookUrl                    | paymentLinkWebhookUrl                    |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | 100.00 | Payment link 1 | SESSION | IDEAL,CREDIT_CARD | my-energie.nl/redirect | my-energie.nl/redirect | http://localhost:port/paymentWebhook | http://localhost:port/paymentLinkWebhook |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | CREATED | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And For payment link with description "Payment link 1" url is set to "http://payment-url.com/payment/6c20beed9d9e"
#    When Payment with link description "Payment link 1" is started
#    Then Payments should look like this
#      | status    | amount | sellerUuid                           |
#      | INITIATED | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 |
#    And Payment events are emitted
#      | status    | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | INITIATED | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#    When Payment completed is triggered for payment with description "Payment link 1"
#    Then Payment links should look like this:
#      | status    | flow    | sellerUuid                           | relationUuid                         | currency | amount | description    | locale |
#      | PROCESSED | SESSION | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | EUR      | 100.00 | Payment link 1 | en-GB  |
#    And Payment link events are emitted
#      | paymentLinkDescription | status    | paymentLinkUrl                                | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | PROCESSED | http://payment-url.com/payment/6c20beed9d9e   | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment link webhook is called for payment link with description "Payment link 1"
#    And Payments should look like this
#      | status                | amount | sellerUuid                           |
#      | AWAITING_CONFIRMATION | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 |
#    And Payment events are emitted
#      | status                | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | AWAITING_CONFIRMATION | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#    When Webhook for payment with description "Payment link 1" and status "AWAITING_CONFIRMATION" is hit with code "AUTHORISATION", success "true" and payment method "ideal"
#    Then Payments should look like this
#      | status | amount | sellerUuid                           | paymentMethod |
#      | PAID   | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 | IDEAL         |
#    And Payment events are emitted
#      | status | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | PAID   | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#    And Journal creation commands are emitted
#      | relationUuid                         | description    | journalType | transactions                                                  |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | Payment link 1 | PAYMENT     | -100.00->ACCOUNT_RECEIVABLE,100.00->IDEAL_PAYMENTS_IN_TRANSIT |
#
#  Scenario: Failed payment link
#    Given Payment methods "IDEAL,CREDIT_CARD" are configured for merchant of seller "ce7835a9-68fc-4406-9e6e-7d073af12457"
#    And Payment link is created
#      | sellerUuid                           | relationUuid                         | amount | description    | flow    | paymentMethods    | successRedirectUrl     | failureRedirectUrl     | paymentWebhookUrl                    | paymentLinkWebhookUrl                    |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | 100.00 | Payment link 1 | SESSION | IDEAL,CREDIT_CARD | my-energie.nl/redirect | my-energie.nl/redirect | http://localhost:port/paymentWebhook | http://localhost:port/paymentLinkWebhook |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | CREATED | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And For payment link with description "Payment link 1" url is set to "http://payment-url.com/payment/6c20beed9d9e"
#    When Payment with link description "Payment link 1" is started
#    Then Payments should look like this
#      | status    | amount | sellerUuid                           |
#      | INITIATED | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 |
#    And Payment events are emitted
#      | status    | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | INITIATED | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#    When Payment completed is triggered for payment with description "Payment link 1"
#    Then Payment links should look like this:
#      | status    | flow    | sellerUuid                           | relationUuid                         | currency | amount | description    | locale |
#      | PROCESSED | SESSION | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | EUR      | 100.00 | Payment link 1 | en-GB  |
#    And Payment link events are emitted
#      | paymentLinkDescription | status    | paymentLinkUrl                                | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | PROCESSED | http://payment-url.com/payment/6c20beed9d9e   | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment link webhook is called for payment link with description "Payment link 1"
#    And Payments should look like this
#      | status                | amount | sellerUuid                           |
#      | AWAITING_CONFIRMATION | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 |
#    And Payment events are emitted
#      | status                | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | AWAITING_CONFIRMATION | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#    When Webhook for payment with description "Payment link 1" and status "AWAITING_CONFIRMATION" is hit with code "AUTHORISATION", success "false" and payment method "ideal"
#    Then Payments should look like this
#      | status | amount | sellerUuid                           | paymentMethod |
#      | FAILED | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 | IDEAL         |
#    And Payment events are emitted
#      | status | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | FAILED | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
#
#  Scenario: Canceled payment link
#    Given Payment methods "IDEAL,CREDIT_CARD" are configured for merchant of seller "ce7835a9-68fc-4406-9e6e-7d073af12457"
#    And Payment link is created
#      | sellerUuid                           | relationUuid                         | amount | description    | flow    | paymentMethods    | successRedirectUrl     | failureRedirectUrl     | paymentWebhookUrl                    | paymentLinkWebhookUrl                    |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | 100.00 | Payment link 1 | SESSION | IDEAL,CREDIT_CARD | my-energie.nl/redirect | my-energie.nl/redirect | http://localhost:port/paymentWebhook | http://localhost:port/paymentLinkWebhook |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | CREATED | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And For payment link with description "Payment link 1" url is set to "http://payment-url.com/payment/6c20beed9d9e"
#    When Cancel payment link with description "Payment link 1" is triggered
#    Then Payment links should look like this:
#      | status  | flow    | sellerUuid                           | relationUuid                         | currency | amount | description    | locale |
#      | DELETED | SESSION | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | EUR      | 100.00 | Payment link 1 | en-GB  |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentLinkUrl                                | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | DELETED | http://payment-url.com/payment/6c20beed9d9e   | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment link webhook is called for payment link with description "Payment link 1"
#
#  Scenario: Expired payment link
#    Given Payment methods "IDEAL,CREDIT_CARD" are configured for merchant of seller "ce7835a9-68fc-4406-9e6e-7d073af12457"
#    And Payment link is created
#      | sellerUuid                           | relationUuid                         | amount | description    | flow    | paymentMethods    | successRedirectUrl     | failureRedirectUrl     | paymentWebhookUrl                    | paymentLinkWebhookUrl                    |
#      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | 100.00 | Payment link 1 | SESSION | IDEAL,CREDIT_CARD | my-energie.nl/redirect | my-energie.nl/redirect | http://localhost:port/paymentWebhook | http://localhost:port/paymentLinkWebhook |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | CREATED | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And For payment link with description "Payment link 1" url is set to "http://payment-url.com/payment/6c20beed9d9e"
#    And Payment with link description "Payment link 1" is started
#    And Payment events are emitted
#      | status    | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | INITIATED | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Payment link with description "Payment link 1" expires on "2020-12-12T16:30:30"
#    And Payment with description "Payment link 1" expires on "2020-12-12T16:30:30"
#    When Expire payment link with description "Payment link 1" is triggered
#    Then Payment links should look like this:
#      | status  | flow    | sellerUuid                           | relationUuid                         | currency | amount | description    | locale |
#      | EXPIRED | SESSION | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 | EUR      | 100.00 | Payment link 1 | en-GB  |
#    And Payment link events are emitted
#      | paymentLinkDescription | status  | paymentLinkUrl                                | paymentMethods    | sellerId                             | relationId                           |
#      | Payment link 1         | EXPIRED | http://payment-url.com/payment/6c20beed9d9e   | IDEAL,CREDIT_CARD | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment link webhook is called for payment link with description "Payment link 1"
#    And Payments should look like this
#      | status  | amount | sellerUuid                           |
#      | EXPIRED | 100.00 | ce7835a9-68fc-4406-9e6e-7d073af12457 |
#    And Payment events are emitted
#      | status  | amount | hasRefunds | sellerUuid                           | relationUuid                         |
#      | EXPIRED | 100.00 | false      | ce7835a9-68fc-4406-9e6e-7d073af12457 | 8405f388-c941-41e7-8a68-b86765d69754 |
#    And Registered payment webhook is called for payment with description "Payment link 1"
