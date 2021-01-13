About The Project
=================
This is a project for csc207.
This program is used for organizing a tech conference, where organizers, speakers, and attendees are allowed to use
various features to perform actions related to the conference.

Authors
=======
Group_0343.
* Yunni Qu
* Ning Song
* Lingfei Li
* Shujie Deng
* Zhaoyi Cheng
* Richard Yan
* Yushi Qin
* Peixuan Xie


Getting Started
===============
* You need to add an sql jar to library with version 8.0.21 before running the program.
  In File -> Project Structure -> libraries, please add the following line, them choose apply:
  mysql:mysql-connector-java:8.0.21
* Start the project by running the class Main in controllers.


Some Conventions You May Want To Know
=====================================
* According to our design, sometimes validity is checked after you input all values. For example, when an organizer
  changes the capacity of an event, validity will be checked after the user put in both event id and the capacity he
  or she wants to change to.


Design Pattern
==============
* gateway uses facade design pattern
  the classes ending with Table implements DB updater
  and they are called in DBtoManagerUpdater.
* We use facade design pattern for the system.
  The SystemBuilder provide a simple interface and connected with interfaces of subsystems.
  SystemBuilder shows the main menu to user and then user can choose different type of features to enter different submenu.
  Each subsystems is divided by independent features, so it will not affect each others.
  Each subsystem is only influenced by SystemBuilder.



Configuration for running the code
===============================
1. implementation of database:
* In File -> Project Structure -> libraries, please add the following line, the choose apply:
mysql:mysql-connector-java:8.0.21


Some Conventions You May Want To Know
=====================================
* According to our design, sometimes validity is checked after you input all values. For example, when an organizer
  changes the capacity of an event, validity will be checked after the user put in both event id and the capacity he
  or she wants to change to.
* when the program ask for an int, for example id of some entity, pleas only enter integers, because we used int
  scanners.


Design pattern in this program
===============================
*We use facade design pattern for the system.
    The SystemBuilder provide a simple interface and connected with interfaces of subsystems.
    SystemBuilder shows the main menu to user and then user can choose different type of features to enter
    different submenu.
    Each subsystems are divide by independent features so it will not affect each others. Each subsystem is
    only influenced by SystemBuilder.



Features implemented in phase 2
===============================
1. Mandatory extensions
* There will now be many types of events. A one-speaker event is the same as a "talk" from Phase 1. You can have
  multi-speaker events, like a panel discussion, and no-speaker events, like a party. Events can last as long as
  you want. You can measure the duration of an event in hours, or minutes. You get to decide.
    - Details:
    （1）There are in total 3 type of events: talk, which has one speaker; discussion, which has multi-speaker
    and party, which do not have speakers。
    （2）There is no restriction for each event, and they are measured in hours and can be held cross a day.
* Events can be canceled by one organizer.
    - Details: When cancelling an event, the organizer is able to see the schedule list and choose which event
    to be cancelled.
* New user type -- VIP. Attendee will have two types, VIP and non VIP. Only VIPs user can access VIP-only events.
  Organizer can upgrade an attendee to VIP or downgrade a VIP to a normal attendee. After the upgrading and downgrading,
  the attendee will receive a message about this change.
* Organizers can also create Speaker accounts and Attendee accounts(including VIP attendee and non-VIP attendee)
* Each event has a maximum number of people who can attend it. This capacity of the event can be set when the event is
  created and also changed later, by an organizer.

2. Optional extensions
* Enhance the user's messaging experience by allowing them to "mark as unread", delete,
  or archive messages after reading them.
