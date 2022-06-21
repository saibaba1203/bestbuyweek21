Feature: Testing different requests on products option of the best buy application

  Scenario:
    When I create a new product by providing information name type price shipping upc description manufacturer model url image
    And I verify that the product has been created
    And I update a name of the product
    And I verify that the product has been updated
    And I delete the product with same id
    Then I verify that the product has been deleted