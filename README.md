# PROJECT STRUCTURE
- This project was developed using the "**_Java_**" programming language. 
- "**_Selenium_**" was chosen as the UI automation tool.
- "**_Maven_**" was used as the build management tool.
- "**_Cucumber(BDD)_**" was preferred as the test framework.
- The "**_feature files_**" that contain the scenarios are located under the src/test/resources directory. 
- The required dependencies are managed within the "**_pom.xml_**" file. This file allows for easy dependency management.
- The relevant "**_java classes_**" are located under the src/test/java/com/remwaste directory.
- Test datas are stored in the "**_configuration.properties_**" file. You can easily modify browser types and URLs from this file
- The project follows the "**_Page Object Model (POM)_**" structure.
- "**_Test reports_**" can be found at target/default-cucumber-report.html file.
- Some pages and methods in the project contain explanatory notes.
- In API testing, I used the Rest Assured, Hamcrest, and JSONObject libraries.
- To run the tests, open the project in IntelliJ IDEA and click the green icon next to the CukeRunner class. This will execute the associated scenarios.
- To change which scenario is executed, modify the tags section in the CukeRunner class by entering the desired scenario's tag.
- The available scenario tags can be found at the top of each scenario inside the feature files located under the resources directory.

