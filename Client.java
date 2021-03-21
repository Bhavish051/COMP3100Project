import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
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

  public Client(String Ipaddress, int Port, String sorter) {
    try {
      this.socket = new Socket(Ipaddress, Port); // Used to establish the connection
      this.input = new BufferedReader(new InputStreamReader(System.in));
      this.out = new DataOutputStream(socket.getOutputStream());
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String args[]) {
    String sorter = "";
    if (args.length == 2) {
      sorter = args[1];
    } else {
      sorter = "ATL";
    }
    Client client = new Client("127.0.0.1", 50000, sorter);
  }
}
