# COMP3100Project

# Cost-Efficient Resource Allocator for Distributed Systems (PAGE 1)

# GROUPMEMBERS (PAGE 1)
Zoheb Eftali (45983569) <br>
Bhavish Dhanda (45525005) <br>
Satyamurthy Bale (45527652) <br>
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
The system works by establishing a connection with the client and server through a 3-way handshake, ensuring a connection is made. It then asks for the authentication of the user; name of the user matches name of the user-system. Once this initial connection is made, the client and server can communicate with each other up until the connection is interrupted or timed out. The client then opens and reads the System.xml files using the libaries mentioned below to gather a list of servers as well as its related information such as
* Number of cores 
* Cost 
* Size of server (in MB)

The client proceeds to store the server and its attributes in an ArrayList so that it can begin scheduling jobs. Jobs are scheduled through a while loop, which only stops if the client receives _"NONE"_ from the server. The main method used is _sendMessage(AlltoLargest(msg, t.get(largestServer)))_. This sends all the jobs to the known largest server. The client finds the first largest server (_findLargestServer_) and sends each job found to that server (_AlltoLargest_).

The libaries used for the project are: 
* java.net.* - Allows connections with the server
* java.util.ArrayList - Creation of lists for the server
* java.util.concurrent.TimeUnit - Used to time out connection if left inactive for a certain amount of time 
* javax.xml.parsers.DocumentBuilder and javax.xml.parsers.DocumentBuilderFactory - Helps to open and read language files (XML files) 
* org.w3c.dom.Document, org.w3c.dom.Element and org.w3c.dom.NodeList - Needed for file input
* java.io.* - Allows for input and output (essential for communication between client and server)

# REFERENCES (PAGE 5)
//TODO
