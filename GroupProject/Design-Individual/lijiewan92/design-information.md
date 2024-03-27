# Step by Step Analyzation

# Entities
find nouns for entities: 
- GroceryList, Users, Database, Item Type, Items

# Attributes
go through all requirements we can make some attributes for each entity
1. GroceryList: 
- a grocery list should contains an ID to associate with a user to let user query and edit the order
- a name attribute that user can name or rename the list
- a list of items that user wants to order
 
2. User
- the user class should be a profile for a user having user's ID, name, phone number, and address

3. Database
- database strores all items and item types, so there should be two separated lists

4. Item Type:
- each type should contains an ID and a name

5. Item:
- every item has an ID, a name, and quantity, moreover, a type ID to determine what the item belongs to

# Operations
1. User
- users can createList(), renameList(), selectList(), and deleteList() accroding to requierment 10

2. GroceryList
- from requirement 1, user can addItem(), deleteItem(), and changeQuantity() of item; from requirement 6 and 7, user can checkOff() and clearCheckOff() a grocery list; from requirement 9, an grocery list can show groupByType()

3. Database
- database can let users to search an item by its name or type, the operation would be searchName() and selectType(); in addition, if the item does not exist in the database, then user can addItem() into databdase(requirement 2, 3, 4)

4. Items
- users can specifyQuantity() when add an item into the list and add new items(addItem()) into database accroding to requirement 3 and 4

# Relationships
1. the relationship between User and GroceryList is association and one to many; a user can have many lists, but a list belongs to one user only

2. the relationship between User and Database is also association and one to many; a user cau get access to one database only, and a database can be used by 0 to many users

3. the relationships between Database and Item and Database and ItemType are both association and one to many since one Database can store many items and item types, and both item and item type can exist in only one database

4. the relationship between GroceryList and Item is aggregation and many to many because an GroceryList can contains one to many items, and one item can be added into one or more lists



