# log-extractor
Extract Logs from Multiple Servers Easily with given timestamp easily.

Prerequisite 
==================

1. Install winscp and set it in environment path 
2. Spring 3.0 +
3. Java 8

Advantage
================
1. Using this project you can download server logs fast and easily.
2. It takes input timestamp, so that you can download logs within that timestamp.
3. Very easy to download server logs even in distrubitued environment.

Downloaded Logs you can easily analyse using notepad++ and regular expression to find out specific errors.

RegEx 
======
URL : https://regex101.com/

Requirement
================

To build a Project which can download logs quickly, provided all deployment structure of nodes.

Input
===========
1. winSCP ID, Password, HashKey.
2. Environment, Deployment Model, including all servers.
3. Server log file paths.
4. Output folder path, should be your local machine Hard drive.
5. Timestamp if require otherwise by default it will fetch last one hour data.

Commands to run this application 
===============================

java -jar prod-log-extract-0.0.5.jar "schedular" "2018-07-11 10:01" "2018-07-11 11:59" "local" "stag"
java -jar prod-log-extract-0.0.5.jar "customerlist" "2018-07-11 10:01" "2018-07-11 11:59" "local" "stag"
java -jar prod-log-extract-0.0.2.jar "audit" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"
java -jar prod-log-extract-0.0.2.jar "acp-ui" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"
java -jar prod-log-extract-0.0.2.jar "acp-event" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"
java -jar prod-log-extract-0.0.2.jar "acp,hq2080" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"

