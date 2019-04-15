# Test Plan

**Author**: Wangqiong Huang, Assisted by Mark Abramov and Shakirkhan Kalavant.

## 1 Testing Strategy

### 1.1 Overall strategy

- The unit testing will be first performed by developers on every individual function of the application, in order to check if each unit is performed the way the application is designed. The developer checks for internal security holes and broken structured paths in the coding process. In addition, the developer will also check for the expected output. The developer will test each statement, object, and functions on an individual basis. The QA will be be performing all the tests the developer does, plus their own set of tests, such as if queries from the database are correctly implemented.

- Integration testing will be performed to test that a system of multiple modules worked as expected.

- System testing will be used on the completed program, using an Android device, and check whether the functions in the program match the users' requirements. The QA also tests the application as a whole, verifying thorough testing of every input in the application to check for the desired outputs.

- Regression testing will also be performed as needed by developers, to make sure that the new code has no downside effect on the program.

### 1.2 Test Selection

- We will test using both black & white box techniques.

- White-box testing will be used on the unit, going through the code to see if each unit works fine.

- Black-box testing will be used on the system and regression, includes if the program executes or not, choosing valid and invalid inputs to check if the program works correctly, see if the selected function shows the expected output, and etc.

 - Most testing will be done manually.


### 1.3 Adequacy Criterion

- To ensure a list of good quality test cases, we will select cases that cover every function in the application, ie every structure must execute at least once.

### 1.4 Bug Tracking

- We will be using GitHub for issue tracking. Each issue will be ticketed and assigned to a developer with additional information such as statuses, resolutions, and priorities.

### 1.5 Technology

Most testing will be done manually. As necessary, the following technologies shall be utilized:
 - JUnit
 - Robotium for Android applications testing
 - Ranorex for GUI testing
 


## 2 Test Cases
#### Legend:
Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)

Fail ![#f03c15](https://placehold.it/15/f03c15/000000?text=+)
 
|Test Case|Purpose|Steps| Expected Result | Actual Result |Pass/Fail|Additional Information|
|:---|:---|:---|:---|:---|:---|:---|
|1 | Test if program opens as expected and without crashing | Open the APP| The app should display without crashing|The app opens as expected|  Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)  | |
|2 | Test if user can add a new list| click "+" button on the right side, a textbox called "Create a new list" will pop out and user inputs the list's name, then clicks "OK" button | New list should be created | Popup opens and you can enter the name of the List, a new list created after click "OK"|  Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)| |
|3 | Test if user can delete existing list | Select the list you want to delete then click on the "trash can" icon| Selected list was removed from main menu | The selected list is removed after click on the "trash can" icon| Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)| |
|4 |Verify if the list can be renamed|Select a list, click on the "pencil" icon, then a textbox called "Rename list" pop out, enter the new name in it, and click ok to save| The list should appear using its new name| List appeared in new name after rename it| Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | it would be nice if the list name is not empty|
|5 |Verify if the list can be selected|Pick a list, click on the the checkbox to select it| The list should be selected|went right in to the list just by click on it, need long press on the list inorder to check off the checkbox in front of the list name| Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | |
|6 |Test if user can add item to the list |Click into the select list, click on the "add" icon on the top right, select an item and add it into the list | The select item will be add into the list| The selected item is able to add into the list|Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)| This is only to test if the item that add will show up in the list|
|7|User is able to delete item from a list|Open a list, select an item, click on the "trash can" icon to delete| The item should disappear from the list|The selected item disappear from the list after click on the "trash can" icon| Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)| |
|8 |Test if user can change quantity of an item on a list | Select the list in which the item is. Select the item that need to change quantity, use up arrow to increase, and down arrow to decrease the value of the item| The quantity of the selected item will change | The quantity of the item changes when clicks the up or down arrow |Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | | 
|9|Verify if user can add an item by picking it from a hierarchical list |Click into a list, click on the "add" icon on the top right, add it to the list|The selected item should appear in the list | The selected item in the hierarical list is add into the list | Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | | 
|10|Verify if an item can be found by its type and name|Click into a list, click on the "add" icon on the top right, click on the type of the item want to add, find the item by its name, and add it to the list |The user should be able to find what they are looking for| Able to find and add items that are saved in the database | Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)| |
|11|Verify if user can find item by typing its name|Click into a list, click on the "add" icon, type into the name of the item| The item that user is looking for should appear and possible to be add in to the list| Not implemented | Fail ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) | |
|12|Verify if an item which is not in the hierarchical list can be add into it|Go to add, type in an item is not in the hierachical list, click on the add item to add it into the list| The item should be add into the hierachical list| Not implemented | Fail ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) | |
|13|Verify if the list is saved automatically|Open a list, add or delete an item, close the list, reopen it to see if the list is as same as last change|The list should appear as same as when it was closed| It did autosave the list of Grocery Lists when app was closed | Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | |
|14|Verify if item can be checked off|Click on the box to check off the item|The box should be checked off| The item in the list can be selected by click on it once, and clear the check on checkbox by click on it again  | Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | |
|15|Verify if user can clear all the check off marks at once|Click on the clear all button|There should be no check marks on the list| All the checked checkbox can be clear out using the clear all button| Pass ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) | |
