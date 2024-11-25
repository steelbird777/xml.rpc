import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.URI;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try {
            // Configure the client with server URL
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            URI uri = new URI("http://localhost:9090/RPC2"); // Server URL
            config.setServerURL(uri.toURL()); // Convert URI back to URL
            
            // Create the client object
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // Prepare the order details (item name, quantity)
            Object[] orderDetails = new Object[] {
                new Object[] { "apple", 5 },
                new Object[] { "banana", 10 },
                new Object[] { "orange", 3 }
            };

            // Send the order to the server and get the total amount
            Object result = client.execute("order.processOrder", new Object[]{orderDetails});
            
            // Display the total amount
            System.out.println("Total amount for the order is: $" + result);
        } catch (Exception e) {
            System.err.println("Client: " + e);
        }
    }
}

