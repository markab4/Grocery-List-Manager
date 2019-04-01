# Design Document

**Author**: Adnan Salimi, Sean Rodriguez

## 1 Design Considerations

The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.

### 1.1 Assumptions

We assume users of this application will be accessing the application with Android devices with Android API 25. The software will be dependent on the Nougat API and may not run optimally on older versions of Android. However, the specs of the application may allow for older devices to use the application without any significant issues. The software will be developed using Java and will be dependent on a SQLite Database.

### 1.2 Constraints

The application will not be run on a server-side database. Rather, it will be run using SQLite, which is embedded into the program. This means users will not be able to access the same grocery list across multiple devices.

### 1.3 System Environment

The system must be able to operate with a device which is capable of receiving input by touch. The device running the application must have enough RAM to be able to run the application without any issues. In order to have persistent data, the system requires the program to have an embedded SQLite database to store user information, such as lists and items.

## 2 Architectural Design

The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.

### 2.1 Component Diagram

* component diagram to be put here shortly*

Our system contains four classes which will work together to provide the full functionality of the application. Our system maintains a hierarchy which enables us maintain the functionality of our application without the worry of classes not functioning together. The ListHolder class will contain our lists, while the GroceryList class will define the actual grocery list and its properties. Items will be contained within this list, which will have an associated item type for labeling purposes.

### 2.2 Deployment Diagram

*deployment diagram to be placed here shortly*

Given the simplicity of the applcation, our application contains a simple design which requires a user interface that displays the ability to create, modify, and view lists. From here, users will then be able to manage their lists and whatever items they may contain. The applications functionality ties to the applications SQLite database, which will allow for persistence as to save the users changes or modifications.

## 3 Low-Level Design

*Describe the low-level design for each of the system components*

### 3.1 Class Diagram

*class diagram to be placed here shortly*

Our class diagram shows the necessary functions and attributes of the classes required for our system to function as intended. A ListHolder class may contain many GroceryLists. The GroceryList class shares a many-to-many relationship with the item class, as there can be many instances of items in many lists. 

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

