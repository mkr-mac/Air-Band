import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AirBandClientTest {
	
	    private static final int PORT = 45320;
	    private static final String ip = "172.19.37.183";
	    
	    public static void main(String[] arg)
	    {
	   
	    	try
	    	{
	    		DatagramSocket socket = new DatagramSocket();
	           // send request
	            byte[] buf = new byte[3];
	            buf[0] = 4;
	            buf[1] = 20;
	            buf[2] = 1;
	            InetAddress address = InetAddress.getByName(ip);
	            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
	            socket.send(packet);
	            socket.close();
	    	} catch(Exception e)
	    	{
	    		System.out.println("Error!");
	    	}
	    }
}

