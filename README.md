# COMP3100Project

# PROJECTTITLE (PAGE 1)
Cloud Job Schedular or Cost-Efficient Resource Allocator for Distributed Systems (can do one of these 2 or one of your choice)

# GROUPMEMBERS (PAGE 1)
FIRST NAME, LAST NAME (STUDENT ID)
Zoheb Eftali ()
Bhavish Dhanda (45525005)
Satyamurthy Bale (45527652)
# INTRODUCTION (PAGE 2)
For this project, the main goal is to develop a client-sever simulator that schedules jobs for distributed systems. The aim for this stage is to create a client-side simulator with a simple job dispatcher, which sends all jobs to the largest server type. This stage, and for the entirety of the project, is referenced accordingly to the ds-sim simulation protocol.

# SYSTEM OVERVIEW (PAGE 2)
//TODO


# DESIGN (PAGE 3)
//TODO

# IMPLEMENTATION (PAGE 4)
//TODO
The way this system works is at the begining of the client and server it performs a 3 way handshake to ensure the connection is established and after that does the authentication of the user.
Once all of this is done the client and server are ready to communicate with each other freely.
After this the first thing this does is read the System.xml file to gather a list of servers available and all the related information about them like their number of cores, cost, how large it is. The client then stores all the servers with all of their attributes in an ArrayList of type server objects.
Then the process of scheduling the jobs begins where after the client detects that the server has sent a job rather than a message or anything else it schedules it to the largest server and sends back the name of the server that is utilised.

# REFERENCES (PAGE 5)
//TODO
