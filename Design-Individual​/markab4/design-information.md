# *GroceryListManager* Design Information

1. A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list (e.g., change from one to two pounds of apples).
    - To realize this requirement, I added to the design a class GroceryList which is composed of GroceryItems.
    - Class GroceryList has methods: 
        - "addItem()" and "deleteItem()" both of which take in a GroceryItem
        - "changeQuantity()" which also takes in in a GroceryItem as well as an integer corresponding to the new desired quantity
        - "selectItem()" which takes in a String and returns an item in the list with that name.
    
2. The application must contain a database (DB) of items and corresponding item types.
    - To realize this requirement, I added to the design a class GroceryItem with attributes "quantity" and "itemName".
    - GroceryItem is a generalization of the class "GroceryItemType."
    
3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.
    - To realize this requirement, I added to the design of class GroceryItemType attribute "typeName"
    - I also added getter and setter methods for the "quantity" attribute in GroceryItem class
    - I added to the design of class GroceryItemType a constructor which initializes the GroceryItemType name with the String that is passed in.

4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.
    - Not considered because it does not affect the design directly.

5. Lists must be saved automatically and immediately after they are modified.
    - Not considered because it does not affect the design directly.

6. Users must be able to check off items in a list (without deleting them).
    - To realize this requirement, I added to the design of class GroceryItem a boolean "checked" attribute, initially set to False, and a method "setCheck(Boolean)" which sets the value of "checked" to the Boolean value passed into it

7. Users must also be able to clear all the check-off marks in a list at once.
    - To realize this requirement, I added to the design of GroceryList a "clearChecks()" method which would loop through all GroceryItems in the list, setting all checked values back to False
    
8. Check-off marks for a list are persistent and must also be saved immediately.
    - Not considered because it does not affect the design directly.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).
    - To realize this requirement, I added to the design of GroceryList a "groupItemsByType()" method

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
    - To realize this requirement, I added to the design a class ListHolder which is composed of GroceryLists. 
    - This ListHolder class has methods "addList(GroceryList)", "deleteList(GroceryList)", and "selectList(String)" which returns a Grocery list with that name.
    - I added also to the design of class GroceryList attribute "listName", which is a String.
    - I also added the following methods: a constructor which takes in a name, a setter method for "name", a "delete()" method which removes all items from the lists
        
11. The User Interface (UI) must be intuitive and responsive.
    - Not considered because it does not affect the design directly.