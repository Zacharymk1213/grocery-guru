# Requirements

1. **Grocery List Management**: A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples). This is covered by the `User` class which stores the user's name and a list of their GroceryLists. The following methods are available:
    - `CreateList(String Listname)`
    - `ChooseList()`
    - `deleteList(String Listname)`
    - `renameList(String Listname, String newname)`
    - `User()`

2. **Database**: The application must contain a database (DB) of items and corresponding item types. This is covered by the `Database` class that contains items and itemtype.

3. **Item Addition**: Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item. This is covered by `GetItemsFromType` and `GetItemType` in `Database` class, and `ChangeQuantity` in `GroceryList` class.

4. **Item Specification**: Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB. This is covered by `getItems` and `findSimilarName` in `Database` class, and `ChangeQuantity` in `GroceryList` class.

5. **Automatic Saving**: Lists must be saved automatically and immediately after they are modified. This is covered by `saveAutomatically` in `GroceryList` class.

6. **Item Checking**: Users must be able to check off items in a list (without deleting them). This is covered by `addChecks` in `GroceryList` class.

7. **Check-off Clearing**: Users must also be able to clear all the check-off marks in a list at once. This is covered by `ClearChecks` in `GroceryList` class.

8. **Persistent Check-off Marks**: Check-off marks for a list are persistent and must also be saved immediately. This is covered by `SaveChecks` in `GroceryList` class.

9. **Item Grouping**: The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles). This can be achieved by using `GetItemsfromType`, as the database will be stored in alphabetical order and will be alphabetized when `createItem` is created.

10. **Multiple Lists**: The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. This is covered by the following methods in the `User` class:
    - `CreateList(String Listname)`
    - `ChooseList()`
    - `deleteList(String Listname)`
    - `renameList(String Listname, String newname)`
    - `User()`

11. **User Interface (UI)**: The UI must be intuitive and responsive. The current UI is intuitive. However, because of the nature of this database the UI could become laggy when searching if the Item database become too large. This can be circumvented by using a relational database.
