# Java_projects
Develop a console application that implements the functionality of generating a receipt in a store.

1. In this task, it is important to show an understanding of OOP, the ability to build models (identify classes, interfaces, their relationships), share functionality between them, and also know the syntax of the language itself. It is obligatory to use design patterns (Factory, Builder, Decorator). Pay attention to resistance to changes in logic and avoid copy-paste.
   
2. Use Java 17, gradle 7.5.
   
3. The application is launched by java RunnerClassName <parameter_set>, where the parameter set is in the itemId-quantity format (itemId is the product identifier, quantity is its quantity. For example: java CheckRunner 3-1 2-5 5-1card-1234 should generate and output to the console a check containing the the name of the product with id=3 in the amount of 1 item, the same with id=2 in the amount of 5 items, id=5 - one item, etc. Card-1234 means that a discount card with the number 1234 was presented. a generated receipt (version in the figure) containing a list of goods and their quantity with a price, as well as the calculated amount, taking into account the discount on the presented card (if any), is sent to the console. Among the goods to provide promotional. If there are more than five of them in the check, then make a 10% discount on this item. This information should be reflected in the check. A set of goods and discount cards can be set directly in the code, by an array or a collection of objects. Their number and nomenclature is of a test nature, therefore the name and quantity are free. 6. Implement exception handling (for example, product with id or file does not exist, etc.).
4. Implement receipt output to a file.
5. Use the gradle project builder.
6. Cover functionality with unit tests (at least 70%).
7. * Replacing the storage of initial data (item 11) in files with MySQL; make 2 tables (product and discount_card); Operation DDL must be stored in src/main/resources in a file with .sql extension; store the database connection settings in application.properties.
8.* Implement a RESTFUL interface (for example, receive a check via GET http://localhost:8080/check?itemId=1&itemId=1). Implementation of your choice (Servlet, Spring). UI does not need to be implemented.

