Spitter
=======

Introduction
------------

This project imitates the fundamental features of Twitter. It intends to dempnstrate the useage of Maven, Spring framework (in partucular MVC and Security) in conjunction with Hibernate used as an ORM tool to hit the datebase (PostgreSQL). Presentation layer is implemented using Tiles2. Application features also JavaScript.

Of course this is not how real Twitter application works. Using traditional database for their purpose is completely unfeasible. Here is an interesting presetation about accessing big datasets in real-time: link_

.. _link: http://www.slideshare.net/nkallen/q-con-3770885?from=ss_embed

The project was inspired by "Spring in action" by C. Walls.

Project Features
-------------

The application enables the user to register. Each registered user can navigate the application through the dashboard where he can post his/her messaged, search for other people, follow and unfollow as they please. The user has full control over his/her own posts and can delete them at any time as well as profile where provided information can be customized as per user's request (it includes prociding personal information and uploading avatar picture).

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

Project force using HTTPS thus adjust your server (in Tomcat normally it's 8443 port) or in spring-security.xml in lines 10, 11, and 12 get rid of attribute 

``requires-channel="https"``