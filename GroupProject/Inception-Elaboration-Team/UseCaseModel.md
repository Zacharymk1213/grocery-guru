# Use Case Model

**Author**: \<Lijie Wan\, \Zachary Kleiman\>

## 1 Use Case Diagram

The use case diagram visually represents the interaction between the user (actors) and the system (grocery list application). It includes the actor who is a customer in this case. The use cases connected to the actor illustrate all the functionalities such as "createList", "deleteList", "checkOff", "clearCheckOff", "addItem", and "updateQuantity".

![](Diagrams/Team2%20Use-case%20Diagram.png)

## 2 Use Case Descriptions

### Use Case 1:

| Use Case | Create List |
|----|----| 
| **Requirements** | Allow the user to create a new named list within the system for organizing and tracking items.|
| **Pre-conditions** | N/A |
| **Post-conditions** | A new list with a specified name is created and appears within the user's account. The list is empty and ready for items to be added. |
| **Scenarios** | **Normal:**<br>1. User selects the option to create a new list.<br>2. The user enters a name for the list in the system.<br>3. The system checks that the name is unique.<br>4. The system creates the list for the user.<br>**Alternate:**<br>1. The user enters an existing name. <br>2. The system prompts the user to enter a different name.<br>3. The user gives a new name and the system creates the list.<br>**Exceptional:** <br>1. The user receives an error message.<br>2. The system encounters an issue while creating the list.<br>|

### Use Case 2:

| Use Case | Delete List|
|----|----| 
| **Requirements** | Allow the user to permanently remove an existing list in their account. This action can contain a confirmation step to prevent accidental deletions.| 
| **Pre-conditions** | N/A |
| **Post-conditions** | The selected list is removed from the user's account and the system's storage. All the data in the list is no longer accessible.|
| **Scenarios**| **Normal:**<br>1. The user accesses the lists area and views all existing lists.<br>2. The user selects the list they intend to delete.<br>3. The system prompts the user to confirm the deletion to prevent accidental data loss.<br>4. The system removes the list.<br>**Alternate:**<br>1. The user cancels the deletion after the confirmation prompt.<br>2. The system cancels the deletion and retains the list without changes.<br>**Exceptional:**<br>1. The user confirms deletion, but the system encounters an error.<br>2. The system displays an error message.<br>3. The user is prompted to try again later or contact customer support for assistance.<br>|

### Use Case 3:

| Use Case | Open List|
|----|----| 
| **Requirements** | User must have the ability to open and review contents including items and details of any lists they have created. | 
| **Pre-conditions** | The User has created at least one list created and has one list selected. |
| **Post-conditions** | The selected list has been opened and the user can view all the items and details. |
| **Scenarios** | **Normal:**<br>1. The user navigates to their collection of lists and selects one.<br>2. The user clicks the open list option.<br>3. The system displays all the content of the list.<br>**Alternate:**<br>The user does not open the list after selecting it and returns to the list collection.<br>**Exceptional:**<br>1. The user clicks the open list option, but the system encounters a loading error.<br>2. The system displays an error message to inform the user to try to open it again.<br>3. If the error still exists, the user is advised to contact support.<br>|
  
### Use Case 4:

| Use Case | Rename List|
|----|----| 
| **Requirements** | Allow users to change the name of any list they have created. | 
| **Pre-conditions** | User can access their existing lists and have one list selected to perform the next action. |
| **Post-conditions** | The list's name is updated to a new name. |
| **Scenarios** | **Normal:**<br>1. The user selects the list they wish to change the name with.<br>2. The user clicks the rename list option.<br>3. The user inputs a new unique name.<br>4. The system checks the uniqueness of the new name.<br>5. The system updates the list's name and confirms the change to the user.<br>**Exceptional:**<br>1. The system encounters an issue when attempting to update the list's name.<br>2. The system displays an error message and suggests the user try again.<br>3. If the error still exists, the user is advised to contact customer support for further assistance.<br>|

### Use Case 5:

| Use Case | Add Entry |
|----|----| 
| **Requirements** | User can add new items to the system's database. | 
| **Pre-conditions** | User is able to modify the database. |
| **Post-conditions** | The database is updated with new items. |
| **Scenarios** | **Normal:**<br>1. The user navigates to the database dashboard.<br>2. The user selects the option to create a new database entry.<br>3. The user enters the required information needed for the new item.<br>4. The system checks the information.<br>5. The system adds the new item to the database.<br>**Exceptional:**<br>1. If the information the user enters is not complete or incorrect, the system displays an error message and requests the correct information.<br>2. If there is a system or database error during the process, the system prompts the user to try again.<br>|

### Use Case 6:

