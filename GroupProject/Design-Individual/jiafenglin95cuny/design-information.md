1. A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list (e.g., change from one to two pounds of apples).

A GroceryList class will represent a list of GroceryItems. The GroceryList class can have multiple instances of GroceryItems, but each GroceryList can only have one GroceryItem of the same name.
GroceryList's add operation will add an *amount* to the item's *quantity* field if already in the list; otherwise, it will insert the new item into the list.
GroceryList's remove operation will remove an *amount* from the item's *quantity* field if it's in the list; if the item's quantity reaches 0, it will notify the user and be deleted from the list once confirmed.

2. The application must contain a database (DB) of items and corresponding item types.

A single GroceryDB will keep the records of all GroceryItems and keep all itemTypes there are; each GroceryItem must be one of the itemTypes.

3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.

Users can shopAisle() by type and add items from an Aisle, which represents a list of GroceryItems of the same type. From the aisle, users may add items to any list they have.

4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.

Users can look up an item by its name, which will be matched against GroceryDB's list of GroceryItems. If not similar item is found, the user then may add a GroceryItem to the GroceryDB's list. If a match is found, the user may choose to add the matching item to any of their lists.

5. Lists must be saved automatically and immediately after they are modified.

Not considered.

6. Users must be able to check off items in a list (without deleting them).

Users can make a GroceryItem in a list be marked, as represented by a boolean in the GroceryItem class.

7. Users must also be able to clear all the check-off marks in a list at once.

User can do this through the clearCheckedMarks() operation.

8. Check-off marks for a list are persistent and must also be saved immediately.

Not considered.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).

The Aisle class accomplishes this as it is a list copied from the GroceryDB and filtered by type.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.

Users can have an array of lists, each of which is a GroceryList instance that has its own name and can be marked/selected. The createList() operation creates a new list of the given name. The deleteList() operation deletes a list by name.

11. The User Interface (UI) must be intuitive and responsive.

Not considered.