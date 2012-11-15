Spitter
=======

Introduction
------------

This project imitates the fundamental features of Twitter. It is based on 3 layer architecture. Presentation layer is carried out in Spring MVC, Tiles2, and JavaScript. Service and Persistence layers use core Spring functionality leveraged by Hibernate. The entire project is built in Maven and current connection settings use PostgreSQL database.

Of course this is not how real Twitter application works. Using traditional database for their purpose is completely unfeasible. Here is an interesting presentation about accessing big datasets in real-time: link_

.. _link: http://www.slideshare.net/nkallen/q-con-3770885?from=ss_embed

The project was inspired by "Spring in action" by C. Walls.

Project Features
-------------

The application enables to register a new account or sign in using existing one. Each registered user can navigate the application through the dashboard where he can post a new message (visible to all who follow him), search for other people, follow and unfollow other users. The user has full control over his own posts and can delete them at any time, as well as customize his own profile (including providing personal information and uploading avatar).

List of features (user):

#) Register new account

#) Remind password on email

#) Customize the profile (personal details, upload/remove picture)

#) Post message which would be visible to the user and people who follow him/her

#) Delete his/her messages

#) Search for other users and browse their profiles

#) Follow/Unfollow other users (link in their profile)

#) Browse list of followers/following

List of features (admin):

#) Everything and standard user

#) Access to admin panel (restricted for other users)

#) Browse list of all users, or by specified query

#) Delete particular user


Quick Start Guide
-----------------

Before you deploy this project on the server (if you're using Eclipse make sure to "Maven>Update project" beforehand)

``tomcat:run``

make sure to provide your own details regarding database connection and email used as a reminder (my implementation facilitates GMail).

In spring-servlet.xml, lines 32 and 33 -- make sure to provide username and password to your email account

``<property name="username" value="spitter.reminder" />
<property name="password" value="yourPass" />``

In spring-servlet.xml, line 74 provide the path to your database (you can name it whatever you want):

``<property name="url" value="jdbc:postgresql://localhost:5432/spitter_db"></property>``

and in line 76 provide your password:

``<property name="password" value="yourPass" />``

Project forces using HTTPS thus adjust your server (in Tomcat normally it's 8443 port) or in spring-security.xml in lines 10, 11, and 12 get rid of attribute 

``requires-channel="https"``