| Use Case | Search Name |
|----|----| 
| **Requirements** | User should be able to search items by their name in the database. | 
| **Pre-conditions** | Items in the database have unique names and the user can access the database. |
| **Post-conditions** | Search results match the search name. |
| **Scenarios** | **Normal:**<br>1. The user accesses the item search interface in the database.<br>2. The user enters the name of the item and clicks the search type option.<br>3. The system processes the query.<br>4. The system displays the items that match the input name or partial name to the dashboard.<br>**Alternate:**<br>If no items match the search name, then the system informs the user and suggests user add the new item to the database.<br>**Exceptional:**<br>1. The operation encounters an error due to system issues and the system prompts the user to try again later.<br>2. If the problem persists, the system advises the user to contact the support.<br>|


### Use Case 7:

| Use Case | Get Items Of Type |
|----|----| 
| **Requirements** | The system must allow users to retrieve a list of items from the database that are categorized under a type to their grocery lists. | 
| **Pre-conditions** | The database contains items categorized by various types. |
| **Post-conditions** | A list of items that match the specific type is displayed on the dashboard for the user. |
| **Scenarios** | **Normal:**<br>1. The user navigates to the search interface.<br>2. The user enters a type and clicks the search option.<br>3. The system retrieves all items matching the entered type.<br>4. The system displays the result on the dashboard.<br>**Alternate:**<br>If there are no items found, then the system informs the user and suggests the user add a new item to the database or check other types.<br>**Exceptional:**<br>1. The system encounters an error while attempting to retrieve items by type.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>| 
    
### Use Case 8:

| Use Case | Add Items |
|----|----|  
| **Requirements** | The user is allowed to directly add items to their chosen grocery list. | 
| **Pre-conditions** | The item that needs to be added is selected, and the user has an active grocery list.|
| **Post-conditions** | The new item is added to the chosen list. |
| **Scenarios** | **Normal:**<br>1. The user searches the item in the database and then selects the item.<br>2. The user inputs the details of the new item and then clicks the add items option.<br>3. The system validates the new item and then adds it to the list.<br>**Alternate:**<br>If the user attempts to add an item that already exists on the list, the system prompts them to update the existing item’s quantity instead of adding a duplicate.<br>**Exceptional:**<br>1. The system encounters an issue while adding the item.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>| 
  
### Use Case 9:

| Use Case | Delete Item |
|----|----|  
| **Requirements** | The user is allowed to directly remove items on their chosen grocery list.  | 
| **Pre-conditions** | The item that needs to be deleted is selected, and the user has a grocery list that is active. |
| **Post-conditions** | The chosen item has been deleted. |
| **Scenarios** | **Normal:**<br>1. The user views the items on their list and selects the item they wish to remove.<br>2. The user clicks the delete item option.<br>3. The system confirms the user's intent to delete the item.<br>4. The system removes the selected items.<br>**Exceptional:**<br>1. The system encounters an issue while deleting the item.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>|
  

  
### Use Case 10:

| Use Case | Change Quantity |
|----|----| 
| **Requirements** | The User is allowed to modify the number of items on their grocery list. | 
| **Pre-conditions** | The item whose quantity needs to be edited is selected, and the user has a grocery list that is active. |
| **Post-conditions** | The quantity of the specified item is updated on the user's list. |
| **Scenarios** | **Normal:**<br>1. The user views the items on their list and selects the item they wish to change the quantity.<br>2. The user enters a new quantity and clicks the change quantity option.<br>3. The system verifies the new quantity and updates the item's quantity on the list.<br>**Exceptional:**<br>1. The system encounters an issue while updating the quantity.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>| 

### Use Case 11:

| Use Case | Sort By Type |
|----|----| 
| **Requirements** | User is allowed to sort items on their grocery list by type. | 
| **Pre-conditions** | The user has an active list open and it contains multiple items categorized by type. |
| **Post-conditions** | The grocery list is reorganized so that items are grouped and displayed by their type. |
| **Scenarios**| **Normal:**<br>1. The user opens a grocery list they wish to sort.<br>2. The user selects the sort by type option.<br>3. The system processes the list and rearranges the items.<br>**Exceptional:**<br>1. The system encounters an issue while trying to sort the list by type.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>|


### Use Case 12:

| Use Case | Save List |
|----|----|  
| **Requirements** | The User is allowed to save any changes made to their grocery list, such as deleting items, or modifying quantity. | 
| **Pre-conditions** | The User has an active list open with at least one change made that has not been saved. |
| **Post-conditions** | The current status of the grocery list is saved in the user's account. |
| **Scenarios** | **Normal:**<br>1. The user makes any changes to their list.<br>2. The user clicks the save list option.<br>3. The system processes the request and saves all changes to the database.<br>**Exceptional:**<br>1. The system encounters an issue while trying to save changes in the list.<br>2. The user is advised to retry the operation.<br>3. If the problem persists, the system advises the user to contact the support.<br>|
