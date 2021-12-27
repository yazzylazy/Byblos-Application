# SEG2105-Project-Gr12
Working repository for course project for SEG 2105.

# SEG2105-Project-Gr12
Working repository for course project for SEG 2105.

Welcome to the Byblos Application, Created by SEG2105 f21  Group 12!
The contributors of this project include:
1) Adam Orchard (Student#: 300169736)
2) Jonathan Treuil (Student#: 300178100)
3) Graydon Berneche (Student#: 300021605)
4) Yasin Elmi (Student#: 300163765)
5) Michael Thompson (Student#: 300175414)

Deliverable 2:
For the second deliverable of our application, we were tasked to implement 2 important features of our working android app. These 2 features are: the admins ability to delete created user accounts, and admins ability to create, edit and delete services using an implemented list of required fields. We have also refactored a lot of our app to conserve space and create a more streamlined process in the backend.

Features added:
1) Created the function for the admin account to delete user accounts. This was implemented using a listView that lists the created users from our database and a funcitonal search method. Long clicking the user will delete that account. Admin account CAN NOT be deleted.
2) Created services, in particular, the admins ability to create a new service, edit an existing service, and delete an existing service. 
    i) When creating a service, you are brought to a page that lists off various elements the admin/branch may require from a user to make their rental (ie. date of birth, name,     email, etc). You are also allowed to set a name and set an hourly rate.
    ii) When editing a service, you are brought to a list view just like the delete account where there are available services from the database. By long clicking the desired       service, you are brought back to the .xml which create service takes you to. By editing a service, you are effectively deleting the old service and creating a new one with       the changes made. NOTE: we tried to implement the feature of having the switches of the service be true if they were previously true (ie. when creating a service and 'Name'     was checked, editing the service would start with 'Name' switch already turned on). However, we had issues trying to access the database (in particular, the onDataChange         method not being called) so for this deliverable we haven't implemented it. For the future we hope to add this feature.
    iii) Deleting a service functions exactly like delete account. A list view displays all current services in the database, and a long click on the desired service will delete     it.
    
Changes made from Deliverable 1:
Instead of creating multiple forms for the same thing, such as CreateEmployeeAccount, CreateCustomerAccount, we made them into one file CreateBranchAccount. Now depending where you click from, the role will be predetermined and sent in within a bundle (ie clicking create account from log in will send you to the page with the role "customer" bundled with the intent). This cleared up space and made the overall design more flexible. This was also done as well with our create/edit services, where they both use the same page to create and/or update. We also changed the admin features page to accomadate the new services we have added.

*********************************************************************************************************************************************************************************
Deliverable 1:
For the first deliverable of our application, we were tasked to create the first few features of a working android app. These features included: the ability to log in, the ability to create accounts (both employee and customer accounts), and a Log In succesful page.

For this deliverable, we have implemented the following features:
1) Created a fully functional "Log In" page that validates each user with their corresponding information. We used FirebaseDB in order to store and validate every user's sign in info.
2) Created a "Log In Succesful" page. This appears to users who can succesfully log in with a valid account. This page outputs the user's name and their role. This page redirects after 3 seconds to the users role's functions page (to be discussed later)
3) Created a "Create Customer Account" page. This page can be accessed by both the main "Log In" page and by the "Employee Features" page. New users, employees, AND admins can create new customer accounts. This page allows customers to create an account with Byblos, which is stored in our previously mentioned database. This page furthermore validates whether the customer's information that was inputted is valid; if not, the app asks the user to re-enter their information.
4) Created a "Create Employee Account" page. This page can only be accessed by the "Employee Features" page, as only employees can create employee accounts. To access the "Create Employee Account" page, the user must either: a) sign in using an employee account, or b) sign in using the admin account (username: admin, password: admin). After signing in as an employee or admin, the option to create an employee account will be available to the user. The "Create Employee Account" page functions identical to the "Create Customer Page", however instead it creates user of role "Employee". This page also validates the user's inputted information.
5) Created an "Employees Features" page, which offers 3 functions: Create new customer account, create new employee acount, and sign out. Only employee and admin accounts can access this page! This will be further developed in the future.
6) Created a "Customer Features" page, which currently offers no functionality except for the option to sign out (returns user to "Log In" page). This will be further developed in the future.
7) Implemented a "Users" class, which creates and holds the essential information of users, including but not limited to: Name, Email, Password, and Role.

To access the employee features, please use {username: admin} and {password: admin} to access the feature to create an employee account.

This deliverable submission includes the project folder for our application. Within this project folder, there are 7 .java files and 6 .xml files including: LogInPage.java, CreateCustomerAccount.java, CreateEmployeeAccount.java, LogInSuccessful.java, EmployeeFeatures.java, CustomerFeatures.java, Users.java, activity_log_in_page.xml, acitvity_log_in_succesful.xml, activity_create_customer_account.xml, activity_create_employee_account.xml, activity_customer_features.xml, activity_employee_features.xml.
