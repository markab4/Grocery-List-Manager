# Test Plan

**Author**: Shakirkhan Kalavant and Wangqiong Huang

## 1 Testing Strategy

### 1.1 Overall strategy

- The unit testing will be performed by developers on every individual function of the application, in order to check if each unit is performed how the application is designed. The developer check for internal security holes and broken structured paths in the coding process. The developer will also check for the expected output. The developer will test each statement, object, and functions on an individual basis. The QA will be testing if queries from the database are correctly implemented

- Integration testing will be performed to test that a system of multiple modules worked as expected

- System testing will be used on the completed program, using an android phone, and check if the functions in the program match the users' requirements. The QA application as a whole. Verifying thorough testing of every input in the application to check for the desired outputs.

- Regression testing will also be performed by developers, to make sure that the new code has no downside effect on the program.

### 1.2 Test Selection

- We will test using both black & white box techniques.

- White-box testing will be used on the unit, going through the code to see if each unit works fine.

- Black-box testing will be used on the system and regression, includes if the program executes or not, choosing valid and invalid inputs to check if the program works correctly, see if the selected function shows the expected output, and etc

 - Most of the functions will be tested manually


### 1.3 Adequacy Criterion

- To ensure a list of good quality test cases, we need to select cases that cover every function in the application, mean that every structure must execute at least once.

### 1.4 Bug Tracking

- We will be using bug tracking software Jira, bitbucket or the debug function on GitHub. Where each issue will be ticketed and assigned to a developer with more features such as statuses, resolutions, and priorities.

### 1.5 Technology

 - JUnit

## 2 Test Cases
|Test Case|Purpose|Steps|Expected Result|Actual Result|Pass/Fail|
|:---|:---|:---|:---|:---|:---|
|1 | check if the program works | open the APP| the app should be display| | Pass |
|:---|:---|:---|:---|:---|:---|
|2 | Add a new list| click "+" button on the right side, new window will open and user inputs the list's name then click "Finish" button | New list should be created | New list is added | | Pass|
|:---|:---|:---|:---|:---|:---|

|3 | Delete existing list | Select the list you want to delete then click on the "trash can" logo| Selected list was removed from main menu | | |Pass | There is another way to delete the list, while you are in the selected item by click on "Trash can" logo. confirmation window will pop-up.
|:---|:---|:---|:---|:---|:---|

|4 | Add item to the list | In "Create a List" window, click on "ADD ITEMS" button. New windows that allow the user to select an item of their choice. Click on "+" button on the right then in Quantity window input quantity and units, then click "Confirm" to add items. | Success message appeared on the bottom of the screen indicating "This item added to "List Name"| | |Pass | There is another way to add an item to the list. while in the selected list. Click on "+" button on top of the screen, then item windows open's then select the item by clicking "+" button then Quantity window pops-up, then input the quantity then click "Confirm" button.
|:---|:---|:---|:---|:---|:---|
|5 | Change quantity | Select the list in which the item is. Click on the item. Quantity window will open and change quantity then click "Confirm" button | The quantity of the selected item will change | The quantity of the selected item changed | Pass |
|:---|:---|:---|:---|:---|:---|
