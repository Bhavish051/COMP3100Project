# COMP3100Project

# Cost-Efficient Resource Allocator for Distributed Systems (PAGE 1)

# GROUPMEMBERS (PAGE 1)
Zoheb Eftali (45983569) <br>
Bhavish Dhanda (45525005) <br>
Satyamurthy Bale (45527652) <br>
# INTRODUCTION (PAGE 2)
For this project, the main goal is to develop a client-sever simulator that schedules jobs for distributed systems. The aim for this stage is to create a client-side simulator with a simple job dispatcher, which sends all jobs to the largest server type which is based on number of CPU cores. This stage, and for the entirety of the project, is referenced accordingly to the ds-sim simulation protocol. The simulator is desigened to work with different work and data sizes. 

# SYSTEM OVERVIEW (PAGE 2)
This system aims to at first perform a 3 way handshake to establish the connection followed by reading a system.xml file to get the details of all the servers available. Also intially it authenticates the user by retrieving his username as well to validate the details. This gives the client an overview of all the resources available. After this the client starts to read byte by byte from the server to evaluate the type of command sent. Only after the server sends a JOBN command to the client and receives the client's request does the scheduled tasks begin. The SCHD command will send jobs to the server and assign them to the appropriate variable sort. A few protocols will be implemented in the code to insure its protection, effectiveness, and performance. The username for our group's handshake will be AUTH, which will be a specific form of string character. To maintain the effectiveness of the handshake and procedure, commands such as 'NONE,' 'ERR,' 'REDY,'OK,' and 'QUIT' will be used. ERR is used to indicate that there is a protocol malfunction. NONE  indicates that there are no more work openings. The command REDY will be used to indicate that the next command simulation is complete. The command QUIT will be used to stop the stimulation.

Refer to the attached image for a digram describing the functioning of the client in a brief manner.

# DESIGN (PAGE 3)
This system is designed in a very modular way with everthign distributed among appropriate methods and into a different class when required.
To start with the system has two classes in a single package named "Assignment".
* Client.java: This is the main driver class which runs the program with the following functions
        Public Client: The public constructor which initialises the parameters of the client: Input stream as a Buffered Reader and an output stream as a Data Output stream.
        Public start: This method drives the client so that when it first starts it does a 3 way handshake to establish the connection and keeps reading lines from the server until it sends a NONE message to end the program.
        Public void main: The main method that initialises that client object and calls the start method.
        Public sendMessage: This message takes a string as a parameter and sends it through to the server as an array of bytes
        Public readMessage: This method reads data from the input stream as a character buffer then converts it to a string and returns that to output it.
        Public void connect: This method establishes the connection to the server in a try clause where it first attempts to connect and if unsuccessfull waits a few seconds and then tries again. This creates a new Socket with the specified address and port while creating the client object and if the connection is successfull sends a "CONNECTED" message.
        Public static readXML: This function takes a String as a parameter which is the name of the file the program reads data from about information about the servers. This function uses a DocumentFactoryBuilder to parse an XML file to read the list of servers and keeps adding them in an arrayList as it reads and returns the arrayList.

* Server.java: This one contains the basic definition of a server with all its attributes 
        String type: A String for the type of server like large, small etc
		String id: A String as an identifier for the server
		int limit: An integer to represent the number of unique servers of a particular type
		int bootTime: An integer to represent the time taken by a server to start
		float hourlyRate: A floating point number for the price of usage of server 
		int coreCount: An integer for the number of cores in a server
		int memory: An integer for the RAM of the server
		int disk: An integer to store the storage limit of the server.

# IMPLEMENTATION (PAGE 4)

The system works by establishing a connection with the client and server through a 3-way handshake, ensuring a connection is made. It then asks for the authentication of the user; name of the user matches name of the user-system. Once this initial connection is made, the client and server can communicate with each other up until the connection is interrupted or timed out. The client then opens and reads the System.xml files using the libaries mentioned below to gather a list of servers as well as its related information such as
* Number of cores 
* Cost 
* Size of server (in MB)

The client proceeds to store the server and its attributes in an ArrayList so that it can begin scheduling jobs. Jobs are scheduled through a while loop, which only stops if the client receives _"NONE"_ from the server. The main method used is _sendMessage(AllToLargest(msg, t.get(largestServer)))_. This sends all the jobs to the known largest server. The client finds the first largest server (_findLargestServer_) and sends each job found to that server (_AlltoLargest_).

