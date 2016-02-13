
import java.io.*;
import java.net.*;

public class AirBand {
	public final int PORT = 45320; 

	public static void main (String args[])
	{
		AirBand air = new AirBand();
		System.out.println("Airband Starting!");
		while(true)
		{
			if(air.recieveStrums())
			{
				System.out.print("Strum!");
			} else
			{
				System.out.println("Error!");
			}
			
		}
		
	}
	
	public boolean recieveStrums()
	{
		try
		{
			DatagramSocket socket = null;
			socket = new DatagramSocket(PORT);
			byte[] buf = new byte[3];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			if(buf[0] == 4 && buf[1] == 20)
			{
				socket.close();
				return true;
			}
               
			socket.close();
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			//get rekt
			return false;
		}
    }
 
}
