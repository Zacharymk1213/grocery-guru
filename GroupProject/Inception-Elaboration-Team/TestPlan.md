# Test Plan



**Author**: Zachary Kleiman

## 1 Testing Strategy

### 1.1 Overall strategy


### Unit Testing
Unit testing will be performed by Zachary Kleiman as the individual developers work on their respective components. This involves testing individual functions and methods to ensure they perform as expected. We will use a unit testing framework appropriate for our programming language. In this case Junit.

### Integration Testing
Once individual components have been unit tested, we will perform integration testing to ensure that the components work together correctly. This will be done by Zachary Kleiman who'll run integration tests. We will use mock objects and stubs as needed to isolate the components being tested.

### System Testing
System testing will be performed by Zachary Kleiman. This involves testing the entire system to ensure that it meets the specified requirements. This includes functional testing to verify that the system does what it's supposed to do, as well as non-functional testing to check performance, usability, security, etc.

### Regression Testing
Regression testing will be performed after each major update to ensure that existing functionality has not been broken. This will be done by Zachary, using manual testing.

All testing activities will be tracked using a test management tool (in this case a table in this Github file), which will allow us to track which tests have been run, their results, and any bugs that have been found. This will ensure that all necessary testing is performed and that any issues are addressed promptly.


### 1.2 Test Selection


-Try all possible forms of input for any text receiving mechanism to ensure that all forms of input are handled

### 1.3 Adequacy Criterion


Coverage: Each use case should have at least one corresponding test case. This ensures that all functionality is tested.

Boundary Conditions: Test cases should cover both the typical usage scenarios and edge cases. This includes testing with minimum, maximum, just below and just above the limit inputs, and with invalid or unexpected inputs.

Uniqueness: Test cases should not be redundant. Each test case should verify a different aspect of the system.

Effectiveness: Test cases should be able to detect potential errors. This can be measured by injecting known faults into the system and verifying that the test cases can catch them.

Maintainability: Test cases should be written in a way that they are easy to update when the system changes.

Readability: Test cases should be clear and easy to understand, with a well-defined expected outcome.

Independence: Each test case should be independent of others. The result of one test case should not affect the result of another test case.

Traceability: Each test case should be traceable to its corresponding requirement or use case.


### 1.4 Bug Tracking

Bugs and Enhancement requests will be tracked  using Github's issue tracking system.
### 1.5 Technology

Test will be done manually using the Android Studio emulator.
## 2 Test Cases

Tests should be run roughly sequentially. If one test fails to check several other tests will likely fail so it makes sense to fix that bug before continuing. 
Fill in all possible bugs I can think of as this develops

| Use Case   | Test  | What to input                                                                                                                            | Expected output                                                                                                                    | Pre-conditions             | Results |
|------------|-------|------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|----------------------------|---------|
| 1  | 1.1   | Click on Create New List button in Home Screen and input a list name using just normal ASCII                                              | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.     | N/A                        | ✅      |
|   1         | 1.2   | Click on Create New List button in Home Screen and input a list name incorporating non-English characters                                 | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.      | N/A                        | ✅       |
|    1        | 1.3   | Click on Create New List button in Home Screen and input a list name incorporating non-English characters and special characters                               | When I go back to Home the list should be present in the lists screen and the items should be displayed once I click on it.      | Test 1.2 completes successfully | ✅       |
|    1        | 1.4   | Go on Create New list, input a list name and create a list, repeat this several times (differs from other tests in creating multiple lists)                                                    | Confirm that all lists are displayed when I go to the home screen                                                    | All of the 1 cases pass.   | ✅      |
|   2         | 2.1   | Click on a list from the home screen and try deleting said list                                                                                    | The list of the lists updates itself and now shows all lists besides the deleted one                                              | Test 2.1 passes            | ✅       |
|   2         | 2.2   | Click on a list from the home screen and try deleting multiple lists                                                                                    | The list of the lists updates itself and now shows all lists besides the deleted ones                                              | Test 2.2 passes            | ✅       |

|    3        | 3.1   | Go on the screen that shows all Lists, select some lists. Using a test case like 2.2 confirm that the database chooses the correct list. | Actions like those in 2.2 succeed                                                                                                 | N/A                        | ✅       |
|    3        | 3.2   | Go on the screen that shows all Lists, select some lists, then unselect said lists and select a different combination of lists.           | Actions like those in 2.2 succeed                                                                                                 | N/A                        | ✅       |
|    4        | 4.1   | Delete all lists but 1, then go to the homepage and click on the one list to show the items.                | One list will be displayed                                                                                                        | Only one list exists in the database. | ✅        |
|    4        | 4.2   | Go to the homepage and click on all the lists and ensure all items are present for each list.              | All items are present in each list                                                                   | Ensure more than 1 list exists in the database                                            | ✅        |
|     5       | 5.1   | From the Homepage, select a list and try to rename said list.                                                                   | List is successfully renamed                                                                                                      | At least a single list exists.       | ✅       |
|   6         | 6.1   | From the new database item screen, press the Create new Item button and then input a name, also select an item type                                   | When I go to search by item this item will be added.                                                                              | N/A                        | ✅       |
|   6         | 6.2   | From the new database item screen, press the Create new Item button and then input a name and select an item-type. This name should conflict with another item in the same type | An error appears and the item is not created                                                                                      | N/A                        | ✅       |
|     7        | 7.1   | Go to home screen, click on search by item. Input a non-existent item                                                                | Nothing appears                                                                                                                   | Some item-types exist      | ✅        |
|     7        | 7.2   | Go to home screen, click on search by item. Input an existent item                                                                        | The item appears                                                                                                                  | Some items exist           | ✅        |
|     8        | 8.1  | Create a New type, add some items to that type then go to the Show all item-types screen, and click on the new type                      | all items added to that item-type are displayed                                                                                   | The artificial item-type was and can be created. | ✅       |
|     9      | 9.1  | Go the lists of all lists, open up a list and select an item                                                                              | the item is selected                                                                                                              | Grocery lists with items exist | ✅        |
|     9       | 9.2  | Go the lists of all lists, open up a list and select an item and then change the selected item                                            | the item selected is changed                                                                                                      | Grocery lists with items exist | ✅         |
|     9        | 9.3  | Go the lists of all lists, open up a list and select more than one item                                                                   | the items are selected                                                                                                            | Grocery lists with items exist | ✅         |
|     9       | 9.4  | Go the lists of all lists, open up a list and select more than one item and change the selected items                                     | the items are selected properly                                                                                                   | Grocery lists with items exist | ✅         |
|     10    | 10.1  | Go to the lists of all lists, select a list and try to add an item to the list from that screen                                           | the item is added                                                                                                                 | The grocery lists exist    | ✅       |
|     10     | 10.2  | Go to the lists of all lists, select a list and try to add an item to the list from that screen, when adding an item ensure the item is already present | an error appears and you're told the item is already present | The grocery lists exist and the item being added is already present    | ✅       |
|     11       | 11.1  | Go to the lists of all lists, select a list and try to delete an item from the list                                                       | the item is deleted from the list                                                                                                 | the grocery lists exist    | ✅       |
|     12      | 12.1  | Go to the home screen, select an item and try modifying its quantity in the grocery list                                                       | The quantity of the item is updated                                                                                               | the item exists in the list| ✅       |
|     13      | 13.1  | Close and reopen the app                                                                                                                  | Changes persist upon closing and reopening the app.                                                                               | All prior tests pass       | ✅       |
