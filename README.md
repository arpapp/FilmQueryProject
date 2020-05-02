## Overview

This program serves as a way for a user to interact with a film database. Through this program, the user is able to view information about films and actors.

The program begins by prompting the user for what kind of information they would like to see about films. If they have the ID, they can view that film directly or they can search by keyword. Their input is fed to the Database Accessor Object class that maintains all queries for the film database being used. Films matching user input are returned and information regarding language, cast, rating, etc. are displayed.

The methods within the Accessor obtain relevant film and actor information to then set it to new actor and film objects. The requested information is the displayed for each actor and film object.

## Technologies used

- Java
- MySQL
- Eclipse
- Atom
- Maven
- GitHub
- MAMP

## Lessons Learned

This project was a good lesson in how Java programs can access and utilize database information. The biggest contributors to my knowledge were the methods in which information was obtained from a specified column and then used to set object fields. For me, this was a clear "connect-the-dots" moment between what is housed in a database and what is done/can be done in a Java program.

This project was also good practice for constructing SQL/MySQL queries. I had to use quite a few keywords that we've been going over in class. However, I would still like to practice with the "JOIN" keyword as that one was the most difficult to use successfully.

Some topics for further exploration would be how the literal connection is made between Java and a database. I was able to put the pieces together in order to connect but I would like to learn more specifically about drivers and the Connection objects.
