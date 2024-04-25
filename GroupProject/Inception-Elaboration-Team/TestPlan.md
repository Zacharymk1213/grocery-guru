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
Regression testing will be performed after each major update to ensure that existing functionality has not been broken. This will be done by Zachary, using manual tests.

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

Test Cases will be laid out manually in 2.

## 2 Test Cases

**Before any of these test cases can be run, copy the local.properties file into the GLM directory from the app subdirectory. Otherwise, the app will not launch.**
Fill in all possible bugs I can think of as this develops

| Use Case | Test |
| --- | --- |
| All Cases | Invalid, Garbage input when possible to ensure there's no unexpected behavior |
| 1 | Test if User can create new list |
| 1 | Test if user can enter name |
| 1 | Test with a duplicate name (i.e uniqueness check passes) |
| 2 | Create a bunch of lists and see if they're all displayed |
| 2 | Try deleting a list and see if the display updates |
| 2 | See if list isn't deleted if the user cancels |
| 3 | Select some lists in the database. Ensure the correct list is selected in the system |
| 3 | User tries to change which list he selected |
| 4 | See if user can open and review contents of lists when none exist (expect error) |
| 4 | See if user can open and review contents of lists when 1 exists |
| 4 | See if user can open and review contents of lists when more than 1 exists |
| 4 | The user does not open the list after selecting it and returns to the list collection |
| 5 | See if user can rename list when name is not identical |
| 5 | See if a rename to a name identical to another list is detected and prevented |
| 6 | See if user can add new item-type to database when this name does not conflict |
| 6 | See if a name conflict is prevented from being added |
| 7 | See if user can add new item to database when this name does not conflict |
| 7 | See if a name conflict is prevented from being added |
| 8 | User attempt to search for a non-existent item-type |
| 8 | User searches for an existent item-type |
| 9 | User attempt to search for a non-existent item |
| 9 | User searches for an existent item |
| 10 | User tries to input an invalid quantity (eg. negative number) |
| 10 | User input a valid quantity, check if updated |
| 11 | Try searching by both name and type |
| 11 | See if expected result if there's some items |
| 11 | See if expected results when there's no matches |
| 12 | See if a list of items can be retrieved from an artificially created type |
| 12 | Try retrieving from a non-existent or invalid type |
| 13 | Try adding an item to a grocery list when previously selected |
| 13 | Try doing so when multiple grocery lists are extant (to test for collisions between lists) |
| 14 | See if can select item in the grocery list |
| 14 | See if can change the selected item in the grocery list |
| 14 | See if can select more than one item |
| 15 | See if User can add item directly to chosen grocery list |
| 15 | See if error check for duplicate is detected and quantity is prompted to be edited |
| 16 | Try to delete an item and check for its presence in the list |
| 16 | Try to delete an item across multiple lists |
| 17 | Modify the quantity of extant items in the grocery list |
| 17 | Try modifying quantity of non-existent item (expect error) |
| 18 | Modify the quantity of extant items in the grocery list |
| 18 | Try modifying quantity of non-existent item (expect error) |
| 19 | Try to mark an item has completed and check if it is |
| 20 | Try to unmark an item has completed and check if it is |
| 21 | Try sorting items on a grocery list by type and make sure only the selected list(s) are affected |
| 22 | Make sure changes are saved |
