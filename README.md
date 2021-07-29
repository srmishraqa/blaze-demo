# Description

This works as a user guide for the repository.
This Project uses Page Object Model Architecture and integrated with testNG and this is developed using java language.

#Steps to use

• For cloning the repo Please use -> git clone <\_file_name>.git

• Once Cloning is done use -> mvn clean install

• It will automatically download all the jars defined in POM.xml file, build the project, run the test cases and will generate a report

• To rerun again first -> clean the target folder by running -> mvn clean

• And then run -> mvn clean install (or) go to src/main/resources folder and run the textng.xml file

• after each run need to clean the target folder by earlier mentioned command

• To find out the default report -> go to test-output and open index.html file (pre-requisite: always should have been run by command -> mvn clean install)

• Currently this repo has a chromedriver (V91.0) for mac in drivers folder

• All the page classes which contains page specific locators and functions can be found inside src/main/java/com.blazeDemo.pages package

• Tests can be found in src/test folder

• For any clarity please contact: soumya_rm@outlook.com
