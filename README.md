# coding-assignment-ubs
coding-assignment-ubs


## SERVICES ##

proposal service
- main service

calculation service
- receiving calculation events for proposal via queue
- creating calculations
- creating calculation events

email service
- receiving events via queue
- sending emails
- in real world should have own table with client emails

## KAFKA ##

This project  is  using  kafka

https://kafka.apache.org/quickstart

bin\windows\zookeeper-server-start.bat config/zookeeper.properties
bin\windows\kafka-server-start.bat config/server.properties