* Add additional constraints to the scheduling for various types of events. When organizers are creating events,
  they can see a suggested list of rooms that fit the requirements of their event. (the capacity of the room
* Allow the system to support additional user requests (e.g. dietary restrictions, accessibility requirements) where
  organizers can tag a request as "pending" or "addressed". All organizers see the same list of requests, so if one
  tags a request as addressed, all other organizers can log in and see that.
* Use a database to store the information from your program through specific gateway class(es) so that you do
  not violate Clean Architecture and still have an Entity layer.

3. Our own extensions
* Organizers can switch to their attendee account and back to organizer account by simply selecting the switch
  account option in the menu.
* Our system automatically identifies your account type (ie organizer/speaker/attendee) when you login.
* The friend system allows friend request functionality. When A wants to be friend with B, A should first send a friend
  request to B. B is able to view friend request and decide whether or not to add A as a friend.
* Attendees are able to cancel the event he/she has signed up for.
* Attendees are allowed to manage their account system. They are able to change their password and view basic account
  information such as friendlist and whether or not they are vip. Organizers can also change their password.
* Organizer can upgrade an attendee to VIP or downgrade a VIP to a normal attendee. After the upgrading and downgrading,
  the attendee will receive a message about this change.
* Organizer can upgrade an event to VIP-only event or downgrade the VIP-only event.


Usage
=====
** Login and register Guide **
In the main menu, you can choose to register a new account as attendee or login with username and password.
You can not register more than one account with same username.

Speaker accounts can only be registered by Organizers, these accounts will have default password "111111", it can be
change later by login into the Speaker account.

Our system automatically identifies your account type (ie organizer/speaker/attendee) when you login.


** Attendee Guide **
An attendee has a unique username, a unique userid that starts with 1, and a password. The default vip status of the
attendee is set to be false.

An attendee is able to:
* Go to message page.
    - In the message page, an attendee is able to:
      * message your friend. You can only message to other attendees.
      * check inbox. (where you can mark messages as unread, delete messages, archive messages and reply messages
      sent from a attendee friend)
* sign up for events.
    - please be patient after choosing the event you want to sign up for, it takes a bit longer than you thought.
* send friend request.
* If this attendee account is switched from organizer, you can switch back to organizer view.
* cancel an event that you registered for.
* make special requests.
* view friend requests.
* view account information.
* Quit the program.


** Organizer Guide **
An organizer has a unique username(ends with "_org"), a userid that starts with 3, and a password. Each organizer has a
corresponding VIP attendee account(which username is the same as organizer username except it does not end with "_org"
that you can switch to or switch back (you can switch back in attendee's main menu) with the same password.
To maintain the safety of the system, all organizers are created before the execution of the system. We have set up
several default organizer accounts. They are:
username; pwd(password)
yunni_org; 111
anicca_org; 111111
emolee_org; 111111
snow_org; 111111
Monica_org; ha
xiaoxiaopeigen_org; 111111

An organizer is able to:
* Goes to messageSystem.
    - In the message menu, an organizer is able to:
      * make announcement to all attendees in a particular event.
      * make announcement to all speakers in a particular event.
      * message individual attendee who are signed up for a particular event.
      * message individual speaker in a particular event.
* schedule events.
    - Organizers are able to schedule the event by specifying room number, date and time, duration of the event, the
    speaker, title of the event, the capacity of the event and whether or not this is a vip-only event.
* create speaker accounts.
    - The default password is set to be "111111"
* create attendee accounts(vip or non-vip)
* upgrade an attendee to vip and downgrade an attendee from vip.
* Upgrade / Downgrade an Event to / from vip.
    - please be patient after choosing 'upgrade' or 'downgrade', it takes a bit longer than you thought.
* change the capacity of an event.
    - according to our design, validity will be checked after you entered event id and the capacity you want to change
    to. So if it tells you 'you have failed to change capacity' after entering capacity you want to change to, it
    means that you entered wrong event id, or the number you entered is larger than room capacity, or the number you
    entered is less than number of attendees already registered for this event. If it tells you 'your input is
    invalid', it means you entered an invalid capacity number (entered 0 or negative number).
* switch to attendee view.
* change password.
* check the special request list and tag requests.
* Quit the program.


** Speaker Guide **
A speaker has a unique username, a userid that starts with 2, and a password. You can only create a speaker account
by organizers. (By choosing the 'create speaker accounts' option in the organizer menu). The default password for
a speaker account is "111111".

A speaker is able to:
* Goes to message menu.
    - In the message menu, a speaker is able to:
      * make announcement to all attendees in a particular event.
      * make announcement to individual attendee who have signed up for a particular event.
      * check inbox. (where you can mark messages as unread, delete messages and archive messages) (note that based on
      our program structure, a speaker can't reply to any message, and only receive message from organizers)
* change password
* Quit the program.

** About Gateway implementation and clean architecture **
  In gateway, there are two interface called DataAccessIn and DataAccessOut.
  The classes in data access layer (where reading and writing mysql happens) implements these interface,
  so when the classes in gateway needs the to read or write data, they call the interface in gateway,
  therefore clean architecture is not violated.



Design Decision and Explanation of Improvement of Code
========================================================
- Clean architecture:
1. We separate the controller and presenter for each system so that "System.out.println" only exists in presenter
course, which follows the single responsibility principle.
2. We extract LoginSystem from SystemBuilder. In phase2, LoginSystem is only responsible for users to login and
SystemBuilder acts as a navigator for users, which allows them to choose from different actions. So it follows single
responsibility principle.
3. For message system, instead of including all methods in one MessageSystem.java, we extract SpeakerMessageSystem,
AttendeeMessageSystem, OrganizerMessageSystem and InboxCheckingSystem so that each system is responsible for single
responsibility. And that also allows more extensions.
4. In entity, we make Event class to be abstract, and Party, Discussion and Talk are subclass of event. This allows
later extension for implementing more different type of events, so that follows the open-closed principle and
dependency inversion principle.


- User-friendly design
1. The program allow users to sign in with their usernames instead of the user IDs we provide them.
2. In the message system, if the user wants to reply to a send a message, they are allowed to see a list of usernames
they can send a message.
3. Add "loading..." when the program takes some time to run.
4. Organizers are allowed to switch back and forth to the corresponding attendee account by simply selecting the
switch account option in the menu.
5. Our system automatically identifies your account type (ie organizer/speaker/attendee) when you login.
6. When applying to a message, users can see the message content they reply to.
7. The friend system allows friend request functionality. After 2 users become friends, the system will send each
of them a notification(message) that they are friends now.
6. After the organizers downgrade/upgrade a user, the system will send them a notification(message) that they are
upgraded/downgraded.