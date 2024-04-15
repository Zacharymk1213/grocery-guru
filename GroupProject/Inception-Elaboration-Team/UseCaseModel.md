# Use Case Model

**Author**: \<Lijie Wan\>

## 1 Use Case Diagram

The use case diagram visually represents the interaction between the user (actors) and the system (grocery list application). It includes the actor who is a customer in this case. The use cases connected to the actor illustrate all the functionalities such as "createList", "deleteList", "checkOff", "clearCheckOff", "addItem", and "updateQuantity".

![](Diagrams/use_case_diagram.png)

## 2 Use Case Descriptions

### Use Case 1:

| Use Case | Create List |
|----|----| 
| **Requirements** | Allow the user to create a new named list within the system for organizing and tracking items.|
| **Pre-conditions** | User can be logged into the system |
| **Post-conditions** | A new list with a specified name is created and appears within the user's account. The list is empty and ready for items to be added. |
| **Scenarios** | **Normal:**<br>1. User selects the option to create a new list.<br>2. The user enters a name for the list in the system.<br>3. The system checks that the name is unique.<br>4. The system creates the list for the user.<br>**Alternate:**<br>1. The user enters an existing name. <br>2. The system prompts the user to enter a different name.<br>3. The user gives a new name and the system creates the list.<br>**Exceptional:** <br>1. The user receives an error message.<br>2. The system encounters an issue while creating the list.<br>|

### Use Case 2:

| Use Case | Delete List|
|----|----| 
| **Requirements** | Allow the user to permanently remove an existing list in their account. This action can contain a confirmation step to prevent accidental deletions.| 
| **Pre-conditions** | User has ownership of the list they intend to delete. |
| **Post-conditions** | The selected list is removed from the user's account and the system's storage. All the data in the list is no longer accessible.|
| **Scenarios**| **Normal:**<br>1. The user accesses the lists area and views all existing lists.<br>2. The user selects the list they intend to delete.<br>3. The system prompts the user to confirm the deletion to prevent accidental data loss.<br>4. The system removes the list.<br>**Alternate:**<br>1. The user cancels the deletion after the confirmation prompt.<br>2. The system cancels the deletion and retains the list without changes.<br>**Exceptional:**<br>1. The user confirms deletion, but the system encounters an error.<br>2. The system displays an error message.<br>3. The user is prompted to try again later or contact customer support for assistance.<br>|

### Use Case 3:

| Use Case | Select List|
|----|----| 
| **Requirements** | Allow the user to choose their existing lists to perform actions such as viewing, editing, or deleting. | 
| **Pre-conditions** | User can access their previously created lists by logging in. |
| **Post-conditions** | The chosen list becomes the active context and is displayed for the user to take the next action. |
| **Scenarios**| **Normal:**<br>1. The user accesses the dashboard where all their lists are displayed.<br>2. The user clicks on the list they wish to interact with.<br>3. The system marks the list as selected.<br>**Alternate:**<br>1. The user attempts to select a list but realizes they open the wrong one.<br>2. The user navigates back to the dashboard and selects the correct list.<br>**Exceptional:**<br>1. The user selects a list but the list does not open or is active due to the system error.<br>2. The system informs the user of the error and suggests they try again.<br>3. If the problem cannot be solved, then the user is advised to contact support.<br>|


### Use Case 4:

| Use Case | Open List|
|----|----| 
| **Requirements** | User must have the ability to open and review contents including items and details of any lists they have created. | 
| **Pre-conditions** | The User has at least one list created in their account and has one list selected. |
| **Post-conditions** | The selected list has been opened and the user can view all the items and details. |
| **Scenarios** | **Normal:**<br>1. The user navigates to their collection of lists and selects one.<br>2. The user clicks the open list option.<br>3. The system displays all the content of the list.<br>**Alternate:**<br>The user does not open the list after selecting it and returns to the list collection.<br>**Exceptional:**<br>1. The user clicks the open list option, but the system encounters a loading error.<br>2. The system displays an error message to inform the user to try to open it again.<br>3. If the error still exists, the user is advised to contact support.<br>|
  
### Use Case 5:

| Use Case | Rename List|
|----|----| 
| **Requirements** | Allow users to change the name of any list they have created. The rename feature should ensure the list's name remains unique. | 
| **Pre-conditions** | User can access their existing lists and have one list selected to perform the next action. |
| **Post-conditions** | The list's name is updated to a new name. |
| **Scenarios** | **Normal:**<br>1. The user selects the list they wish to change the name with.<br>2. The user clicks the rename list option.<br>3. The user inputs a new unique name.<br>4. The system checks the uniqueness of the new name.<br>5. The system updates the list's name and confirms the change to the user.<br>**Alternate:**<br>1. If the new name is already exited, the system prompts the user to enter a different name.<br>2. The user inputs another name and the system retries the same process.<br>**Exceptional:**<br>1. The system encounters an issue when attempting to update the list's name.<br>2. The system displays an error message and suggests the user try again.<br>3. If the error still exists, the user is advised to contact customer support for further assistance.<br>|

### Use Case 6:

| Use Case | Create New Database Entry |
|----|----| 
| **Requirements** | User can add new item types to the system's database. | 
| **Pre-conditions** | User can modify the database. |
| **Post-conditions** | The database is updated with new item types. |
| **Scenarios** | **Normal:**<br>1. The user navigates to the database dashboard.<br>2. The user selects the option to create a new database entry.<br>3. The user enters the required information needed for the new item type, such as name.<br>4. The system checks the uniqueness of the name.<br>5. The system adds the new item type to the database.<br>**Exceptional:**<br>1. If the information the user enters is not complete or incorrect, the system displays an error message and requests the correct information.<br>2. If there is a system or database error during the process, the system prompts the user to try again. <br>|



### Use Case 7:

| Use Case | Search Type |
|----|----| 
| **Requirements** | User should be able to search items based on their type in the database. | 
| **Pre-conditions** | The database has items categorized by type and the user can access the database. |
| **Post-conditions** | The list of items under the type that the user wishes to search is displayed on the dashboard. |
| **Scenarios** | **Normal:**<br>1. The user accesses the search feature in the database.<br>2. The user enters the type and clicks the search type option.<br>3. The system retrieves all items matching the entered type.<br>4. The system displays the result on the dashboard.<br>**Alternate:**<br>If no items match the search type, then the system informs the user and suggests user similar type items or options to add the new item to the database.<br>**Exceptional:**<br>1. If the system fails to retrieve items due to an error, then an error message will show to the user.<br>2. If the error still exists after retiring,  the system suggests the user contact support.<br>|

### Use Case 8:

| Use Case | Search Name |
|----|----| 
| **Requirements** | User should be able to search items by their name in the database. | 
| **Pre-conditions** | Items in the database have unique names and the user can access the database. |
| **Post-conditions** | Search results match the search name. |
| **Scenarios** | **Normal:**<br>1. The user accesses the item search interface in the database.<br>2. The user enters the name of the item and clicks the search type option.<br>3. The system processes the query.<br>4. The system displays the items that match the input name or partial name to the dashboard.<br>**Alternate:**<br>If no items match the search name, then the system informs the user and suggests user add the new item to the database.<br>**Exceptional:**<br>1. The operation encounters an error due to system issues and the system prompts the user to try again later.<br>2. If the problem persists, the system advises the user to contact the support.<br>|

```markdown
