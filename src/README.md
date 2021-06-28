# Calculate annual Salary

# Liquibase
Database info is in the ```application.properties``` file

In the main ```application.properties``` file, you can find the path for ```changelog-master.yaml```
<br/>Here, you can register the changes to apply for the database structure

<b>How to create properties:</b>
https://docs.liquibase.com/concepts/basic/changelog-property-substitution.html

<b>How to create tables:</b>
https://docs.liquibase.com/change-types/community/create-table.html

<b>How to create foreign keys:</b>
https://docs.liquibase.com/change-types/community/add-foreign-key-constraint.html

For additional liquibase information, please go to the official website https://www.liquibase.org/

Before uploading the environment please create a Postgres database with the following credentials:
-> localhost:5432/mas_salary, password: admin, user: postgres