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
