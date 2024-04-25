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

| Use Case | Test | What to input | Expected output | Pre-conditions | Results
| --- | --- |
| All Cases | Invalid, Garbage input when possible to ensure there's no unexpected behavior
| 1.1 | Test if User can create new list | Click on Create New List button in Home Screen and input a list name using just normal ASCII | When I go back to Home and click on the Lists the List should be present and the items should be displayed once I click on it. | N/A
| 1.2 | Test if user can enter name for list using non-English characters | Click on Create New List button in Home Screen and input a list name incorporating non-English characters | When I go back to Home and click on the Lists the List should be present and the items should be displayed once I click on it. | N/A
| 1.3 | Test if user can enter name for list using special and non-English characters (eg. Arabic) | Click on Create New List button in Home Screen and input a list name incorporating non-English characters | When I go back to Home and click on the Lists the List should be present and the items should be displayed once I click on it. | Test 1.2 completes successfully.
| 1.4 | Test with a duplicate name (i.e uniqueness check passes) | Click on Create New List button in Home Screen and input a list name that has already been used | I expect to receive an error and be prompted to input a different name.
| 2.1 | Create a bunch of lists and see if they're all displayed | Go on Create New list, input a list name and create a list, repeat this several times. | Confirm that all lists are displayed when I press on the button to Show Lists. | All of the 1 cases pass.
| 2.2 | Try deleting a list and see if the display updates | Go on the Show All Lists screen and try deleting a list | The list of the lists updates itself and now shows all lists besides the deleted one | Test 2.1 passes
| 2.3 | See if list isn't deleted if the user cancels | In the Show All Lists screen, select a bunch of lists and try deleting them. When confirmation is requested cancel. | Confirm that all the selected lists are still present in the database | Test 2.2 passes
| 3.1 | Select some lists in the database. Ensure the correct list is selected in the system | Go on the screen that shows all Lists, select some lists. Using a test case like 2.2 confirm that the database chooses the correct list. | Actions like those in 2.2 succeed | N/A
| 3.2 | User tries to change which list he selected | Go on the screen that shows all Lists, select some lists, then unselect said lists and select a different combination of lists. Using a test case like 2.2 confirm that the database chooses the correct list. | Actions like those in 2.2 succeed | N/A
| 4.1 | See if user can open and review contents of lists when none exist | Delete all lists (if any exist), then click on the Show Lists button from the homepage. | An error will be displayed indicating the non-existence of a list in the database. | No lists exist.
| 4.2 | See if user can open and review contents of lists when 1 exists | Delete all lists but 1, then click on the Show Lists button from the homepage and click on the one list to show the items. | One list will be displayed | Only one list exists in the database.
| 4.3 | See if user can open and review contents of lists when more than 1 exists | Click on the show lists button from the homepage and click on all the lists and ensure all items are present for each list. | Ensure more than 1 list exists in the database
| 4.4 | The user does not open the list after selecting it and returns to the list collection | Click on the Show all lists screen, select some lists and then go back to the homepage | No list is affected in any way | Ensure some lists exist.
| 5.1 | See if user can rename list when name is not identical | Go the Show all list screen, select a list and try to rename said list. | List is successfully renamed | Several lists exist.
| 5.2 | See if a rename to a name identical to another list is detected and prevented | Go the Show all list screen, select a list and try to rename said list in a way that matches another list. | An error pops up and the list is not renamed | Several lists exist.
| 6.1 | See if user can add new item-type to database when this name does not conflict | Go to the Home screen, press the Create new Item-type button and then input a name | When I go to search by item-type this item-type will be added. | N/A
| 6.2 | See if a name conflict is prevented from being added | Go to the Home screen, press the Create new Item-type button and then input a name that matches the name of an existing item-type | When I go to search by item-type this item-type will be added. | N/A
| 7.1 | See if user can add new item to database when this name does not conflict | Go to the Home screen, press the Create new Item button and then input a name, also select an item type | When I go to search by item this item will be added. | N/A
| 7.2 | See if a name conflict is prevented from being added | Go to the Home screen, press the Create new Item button and then input a name and select an item-type. This name should conflict with another item in the same type (this is done since someone may want to put an item in two categories eg. lettuce can be in the vegetable category as well as a custom favorite foods category) | An error appears and the item is not created | N/A
| 8.1 | User attempt to search for a non-existent item-type | Go to home screen, click on search by item-type. Input a non-existent type | Nothing appears | Some item-types exist
| 8.2 | User searches for an existent item-type | Go to home screen, click on search by item-type. Input a existent type | The item type appears | Some item-types exist
| 9.1 | User attempt to search for a non-existent item | Go to home screen, click on search by item-type. Input a non-existent type | Nothing appears | Some item-types exist
| 9.2 | User searches for an existent item | Go to home screen, click on search by item. Input an existent item | The item appears | Some items exist
| 10.1 | User tries to input an invalid quantity (eg. negative number) when creating an item | Try to create an item, when doing so input an invalid quantity | An error pops up and the item is not created | N/A
| 10.2 | User tries to input an invalid quantity (eg. negative number) when modifying an item | Go to the list of all items within an item-type, sleect an item and try modifying the quantity, when doing so input an invalid quantity | An error pops up and the item is not created | N/A
| 10.3 | User tries to input an invalid characters (eg. chinese characters) | Try to create an item, when doing so input using invalid characters | An error pops up and the item is not created | N/A
| 10.4 | User tries to input an invalid quantity (eg. negative number) when modifying an item | Go to the list of all items within an item-type, sleect an item and try modifying the quantity, when doing so input invalid characters | An error pops up and the item is not created | N/A
| 10.5 | User input a valid quantity, when creating an item | 
| 10.6 | User input a valid quantity, when modifying an item | Go to the list of all items within an item-type, select an item and try modifying the quantity, when doing so input an invalid quantity | The item quantity is updated | N/A

