# Design Discussion

## Design 1

![lynnHuangQ](../Design-Individual​/lynnHuangQ/design.png) 

Lynns design fulfills the requirements of the application but includes
some inconsistencies and unnecessary classes.
<br><br>
\- The GroceryList should be a managing class for the lists and contains no attributes.<br>
\- The HierarchicalList class is unnecessary since sorting is not part of the design.<br>
\- The Database should not be part of the design as it describes a back end implementation.<br>
<br>
\+ All of the requirements are addressed.<br>
\+ The extra methods included in the List and Item classes help in the team discussion to finalize the group design.

## Design 2
![markab4](../Design-Individual​/markab4/design.png)

Marks design fulfills the requirements of the application with minor
design flaws. 
<br><br>
\- Some relationships were not implemented correctly.<br>
\- The constructors and some attributes in GroceryList and ListHolder were contradicting.<br>
<br>
\+ All the requirements are addressed.<br>
\+ GroceryItemType class eliminates repetition of the type attribute.<br>
\+ Quantity is set directly in the GroceryItem class.<br>


## Design 3
![sero-dev](../Design-Individual​/sero-dev/design.png)

Seans design fulfills all the requirements of the application with 
more classes than necessary.
<br><br>
\- The User class should be considered as a List managment class.<br>
\- The User class should not be connected with the Items in any way, only the List class.<br>
\- Some relationships between classes were not implemented correctly.<br>
<br>
\+ All the requirements are addressed.<br>
\+ Catalog eliminates repetition of the type attribute.<br>


## Design 4
![Theofilos067](../Design-Individual​/Theofilos067/design.png)

Theofilos design focuses on minimal design but fails to address some 
requirements.
<br><br>
\- The groupByType and selectItem methods are not implemented.<br>
\- The ListManager and List classes should have an aggregate relationship.<br>
\- ItemType should be a separate class to eliminate repetition.<br>
<br>
\+ Simple and easy to understand.<br>
\+ ItemQuantity is implemented in the List class and allowed for an extended discussion of the subject for the final design.<br>


## Design 5
![shakirkalavant](../Design-Individual​/shakirkalavant/design.png)

Shakir's design ... 
requirements.
<br><br>
\- No comments.<br>


## Team Design
![Team](design-team.png)

[discuss the main commonalities and differences between this design and the individual ones, and concisely justify the main design decisions]
 

## Summary

[concisely summarize the lessons learnt in the process of discussing the designs, in terms of design, team work, and any other aspect that you consider relevant]
