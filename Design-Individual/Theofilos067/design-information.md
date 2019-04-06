## Design Information - Grocery List

1. A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list.
   - In order to represent this requirement for the user to add, delete or edit the quantity of 
items to a list, I created class "List" which will implement these three methods. The class also 
contains the attribute "itemQuantity" which represents the quantity of a specific item on the list.

2. The application must contain a database (DB) of items and corresponding item types.
   - This requirement is not considered at this stage because it does not affect the design of the 
software.

3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.
   - The ability to add items has already been realized as part of the design, "how" the method
will be implemented is not relevand at this point. A class Item is implemented with attributes 
"itemName" and "itemType" which will be the two levels referred above and the way the list will 
identify the items. The quantity will be specified at the list directly with attribute "itemQuantity" 
specified in requirement 1.

4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.
   - The method "searchItem" was added to the "List" class to realize the requirement of search 
in the software. In the case of a match not found, "createItem" method was added to the "List" 
class that will allow users to add a new item and its type to the database. The way the search 
will be implemented is not considered at the design stage.

5. Lists must be saved automatically and immediately after they are modified.
   - Not considered at this stage, it does not affect the design of the software.

6. Users must be able to check off items in a list (without deleting them).
   - A boolean operation was added to the "Item" class that will make an item as checked and
a method "checkItem" was added to the "List" class that will allow the user to check off items
in the list.

7. Users must also be able to clear all the check-off marks in a list at once.
   - Not considered at this stage, it does not affect the design of the software.

8. Check-off marks for a list are persistent and must also be saved immediately.
   - Not considered at this stage, it does not affect the design of the software.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).
	- Not considered at this stage, it does not affect the design of the software.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.
	- A Utility class "ListManager" was added to the design which contains the four methods required.
This class is implemented by the "List" class in order to create, select, name or rename
and delete lists.

11. The User Interface (UI) must be intuitive and responsive.
	- Not considered at this stage, it does not affect the design of the software.