| 11.1 | Try searching by both name and type for an extant item | Try to search for an item both by it's name and within a type | The item appears | The item exists in said type
| 11.2 | Try searching by both name across several types for an extant item | Try to search for an item both by it's name and within those types | the item appears | the item exists in the union of said types
| 11.3 | Try searching by both name across several types for a non-existent item | Try to search for an item both by it's name and within the types | an error appears indicating the non-existence of the item | the item does not exist.
| 11.4 | Try searching by both name and type for a non-existent item | Try to search for an item both by it's name and within a type | an error appears indicating the non-existence of the item | the item does not exist.
| 11.2 | Try searching by both name across several types for an extant item that exists in multiple types | Try to search for an item both by it's name and within those types | the item appears only once | the item exists in the union of said types

| 12.1 | See if a list of items can be retrieved from an artificially created type | Create a New type, add some items to that type then go to the Show all item-types screen, and click on the new type| all items added to that item-type are displayed | The artificial item-type was and can be created.
| 12.2 | Try retrieving from a non-existent or invalid type | Go to the Item-list, when one can choose by inputting a name try retrieving items from an invalid type | an error appears | The inputted item-type does not exist
| 13.1 | Try adding an item to a grocery list when previously selected | Go to search for item, select an item and add it to a list. Then go to the Show All Lists screen, click on the one list. | The item has been added to the selected list | The list exists.
| 13.2 | Try doing so when multiple grocery lists are extant (to test for collisions between lists) | Go to search for item, select an item and add it to a list. Then go to the Show All Lists screen, click on the list it was added to. | The item has been added to the selected list | The list exists.
| 14.1 | See if can select item in the grocery list | Go the lists of all lists, open up a list and select an item | the item is selected | Grocery lists with items exist
| 14.2 | See if can change the selected item in the grocery list | Go the lists of all lists, open up a list and select an item and then change the selected item | the item selected is changed | Grocery lists with items exist
| 14.3 | See if can select more than one item | Go the lists of all lists, open up a list and select more than one item | the items are selected | Grocery lists with items exist
| 14.4 | See if can select more than one item | Go the lists of all lists, open up a list and select more than one item and change the selected items | the items are selected properly | Grocery lists with items exist
| 15.1 | See if User can add item directly to chosen grocery list | Go to the lists of all lists, select a list and try to add an item to the list from that screen | the item is added | The grocery lists exist
| 15.2 | See if there's error check for duplicate is detected and quantity is prompted to be edited | Go to the lists of all lists, select a list and try to add an item to the list from that screen, when adding an item ensure the item is already present | an error appears and the user is prompted to modify the quantity of the item | The grocery lists exist
| 16.1 | Try to delete an item and check for its presence in the list | Go to the lists of all lists, select a list and try to delete an item from the list | the item is deleted from the list | the grocery lists exist
| 16.2 | Try to delete an item across multiple lists | Go to the lists of all lists, press a button to have all items from all lists appear. Try to delete an item across multiple lists | Item is deleted from all lists | the item exists in multiple lists
| 17.1 | Modify the quantity of extant items in the grocery list | Go to the list of all lists screen, select an item and try modifying its quantity | The quantity of the item is updated | the item exists in the list
| 17.2 | Try modifying quantity of non-existent item (expect error) | Go to the list of all lists screen, select a non-present item and try modifying its quantity | Error will be displayed | The item doesn't exist in the list
| 18.1 | Modify the quantity of extant items in the grocery list | Go to the list of all lists screen, select an item and try modifying its quantity | The quantity of the item is updated | the item exists in the list
| 18.2 | Try modifying quantity of non-existent item (expect error) |Go to the list of all lists screen, select a non-present item and try modifying its quantity | Error will be displayed | The item doesn't exist in the list
| 19.1 | Try to mark an item has completed and check if it is | Go to the list of all lists screen, select an item and mark it as completed. | The item is marked as completed | items exist in the list.
| 20.1 | Try to unmark an item has completed and check if it is | Go to the list of all lists screen, select an item and mark it as completed, after confirming test 19.1 ensure the item can be unmarked. | The item is unmarked | items exist in the list.
| 21.1 | Try sorting items on a grocery list by type and make sure only the selected list(s) are affected | On the list of all lists press the button to sort the items by item-type in alphabetical order. | Chosen (and only the chosen) lists should be sorted in said manner  | Multiple lists exist
| 22.1 | Make sure changes are saved | Close and reopen the app | Changes persist upon closing and reopening the app. | All prior tests pass
