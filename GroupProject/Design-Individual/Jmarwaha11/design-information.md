The grocery list manager application works of the following design. at the center of the application are users which can search the database named "GroceryStoreItems" add remove and change the quanity assigned in a list entry which is part of a bigger list. Instances of the list can be saved and accesed by the user at later points. there is a saveuserlist method inside the list class which can be called to save the userlist after any method which edits a listentry can be called. The database has subclasses for each of the various types of goods avaiable in the department store. There is a subclass of User Admins which can edit GroceryStoreItems to ensure data integerity and users in case users forget passwords etc. additionally users can save themselves as customers with saved payment info and place orders within the app. 
In summary here is a list of the requirements and how the design fufiles them. 
1. Users can add Item entrys to a list are there are methods to delete add and change the quanity of items.
2. the Database of the items requires is called GroceryStoreItems.
3. using subclasses and the searchbytype method users can search items by usertype.
4. in the GroceryStoreItems entity there is a method called Searchbyname which allows users to search by name.
5. in the list entity there is a method Saveuserlist(list) which should be called after every modifcation saving the list
6. Item entrys have a boolean value called checkornotchecked and that can be turned on and off via a setter to provide that functionality.
7. in the list  enity there are two methods  checklist and removecheckformatlist which removes the checks from all listentites or addscheckstoallentities
8.c heckmarks are part of the item entry entity so they are persistent and any change them to can be saved by called the saveuserlist method in list in any function which change the checkmarks 
9. items are subdivided by class which can be searched via the saerchbytype method. Additionally enabling the addition of data pertinent to that subclass.  
10.users can created savedlist which have a name assigned to them and methods to  save delete and rename them
11. I believe the userinferface is intutitve and responsive but that is subjective and looking foward to feedback .