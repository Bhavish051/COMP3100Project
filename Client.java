import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.*;
public class Client {
	// initialize socket and input output streams
	private static Socket socket = null;
	private BufferedReader input = null;
	private DataOutputStream out = null;
	private BufferedReader in = null;
	// constructor to put ip address and port
	public Client(String address, int port) throws IOException {
		connect(address, port);
		input = new BufferedReader(new InputStreamReader(System.in));
		out = new DataOutputStream(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	private void start () {
		boolean connected = false;
		String outStr = "";
		
		sendMessage("HELO");
		readMessage();

		sendMessage("AUTH user");
		readMessage();
		//Reads ds-system and populates it into array Server 't'
		ArrayList<Server> t = new ArrayList<Server>();
		t = readXML("ds-system.xml");
		
		//Finds index of largest server; most cores
		int largestServer = findLargestServer(t);
		
		connected = true;

		sendMessage("REDY");

		String msg = readMessage();

		if (msg.contains("NONE")) { 
			sendMessage("QUIT");
			connected = false;
		}
		//Job scheduler to largest server (THERE IS A BUG WITH THIS I CAN'T SEEM TO FIGURE IT OUT >>> MAIN METHOD FOR ASSIGNMENT)
		else if(msg.contains("JOBN"))
		{
			while(!msg.contains("NONE")){
				if(msg.contains("JCPL")){
					sendMessage("REDY");
					msg = readMessage();
				}
				else{
					sendMessage(AllToLargest(msg, t.get(largestServer)));
					msg = readMessage();
					sendMessage("REDY");
					msg = readMessage();
				}
			}
		}
	
		while (connected){
			try {
				outStr = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (outStr.equals("QUIT")){
				connected = false;
				sendMessage("QUIT");
				break;
			}

			sendMessage(outStr);
			readMessage();
		}
		try {
			//Closes input, output and socket connection once QUIT message has been received; terminates connection
			if (readMessage().contains("QUIT")){
				input.close();
				out.close();
				socket.close();
			}	
		} catch (IOException i) {
			System.out.println(i);
		}
		System.exit(1);
	}
		
	//Sends all jobs to largest server 
	private String AllToLargest(String job, Server s){
		String[] strSplit = job.split("\\s+");
		return "SCHD " + strSplit[2] + "" + s.getType() + "" + (s.getLimit()-1);
	}
	
	//Finds the largest server; counts through cores until largest is found then returns largest
	private int findLargestServer(ArrayList<Server> s){
		int largest = 0;
		for (int i = 0; i < s.size(); i++){
			if (s.get(i).getCores() > largest){
				largest = i;
			}
		}
		return largest;
	}
	
	//Send message to server
	private void sendMessage (String outStr) {
		byte[] byteMsg = outStr.getBytes();
		try {
			out.write(byteMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Display output from client
		System.out.println("OUT: " + outStr);
	}
	
	//Read message from server
	private String readMessage () {
		String inStr = "";
		char[] cbuf = new char[65535];
		try {
			in.read(cbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		inStr = new String(cbuf, 0, cbuf.length);

		//Display input from server
		System.out.println("INC: " + inStr);

		return inStr;
	}
	
	//Establishes connection to initiate handhsake
	private static void connect(String address, int port) {
		double secondsToWait = 1;
		int tryNum = 1;
		while (true) {
			try {
				System.out.println("Connecting to server at: " + address + ":" + port);
				socket = new Socket(address, port);
				System.out.println("Connected");
				break; 
			} catch (IOException e) {
				secondsToWait = Math.min(30, Math.pow(2, tryNum));
				tryNum++;
				System.out.println("Connection timed out, retrying in  " + (int) secondsToWait + " seconds ...");
				try {
					TimeUnit.SECONDS.sleep((long) secondsToWait);
				} catch (InterruptedException ie) {
          ie.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		Client client = new Client("127.0.0.1", 50000);
		client.start();
	}

	public static ArrayList<Server> readXML(String fileName){
        ArrayList<Server> serverList = new ArrayList<Server>();
		
		try {
			//Read XML file
			File systemXML = new File(fileName);

			//Document parser
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(systemXML);

			doc.getDocumentElement().normalize();
			NodeList servers = doc.getElementsByTagName("server");
			for (int i = 0; i < servers.getLength(); i++) {
				Element server = (Element) servers.item(i);

				String type = server.getAttribute("type");
				int limit = Integer.parseInt(server.getAttribute("limit"));
				int bootupTime = Integer.parseInt(server.getAttribute("bootupTime"));
				float hourlyRate = Float.parseFloat(server.getAttribute("hourlyRate"));
				int coreCount = Integer.parseInt(server.getAttribute("coreCount"));
				int memory = Integer.parseInt(server.getAttribute("memory"));
				int disk = Integer.parseInt(server.getAttribute("disk"));
				
				Server s = new Server(type,limit,bootupTime,hourlyRate,coreCount,memory,disk);
				serverList.add(s);
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	return serverList;
    }

	
}
