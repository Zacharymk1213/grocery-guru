1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
   -I have a grocery list class that has methods that adds and delete items and also change teh quantity of items.
2. The application must contain a database (DB) of items and corresponding item types.
   -The database is represented by the database class and type is represent by the attribute and can be looked up by the type and name

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
   quantity for that item.
   -User can search items by the type follow by name

4. Users must also be able to specify an item by typing its name. In this case, the
   application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
   -The user has a method call lookUpItem and addItem that allows them to look up items in DB and add items

5. Lists must be saved automatically and immediately after they are modified.
   -The GroceryList class have a save method that automatically saves the list after each modified.

6. Users must be able to check off items in a list (without deleting them).
   -The GroveryList have check method and also check if the groceryitem is checked

7. Users must also be able to clear all the check-off marks in a list at once.
   -The user have a method to clearcheck

8. Check-off marks for a list are persistent and must also be saved immediately.
   -its in the grocerylist so it will be save when theres a new modified
9. The application must present the items in a list grouped by type, so as to allow users to
   shop for a specific type of products at once (i.e., without having to go back and forth
   between aisles).
   -the item are sepreated by types

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
    farmer’s market list”). Therefore, the application must provide the users with the ability to
    create, (re)name, select, and delete lists.
    -the user have the method to create, rename, delete, select methods
11. TheUserInterface(UI)mustbeintuitiveandresponsive.
