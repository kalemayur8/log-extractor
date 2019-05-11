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

*cmd :\> java -jar prod-log-extract-0.0.5.jar "app1" "2018-07-11 10:01" "2018-07-11 11:59" "local" "stag"*

fetch logs from all nodes of app1.

*cmd :\> java -jar prod-log-extract-0.0.5.jar "app2" "2018-07-11 10:01" "2018-07-11 11:59" "local" "stag"*

Fetch logs from all nodes of app2.

*cmd :\> java -jar prod-log-extract-0.0.2.jar "app3" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"*

Fetch logs from all nodes of app3.

*cmd :\> java -jar prod-log-extract-0.0.2.jar "app1-ui" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"*

Fetch logs from only UI nodes of app1, lets say we have app1 deployed on node1, node2, node3,  node4 while only node1 and node2 is exposed for end user to use. And in this case we need logs only for users did activity. we can go for it.

*cmd :\> java -jar prod-log-extract-0.0.2.jar "app4" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"*

Fetch logs from all nodes of app4.

*cmd :\> java -jar prod-log-extract-0.0.2.jar "app1,hq2080" "2018-07-11 11:01" "2018-07-11 11:59" "local" "prod"*

Fetch logs only from hq2080 node of app1.
