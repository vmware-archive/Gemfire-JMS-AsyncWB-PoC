#Integration of JMS and Gemfire async Write-behind

This project provides a sample implementation of async write-behind after reading a message from JMS. Demo makes use of Apache activemq, mysql and Gemfire. Before we good run this project, we need to have activemq and mysql running on the local sandbox.

## Steps to run the project

1. Install Apache Activemq 

   http://activemq.apache.org/getting-started.html#GettingStarted-UsingHomebrewinstalleronOSX

2. Install mysql

3. Create a mysql database **gemfiretest** and create a table **trade**

4. Project can be executed either as spring boot project or by using scripts in **grid** folder