The libaries used for the project are: 
* java.net.* - Allows connections with the server
* java.util.ArrayList - Creation of lists for the server
* java.util.concurrent.TimeUnit - Used to time out connection if left inactive for a certain amount of time 
* javax.xml.parsers.DocumentBuilder and javax.xml.parsers.DocumentBuilderFactory - Helps to open and read language files (XML files) 
* org.w3c.dom.Document, org.w3c.dom.Element and org.w3c.dom.NodeList - Needed for file input
* java.io.* - Allows for input and output (essential for communication between client and server)

The main data structure used in this project were linear; an array list and node mapping. An array is created to receive messages and split its from the server. Node mapping was used to read/write the information from the file and accessible by the server and used in the client. 
Functions/Components of the simulator:
* public Client(string address, int port) : Constructor for the client. IP address and port number are inputs 
* public void start() : Main body of the program. This is used to initiate connection via 3-way handshake and schedule jobs to largest server with the use of the methods _findLargestServer_ and _AllToLargest_ 
* public String AllToLargest(String job, Server s) : Sends all jobs to the largest server with the inputs String _job_ and Server _s_. Server _s_ is used to get the type of server whilst String _job_ is used to split the string.
* public String findLargestServer(ArrayList<Server> s) : Finds the first largest server. Determined by the amount of cores in each server. Input _s_ is used to find the size of each server through the method _getCores()_ in the Server class. 
* public void sendMessage(String outStr) and private String readMessage() : Methods used to send/receive messages from the server. Essential as these methods are used as the main form of communication between server and client and are used in the initial handhsake connection and retrieval of server information.
* public void connect (String address, int port) : Method used for connection between client and server through the address and port number. Usage of socket. Contains condition for the connection to be stopped if timed out (uses of less resources).
* public void main (String args[]) : Main part of the program. Used to initiate everything. Includes the reading/writing of the XML file and instantiating all server class related methods for use in the client class. 
The entire workflow is focused on a few main basic principles that are built on top of one another in order to function effectively and efficiently. To insure protection, the socket and streams are set privately before being specified and created in the client process. The next approach is crucial to the software because it establishes the bond and acts as a handshake between the server and the client.

There are two methods for sending and receiving messages that will be used in the application and are crucial to the code's functionality. The sendMSG method gets message bytes from a string, converts them to characters, and then writes and flushes the output stream.

The run method is essential for running the ds-sim protocols and commands, as well as ensuring that all steps are completed and the mechanism functions properly. The HELO command will always come first, followed by AUTH and  group number authentication. After that, the device will say it is ready, and it will use'readXMLFile' to read the selected input XML file.

If nothing happens, the process will return a NONE and exit. The following section will deal with methods for scheduling and sorting jobs that are found deep within the code. OK is used to insure that everything is running smoothly and correctly, and it is returned after the previous instruction. To assist in this action, it will name 'allToLargest' and largestServer.It would always be possible to send messages after this has happened for all jobs and before QUIT has been received. A QUIT protocol is invoked to complete the handshake, and the output stream and socket are locked.

ReadXMLFiles, an array list of servers for reading files, is another important process. This is crucial for reading all of the input XML files, and it makes use of nodes, arraylists, and loops to insure that the process runs smoothly. The structure is built using paper builders and factories. The nodelist will call the server's elements, and a loop will iterate over the server's elements.

All of the elements are called and placed into a variable called serverList, which is then returned. This is critical for reading pre-compiled and input files when they are accessed.

The key method is the main, and it will call the client and server's addresses and ports. 

# DEMONSTRATION 
* The machine on which the program is running should have a jdk installed if not run "sudo apt install default-jre"
* The machine should allow connections over port "5000"
* The machine should have the "ds-client" and "ds-server" files along with the appropriate configuration files 
* To run the program 
    > Open a Terminal and run ds-server with a configuration file by "./ds-server -c [config-file] -v all"
    > On another terminal run the client by 
        > First make sure the "Server.java" is compiled and then compile "Client.java"
        > Run "java Client.java"


# REFERENCES (PAGE 5)
GitHub - https://github.com/Bhavish051/COMP3100Project
GitHub for ds-server files - https://github.com/distsys-MQ/ds-sim.git
