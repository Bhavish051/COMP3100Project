import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.lang.model.element.Element;

public class Client {
  private Socket socket;
  private BufferedReader input;
  private DataOutputStream out;
  private BufferedReader in;
  static private ArrayList<Element> serverType;
  static private ArrayList<Server> servers;
  private DataInputStream DIS;
  private DataOutputStream DOUT;


  public Client(String Ipaddress, int Port, String sorter) throws Exception{

      this.socket = new Socket(Ipaddress, Port); // Used to establish the connection
      this.input = new BufferedReader(new InputStreamReader(System.in));
      this.out = new DataOutputStream(socket.getOutputStream());
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      DIS = new DataInputStream(socket.getInputStream());
      DOUT = new DataOutputStream(socket.getOutputStream());


  public static void main(String args[]) {
    String sorter = "";
    if (args.length == 2) {
      sorter = args[1];
    } else {
      sorter = "ATL";
    }
    Client client = new Client("127.0.0.1", 50000, sorter);

    While(!EOF)   // Pseudocode
    { 
      Read(client.DIS);
    }
    
  }

  private static void Read(DataInputStream D) throws Exception
  {
      byte[] readingArray = new byte[D.available()];
      D.read(readingArray);
      String Message = new String(readingArray);
  }
}
