# Study Helper
- Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Authors](#authors)

## <a name="purpose"></a>Project purpose

This application allows the teacher to monitor the student's 
work (after the student's permission).
<hr>
Application going to specified email every scheduled time and check incoming message. 
If some message has subject "screen" or "webcam", application will take a picture 
from screen or webcam accordingly from computer where running this application and
send to person who sent mail with subject "screen" or "webcam". Incoming message with
with subject "screen" or "webcam" will be automatically deleted.
So if you want to get photo you need to send on certain email with subject "screen" or "webcam". 

Available functions: 
* take screenshot
* take webcam photo
* check incoming email
* send email with photo
* delete email 
* save photo on computer

<hr>

## <a name="structure"></a>Project Structure
* Java 8
* Spring boot
* Maven 4.0.0
* log4j 1.2.17

<hr>

## <a name="developer-start"></a>For developer
Open the project in your IDE.

In Config.java file set yours appropriate properties

APP_EMAIL-email where you need to send a message with subject "screen" or "webcam" to get screenshot back.

APP_EMAIL_PASSWORD- password for APP_EMAIL.

FOLDER_PATH-folder on your computer(where ranning app) where app will save photo.

EMAIL_FOLDER_NAME- name of folder in mail which will be checked by application.

SCREEN_COMMAND and WEBCAM_COMMAND- secret words in email subject which you send to APP_EMAIL.

EMAIL_CHECKING_FREQUENCY-how ofter app will check APP_EMAIL to find message with certain subject.

HOST, PORT and other properties are set for google mail (gmail).

In application.properties file set log properties.

Run the project.
 
<hr>

## <a name="authors"></a>Authors
[Mykhailo Kramar](https://github.com/Mykhaylo12?tab=repositories)

