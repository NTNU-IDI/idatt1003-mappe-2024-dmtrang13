# Food

*Food* is a Java-based application for managing ingredients in a storage and recipes in a cookbook. It includes features such as adding, removing, and searching for ingredients and recipes.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Build and Run](#build-and-run)
- [Testing](#testing)
- [License](#license)

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/NTNU-IDI/idatt1003-mappe-2024-dmtrang13.git
   cd idatt1003-mappe-2024-dmtrang13
   
2. Ensure Maven is installed on your system. You can install it [here](https://maven.apache.org/download.cgi)
3. Build the project:
   ```bash
   mvn clean install
   
---

## Usage

*Food* allows you to manage ingredients and recipes effortlessly through a user-friendly menu system. The application provides the following features:

### 1. Main Menu
After starting the application, you’ll be presented with the main menu:

1. **Manage Storage**: Access features for managing ingredients in your storage.
2. **Manage Cookbook**: Access features for managing recipes in your cookbook.
3. **Exit Application**: Close the application.

### 2. Storage Management
From the Storage Menu, you can:

1. **View All Ingredients**: Display a list of all ingredients in storage.
2. **Find Ingredient by Name**: Search for specific ingredients by name.
3. **Add New Ingredient**: Add a new ingredient to the storage.
4. **Remove Ingredient by Name and Amount**: Remove a specific quantity of an ingredient.
5. **View Expired Ingredients and Total Value**: List expired ingredients and calculate their total value.
6. **Calculate Total Value of Storage**: Display the total value of all ingredients in storage.
7. **Get Ingredients in Date Range**: Search for ingredients based on expiration dates.
8. **Return to Main Menu**: Go back to the main menu.

### 2.Cookbook Management
From the Cookbook Menu, you can:

1. **View All Recipes**: Display a list of all recipes in the cookbook.
2. **View Recipes by Category**: Search for recipes by category.
3. **Add a New Recipe**: Add a new recipe to the cookbook.
4. **Find a Recipe by Name**: Search for a recipe by its name.
5. **Check if a Recipe Can Be Made**: Verify if a recipe can be prepared using available ingredients.
6. **Suggest Recipes Based on Ingredients**: Get recipe suggestions based on the ingredients in storage.
7. **Return to Main Menu**: Go back to the main menu.

### **Example usage**:
1. **Run the application**:
   ```bash
   java -jar target/Food-1.0-SNAPSHOT.jar
   ```
   The application will display the main menu

2. **Add an Ingredient**:
   - Select "Manage Storage" from the main menu.
   - Choose "Add New Ingredient" from the storage menu.
   - Follow the prompts to enter the ingredient details (e.g., name, quantity, and expiration date).

3. **Find a Recipe by Name**:

   - Select "Manage Cookbook" from the main menu.
   - Choose "Find a Recipe by Name" and provide the recipe's name.

4. **Check if You Can Make a Recipe**:
   - Select "Manage Cookbook."
   - Choose "Check if a Recipe Can Be Made" and follow the prompts to verify.

---

## Build and Run
1. Build the application: Compile and build the project using Maven:
   ```bash
   mvn clean install
   ```
   this will generate a JAR file in the ```target``` directory

2. Run the Application: Execute the JAR file:
   ```bash
   java -jar target/Food-1.0-SNAPSHOT.jar
   ```
   alternatively
      ```bash
      mvn exec:java -Dexec.mainClass="edu.ntnu.idi.idatt.main"
     ```
---

## Testing
1. Run all test using Maven:
   ```bash
   mvn test

2. **Surefire Plugin**: The project uses the Maven Surefire Plugin to execute unit tests. It is pre-configured in the pom.xml to support features such as:

   - Running JUnit 5 tests.
   - Adding JVM arguments for testing tools like ByteBuddy:
   ```bash
   <argLine>
      -XX:+EnableDynamicAgentLoading
      -javaagent:${settings.localRepository}/net/bytebuddy/byte-buddy-agent/1.15.4/byte-buddy-agent-1.15.4.jar
   </argLine>
   ```
   Integration with JUnit 5 and Mockito for robust test coverage.


3. Unit tests are included to ensure the stability of the application. The following tools are used:
- **Junit 5**: For writing and running unit tests.
- **Mockito Core**: For mocking dependencies during testing.
- **Mockito Inline**: Simplifies testing final methods and classes.
- **Mockito Junit 5 Integration**: Provides seamless integration with JUnit 5.

---

## License
This project is licensed under the following:
1. [Eclipse Public License 2.0](https://github.com/junit-team/junit5/blob/main/LICENSE.md): Covers Junit 5.
2. [MIT License](https://github.com/mockito/mockito/blob/main/LICENSE): Covers Mockito Core and extensions.