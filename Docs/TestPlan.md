# Test Plan
********************************************

*********************************************
*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: \<person or team name\>

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

- The unit testing will be perform by developers on every individual function of the application, inorder to check if each unit is perfomed as how the application is designed.The developer check for internal security holes and broken structured paths in the codeing process.The developer will also check for the expected output. The developer will test each statement, object, and functions on an individual basis.(a White-box Testing method is usually used to get the job done<--from google)//I think we need a tester to test each process or series of processes in the application.


- Integration testing will be performed to see if the application is compatible with other devices such as tablets, phones, etc. An task will be started on mobile and completed on a tablet and vice versa.

- System testing will be use on the completed program, using an android phone, and check if the functions in the program match the users' requirements. The QA application as a whole. Verifing thorough testing of every input in the application to check for the desired outputs.(Usually, Black Box Testing method is used.<-- google)

- Regression testing will also be perform by developers, to make sure that the new code have no down side effect on the program. (fixing bugs? )// test changes to the code have no unexpected changes in the performace, inputs, or outputs.

### 1.2 Test Selection

*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

- We will test using both black & white box techniques.

- White-box testing will be used on the unit, going through the code to see if each unit works fine.

- Black-box testing will be used on the system and regression, includes if the program execute or not, choosing valid and invalid inputs to check if the program works correctly, see if the selected function shows the expected output, and etc


### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

  - To ensure a list of good quality test cases, we need to select cases that covers every functions in the application, mean that every structure must execute at least once.(not sure if this is what the question asks.)

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

  - We will be using bug tracking software Jira, bitbucket or the debug function on github. Where each issue will be ticketed and assigned to a developer with more features such as statuses, resolutions, and priorities.

### 1.5 Technology

*Describe any testing technology you intend to use or build (e.g., JUnit, Selenium).*

 - JUnit
 - Most of the will be done manually 

## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*

Test Case #	|Purpose|	Steps to Complete|	Expected Result|	Actual Result	|Pass/Fail	| Additional Information

1 |check if the program works | open the APP| the app should be display

2 |Add a new list| click "+" button on the right side| New list should be created| New list was added| Pass| 

3 | Delete existing list | Select the list you want to delete then click on the "trash can" logo| Selected list was removed from main menu| Pass| There is another way to delete the list, while you are in the selected item by click on "Trash can" logo. confirmation window will pop-up.

4 | Add item to the list| In "Create a List" window, click on "ADD ITEMS" button. New windowns that allows the user to select a items of their choice. Click "+" button on the right then in Quantity window input quantity and units, then click "Confirm" to add items. | Success message appeared on the bottom of the screen indicating "This item addded to "List Name"| Pass| There is another way to add item to the list. while in selected list. Click on "+" button on top of the screen, then item windows opens then select the item by clicking "+" button then Quantity windwow pops-up, then input the quantity then click "Confirm" button.

5 | Change quantity| Select the list in which the item is. Click on the item. Quantity window will open and change quantity then click "Confirm" button| The quantity of the selected item will change| The quantity of the selected item changed| Pass 
