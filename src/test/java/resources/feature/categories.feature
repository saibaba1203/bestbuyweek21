Feature:Testing different request on the best buy api
Scenario Outline: Check if the best buy application can be accessed by users
When I create a new category by providing the information id "<id>" name "<name>"
  And I verify that the category has been created
  And I updated a name of the category
  And I verify that the category name has been updated
  And I deleted category with same id
  Then I verify that the category has been deleted

  Examples:
  |id |name|
  | Catalog01-|My Unique Gifts Ideas|