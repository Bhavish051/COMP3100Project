# COMP3100Project

# Cost-Efficient Resource Allocator for Distributed Systems (PAGE 1)

# GROUPMEMBERS (PAGE 1)
FIRST NAME, LAST NAME (STUDENT ID)
Zoheb Eftali (45983569)
Bhavish Dhanda (45525005)
Satyamurthy Bale (45527652)
# INTRODUCTION (PAGE 2)
For this project, the main goal is to develop a client-sever simulator that schedules jobs for distributed systems. The aim for this stage is to create a client-side simulator with a simple job dispatcher, which sends all jobs to the largest server type. This stage, and for the entirety of the project, is referenced accordingly to the ds-sim simulation protocol.

# SYSTEM OVERVIEW (PAGE 2)
//TODO
This system aims to at first perform a 3 way handshake to establish the connection followed by reading a system.xml file to get the details of all the servers and in the end reads the particular string received to evaluate on a one by one basis what needs to be done. It parses the first 4 characters of the string to see if it is a job that needs to be scheduled or just a message like QUIT received from the server.


# DESIGN (PAGE 3)
//TODO
This system is designed to 

# IMPLEMENTATION (PAGE 4)
//TODO
The way this system works is at the begining of the client and server it performs a 3 way handshake to ensure the connection is established and after that does the authentication of the user.
Once all of this is done the client and server are ready to communicate with each other freely.
After this the first thing this does is read the System.xml file to gather a list of servers available and all the related information about them like their number of cores, cost, how large it is. The client then stores all the servers with all of their attributes in an ArrayList of type server objects.
Then the process of scheduling the jobs begins where after the client detects that the server has sent a job rather than a message or anything else it schedules it to the largest server and sends back the name of the server that is utilised.
The libaries used for the project are: 
* java.net.* - Allows connections with the server
* java.util.ArrayList - Creation of lists for the server
* java.util.concurrent.TimeUnit - Used to time out connection if left inactive for a certain amount of time 
* javax.xml.parsers.DocumentBuilder and javax.xml.parsers.DocumentBuilderFactory - Helps to open and read language files (XML files) 
* org.w3c.dom.Document, org.w3c.dom.Element and org.w3c.dom.NodeList - Needed for file input
* java.io.* - Allows for input and output (essential for communication between client and server)

# REFERENCES (PAGE 5)
//TODO
