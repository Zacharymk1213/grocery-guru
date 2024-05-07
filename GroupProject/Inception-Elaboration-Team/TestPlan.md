# Test Plan



**Author**: Zachary Kleiman

## 1 Testing Strategy

### 1.1 Overall strategy

All manner of testing (unit, integration, system and regression) will be performed manually by Zachary Kleiman. This will be done using Android Studio's emulator. All testing activities will be tracked using a test management tool (in this case a table in this Github file), which will allow us to track which tests have been run, their results, and any bugs that have been found. This will ensure that all necessary testing is performed and that any issues are addressed promptly.


### 1.2 Test Selection


-Try all possible forms of input for any text receiving mechanism to ensure that all forms of input are handled
-Test all features that will be in the app
-Tests are ordered with reference to the usecasemodel as of 0cfbd59c958244598b05d9bfc7ba54de64ea76d8

### 1.3 Adequacy Criterion


Coverage: Each use case should have at least one corresponding test case. This ensures that all functionality is tested.

Boundary Conditions: Test cases should cover both the typical usage scenarios and edge cases. This includes testing with minimum, maximum, just below and just above the limit inputs, and with invalid or unexpected inputs.

Uniqueness: Test cases should not be redundant. Each test case should verify a different aspect of the system.

Effectiveness: Test cases should be able to detect potential errors. This can be measured by injecting known faults into the system and verifying that the test cases can catch them.

Maintainability: Test cases should be written in a way that they are easy to update when the system changes.

Readability: Test cases should be clear and easy to understand, with a well-defined expected outcome.

Independence: Each test case should be independent of others. The result of one test case should not affect the result of another test case. A lot of tests in earlier deliverables were removed because of this.

Traceability: Each test case should be traceable to its corresponding requirement or use case.


### 1.4 Bug Tracking

Bugs and Enhancement requests will be tracked  using Github's issue tracking system.
### 1.5 Technology

Test will be done manually using the Android Studio emulator.
## 2 Test Cases

Tests should be run roughly sequentially.

| Use Case   | Test  | What to input                                                                                                                            | Expected output                                                                                                                    | Pre-conditions             | Results |
|------------|-------|------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|----------------------------|---------|
| 1  | 1.1   | Click on Create New List button in Home Screen and input a list name using just normal ASCII                                              | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.     | N/A                        | ✅      |
| 1  | 1.2   | Click on Create New List button in Home Screen and input a list name using just normal ASCII and special characters                                             | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.     | N/A                        | ✅      |
|   1         | 1.3   | Click on Create New List button in Home Screen and input a list name incorporating non-English characters                                 | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.      | N/A                        | ✅       |
|    1        | 1.4   | Click on Create New List button in Home Screen and input a list name incorporating non-English characters and special characters                               | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.      | N/A | ✅       |
|    1        | 1.5   | From the Home Screen click on Create New List , input a list name and create a list, repeat this several times (differs from other tests in creating multiple lists)                                                    | Confirm that all lists are displayed when I go to the home screen                                                    | N/A   | ✅      |
|   2         | 2.1   | Click on a list from the home screen and try deleting said list                                                                                    | The list of the lists updates itself and now shows all lists besides the deleted one                                              | At least one list exists            | ✅       |
|   2         | 2.2   | Click on a list from the home screen and try deleting multiple lists                                                                                    | The list of the lists updates itself and now shows all lists besides the deleted ones                                              | several lists exist            | ✅       |
|    3        | 3.1   | Delete all lists but 1, then go to the homepage and click on the one list to show the items.                | One list will be displayed                                                                                                        | Only one list exists in the database. | ✅        |
|    3        | 3.2   | Go to the Home Screen and click on all the lists and ensure all items are present for each list.              | All items are present in each list                                                                   | More than 1 list exists in the database                                            | ✅        |
|     4       | 4.1   | From the Home Screen, select a list and try to rename said list.                                                                   | List is successfully renamed                                                                                                      | At least a single list exists.       | ✅       |
|   5         | 5.1   | From the New Database Item Screen, press the Create new Item button and then input a name, also select an item type                                   | When I go to search by item this item will be added.                                                                              | N/A                        | ✅       |
|   5         | 5.2   | From the New Database Item Screen, press the Create new Item button and then input a name and select an item-type. This name should conflict with another item in the same type | An error appears and the item is not created                                                                                      | N/A                        | ✅       |
|     6        | 6.1   | Go to Home Screen, click on search by item. Input a non-existent item                                                                | Nothing appears                                                                                                                   | some items exist      | ✅        |
|     6        | 6.2   | Go to Home Screen, click on search by item. Input an existent item                                                                        | The item appears                                                                                                                  | Some items exist           | ✅        |
|     7        | 7.1  | Click on Browse Type from the Home Screen and click on a type                      | all items added to that item-type are displayed                                                                                   | Some items with item-types exist. | ✅       |
|     8    |  8.1  | Go to the home screen, select a list and try to add an item to the list from that screen                                           | the item is added                                                                                                                 | The grocery lists exist    | ✅       |
|     8     | 8.2  | Go to the home screen, select a list and try to add an item to the list from that screen, when adding an item ensure the item is already present | an error appears and you're told the item is already present | The grocery lists exist and the item being added is already present    | ✅       |
|     9       | 9.1  | Go to the home screen, select a list and try to delete an item from the list                                                       | the item is deleted from the list                                                                                                 | the grocery lists exist and an item is present in the selected list   | ✅       |
|     10      | 10.1  | Go to the home screen, select an item and try modifying its quantity in the grocery list                                                       | The quantity of the item is updated                      | the item exists in the list| ✅       |
|     11      | 11.1  | Go to the home screen, Click on a list that has items from multiple types in it and click on Browse Type from there and ensure that only the items added to the list display when one click on the types                                                      | Only the items added to the list display when one clicks on the types                                                                                               | A list with items from multiple types exists| ✅       |
|     12      | 12.1  | Close and reopen the app                                                                                                                  | Changes persist upon closing and reopening the app.                                                                               | All prior tests pass       | ✅       |
