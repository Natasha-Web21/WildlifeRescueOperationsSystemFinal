WILDLIFE RESCUE OPERATIONS SYSTEM

Programming 1B - Part 2 Assignment


1. PROJECT DESCRIPTION

The Wildlife Rescue Operations System is a Java console-based application
developed for a wildlife conservation organisation.

The system is used to manage different wildlife rescue cases and allows
the user to create, search, update and display rescue cases.

The system supports three types of rescue cases:

1. Injured Animal Rescue
2. Orphaned Animal Rescue
3. Endangered Species Rescue


2. MAIN FEATURES

The system provides the following menu options:

1. Create new rescue case
2. Search rescue case by ID
3. Update rescue status
4. Display all rescue cases
5. Start rescue operation
6. Complete rescue operation
7. Generate rescue summary
8. Generate rescue report
9. Exit


3. RESCUE CASE INFORMATION

Each rescue case contains common information including:

- Rescue Case ID
- Animal Name
- Species
- Rescue Location
- Assigned Ranger
- Number of Rescue Days
- Daily Care Cost
- Current Rescue Status


4. INJURED ANIMAL RESCUE

An injured animal rescue case includes:

- Injury Description
- Veterinary Treatment Cost
- Surgery Required

The total cost is calculated using:

Number of Rescue Days x Daily Care Cost
+ Veterinary Treatment Cost
+ Surgery Fee when required


5. ORPHANED ANIMAL RESCUE

An orphaned animal rescue case includes:

- Estimated Age in Months
- Feeding Cost
- Foster Care Required

The total cost is calculated using:

Number of Rescue Days x Daily Care Cost
+ Feeding Cost
+ Foster Care Fee when required


6. ENDANGERED SPECIES RESCUE

An endangered species rescue case includes:

- Conservation Classification
- Security Cost
- Specialist Team Required

The total cost is calculated using:

Number of Rescue Days x Daily Care Cost
+ Security Cost
+ Specialist Team Fee when required


7. OBJECT-ORIENTED PROGRAMMING

The application demonstrates several object-oriented programming concepts.

Encapsulation:
Private variables and public getter methods are used to protect
the data stored in each rescue case.

Inheritance:
InjuredAnimalRescue, OrphanedAnimalRescue and
EndangeredSpeciesRescue inherit from RescueCase.

Abstraction:
RescueCase is an abstract class that contains common information
and behaviour for all rescue cases.

Interface:
The Rescuable interface defines operations that every rescue case
must support.

Polymorphism:
The RescueCase reference can refer to different rescue case types.
The calculateTotalCost(), determinePriority() and other overridden
methods behave according to the actual rescue type.


8. DATA STORAGE

An ArrayList is used to store multiple rescue cases while the
application is running.

The system does not use a database. The data is temporary and will
be lost when the application is closed.


9. INPUT VALIDATION

The application validates user input to reduce errors.

The system checks that:

- Required text fields are not blank.
- Rescue Case IDs are unique.
- Number of rescue days is greater than zero.
- Costs are greater than zero.
- Menu choices are within the valid range.
- Age values are greater than zero.
- Yes/No questions accept Y or N.
- Invalid numeric input does not cause the program to crash.


10. RESCUE STATUS

A rescue case can have the following statuses:

- Reported
- In Progress
- Completed

A rescue can be started when it is reported and completed after it
has been started.


11. JUNIT TESTING

The project includes RescueCaseTest.java.

JUnit 5 is used to test important parts of the application, including:

- Injured animal cost calculations
- Orphaned animal cost calculations
- Endangered species cost calculations
- Rescue priorities
- Rescue status updates
- Searching for existing cases
- Searching for cases that do not exist
- Duplicate Rescue Case ID validation


12. HOW TO RUN THE APPLICATION

1. Install a Java JDK.
2. Open the project folder in Visual Studio Code or IntelliJ IDEA.
3. Make sure Java is correctly configured.
4. Open Main.java.
5. Run Main.java.
6. Follow the menu displayed in the console.


13. PROJECT FILES

Main.java
RescueCase.java
RescueManager.java
Rescuable.java
RescueStatus.java
RescuePriority.java
InjuredAnimalRescue.java
OrphanedAnimalRescue.java
EndangeredSpeciesRescue.java
RescueCaseTest.java
README.txt


14. ASSUMPTIONS

The following assumptions were made:

- The system is used by one wildlife rescue organisation.
- Each rescue case represents one animal.
- Each rescue case has a unique Rescue Case ID.
- Rescue cases are stored temporarily using an ArrayList.
- No database is used.
- Data is lost when the application is closed.
- The application is console-based.


15. STUDENT DETAILS

Student Name: Natasha Mabija

Student Number: ST10505231


16. Development Environment

The Wildlife Rescue Operations System was developed and tested using **IntelliJ IDEA**. Java was used to implement the application, and Maven was configured through the `pom.xml` file to manage project dependencies, including JUnit 5 for unit testing.

The application was run and tested in IntelliJ IDEA to check the rescue-case creation process, searching, status updates, cost calculations, input validation, and report generation.

17. Tools and Technologies

Programming language: Java
IDE:IntelliJ IDEA
Online Compiler
Build and dependency management: Apache Maven
Unit testing framework: JUnit 5
Data storage: ArrayList (in-memory storage)

18 .AI Tool Use

AI tools were used during development to assist with understanding programming concepts, reviewing and debugging code, and testing application functionality. The code was reviewed and tested during development.

19. References

OpenAI (2026) *ChatGPT*. Available at: https://chatgpt.com/ (Accessed: 9 October 2026).

JetBrains (n.d.) *IntelliJ IDEA*. Available at: https://www.jetbrains.com/idea/ (Accessed: 9 October 2026).

Apache Software Foundation (n.d.) *Maven*. Available at: https://maven.apache.org/ (Accessed: 9 October 2026).

JUnit Team (n.d.) *JUnit 5 User Guide*. Available at: https://junit.org/junit5/docs/current/user-guide/ (Accessed: 9 October 2026).
