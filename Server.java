import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.HashMap;
import java.util.Map;

public class Server {
    
   // Item catalog with prices
   private static final Map<String, Double> itemCatalog = new HashMap<>();
   
   static {
       itemCatalog.put("apple", 1.0);  // Price per apple is $1.0
       itemCatalog.put("banana", 0.5); // Price per banana is $0.5
       itemCatalog.put("orange", 0.75); // Price per orange is $0.75
   }

   // Method to process the order and calculate total amount
   public double processOrder(Object[] orderDetails) {
       double totalAmount = 0.0;
       
       for (Object detail : orderDetails) {
           Object[] item = (Object[]) detail;
           String itemName = (String) item[0]; // Item name
           int quantity = (int) item[1];       // Quantity

           // Check if the item exists in the catalog
           if (itemCatalog.containsKey(itemName)) {
               totalAmount += itemCatalog.get(itemName) * quantity;
           } else {
               System.out.println("Item " + itemName + " not found in catalog.");
           }
       }
       
       return totalAmount;
   }

   public static void main(String[] args) {
       try {
           WebServer server = new WebServer(8080);  // Create server at port 8080
           PropertyHandlerMapping phm = new PropertyHandlerMapping();
           
           phm.addHandler("order", Server.class); // Bind "order" to the Server class
           server.getXmlRpcServer().setHandlerMapping(phm);  // Set handler mapping
           
           server.start();  // Start the server
           System.out.println("XML-RPC Server started at port 8080. Accepting requests...");
       } catch (Exception exception) {
           System.err.println("Server: " + exception);
       }
   }
}
