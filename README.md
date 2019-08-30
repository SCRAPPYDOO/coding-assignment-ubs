## SERVICES ##

proposal service
- main service
- http://localhost:8881/swagger-ui.html

calculation service
- receiving calculation events for proposal via queue
- creating calculations
- creating calculation events

email service
- receiving events via queue
- sending emails(just a log)
- in real world should have own table with client emails

## PDFS ##
Assuming that we have common pdf storage, and when creating pdf we are sending it to this storage (in code im just using path),
in real world i would create pdf web service to manage pdfs.

Configurations: pls change the path if  u using  mac or linux in both proposal and calculation service

pdf-service:
  proposal:
    path: C://proposals/
  calculation:
    path: C://calculations/

## KAFKA ##

This project is using kafka, default configuration

https://kafka.apache.org/quickstart

## RUN ##

to run u can use command `./gradlew bootRun` in every service directory
u need to run kafka locally

## FRONTEND ##
didn't have time for creating basic frontend

## TESTS ##
same as above, but if u want me to create them, let me know :)
