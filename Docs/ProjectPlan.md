# Project Plan

**Author**: Mark Abramov

## Introduction

 Tired of doing grocery lists with pencil and paper and unhappy with each and every existing app for managing grocery lists​? Our product is *GroceryListManager​*, an application for managing grocery lists that works exactly the way you want.

## Process Description

### Gather Requirements 
- The core requirements have been set by users, as listed in the [Requirements Document](Requirements.md).
- Exit criteria: Requirements understanding as evidenced by the fidelity of the primary [use cases](UseCaseModel.md).

### Analyze & Design 
- Consists of determining whether the stated requirements are clear, complete, consistent and unambiguous, and resolving any apparent conflicts
- Entrance criteria: [Requirements](Requirements.md) as gathered from the users
- Exit criteria: 
    - [Use cases](UseCaseModel.md)  which describe scenarios for standard domain functions that the system must accomplish. 
    - [Class diagram](../Design-Team/design-team.png) describes the names, class relations, operations, and properties of the main classes. 
    - [User-interface mockups or prototypes](./DesignDocument.md) created to help understanding.

### Implement
- The part of the process where software engineers actually program the code for the *GroceryListManager*. Documentation is also done at this stage, as contained in the [User Manual](UserManual.md). We will be using Android Studio with Java to build the Android App, and GitHub to manage version control and to track and assign issues. We will also use the SQLite Database Management System.
- Entrance criteria: the requirements are established and the software design documents are complete .
- Exit criteria: an initial version of the app released.

### Test 
- Ensure that defects are recognized as soon as possible, covered in greater detail in the [Test Plan](TestPlan.md). GitHub will be used to track bugs and there will be a heavy reliance on manual testing to ensure the user interface is bug-free and any errors are caught. Both actual Android devices as well as emulators will be used in testing.
- Entrance criteria: An initial version of the app.
- Exit criteria: All bugs repaired and application fits the criteria specified in the Requirements Gathering and Design activities.

## Team

### Team Members

- Mark Abramov
- Wangqiong Huang
- Sean Rodriguez
- Adnan Salimi
- Theofilos Papadopoulos
- Shakirkhan Kalavant

### Roles

- **Project Manager** -  in charge of the planning and execution of the project. Responsible for developing the software project plan, managing deliverables according to the software project plan, leading and managing the software project team, assigning tasks to project team members, and keeping everyone happy.

- **Database Administrator** - responsible for the organisation, storage and retrieval of the data. Designs, builds, and tests the database.

- **Quality Assurance** - responsible for defining quality standards and metrics for the project, and ensuring all development tasks meet quality criteria through test planning, test execution, quality assurance and issue tracking.

- **Technical Lead** - in charge of leading the development team, and responsible for the quality of its technical deliverables.

- **Backend Developer** - responsible for managing the interchange of data between the server and the users and all server-side logic, as well as integrating the front-end elements built by the front-end team into the application. 

- **UI/UX Developer** - responsible for User Interface and User Experience respectively. Manages the visual aspects of the design of the site and ensures the site is working well with preferred users.

- **Frontend Developer** - manages what users visually see first in their browser or application. Front end developers are responsible for the look and feel of a site. 

### Team Member Roles

- **Mark Abramov** - Project Manager, Frontend Developer
- **Wangqiong Huang** - Quality Assurance, Backend Developer
- **Sean Rodriguez** - Technical Lead, Backend Developer
- **Adnan Salimi** - UI/UX Developer
- **Theofilos Papadopoulos** - Frontend Developer
- **Shakirkhan Kalavant** - Database Administrator, Quality Assurance