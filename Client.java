public class Client{
  private Socket socket		 = null; 
  private BufferedReader input = null; 
  private DataOutputStream out	 = null; 
  private BufferedReader in	 = null; 
  static private ArrayList<Element> serverType = null;
  static private ArrayList<Server>  servers = null;
  
  public static void main(String args[]){
    String sorter = "";
    if(args.length == 2){
      sorter = args[1];
    }
    else{
      sorter = "ATL";
    }
    Client client = new Client("127.0.0.1", 50000, sorter);
  }
}
