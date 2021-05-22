# Planit

Important Files:</br>
Framework related information document location : root directory : PlanIt documentation.docx </br>
Assessment Questions and Answers document location : root directory : PlanIt Questions and Answers.docx</br>
Sample Results stored at : Execution Results folder</br>


# Setup/ How to execute

first of all you need to build the solution. please do below steps for project configuration

1) Clone the Repository on your local machine
2) Build the solution using "mvn install" command
3) Ensure that run configuration should be proper.if Maven does not have any run configuration, then add the same and setup the right JRE version
4) Run maven > update project command and select the "force Update of the snapshots/Releases" option in your eclispe
5) Ensure that there are no error in your solution
6) Invoke command prompt and go to project directory where you cloned this solution.
7) Run "mvn test" command
8) It will start executing all the test cases
9) To make changes in test execution, use testng.xml to modify the execution classes.
10) If you want to edit the test data, use testdata.xlsx for the same. each test case has its own sheet. it is in root folder. framework supports multiple data iterations. it means we can run same test case multiple times using different datasets. (TC003 is an example)
11) You can also setup the execution in headless mode. change the setting in config.properties for the same

please feel free to contact me if you are facing any dificulty while executing or for code walkthrough.

demo video : https://youtu.be/VIHtoDVJ2-s
