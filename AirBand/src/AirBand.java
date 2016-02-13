
import java.net.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class AirBand {
	
	public static final int PORT = 45320; 
	public DatagramSocket socket = null;
	
	// This is returned from the socket if something bad happened and it crashed.
	public static final byte ERROR_BYTE = -1;
	// This is returned if there was no note.
	public static final byte NO_NOTE = -2;
	private DatagramPacket packet;
	private byte buf[];
	
	public static void main (String args[])
	{
		
		AirBand air = new AirBand();
		if(!air.initSocket())
			return;
		
		System.out.println("Socket set up.");
		
		MidiRoutines mid = new MidiRoutines();
		System.out.println("MIDI Initalized.");
		
		int[] notes = {60,62,64,67,69};
		while(true)
		{
			byte in = air.recieveStrums();
			if(in == ERROR_BYTE)
			{
				System.out.println("Socket Crashed");
				return;
			} else if(in != NO_NOTE)
			{
				mid.noteQueue(in, notes[(int)(Math.random()*5)]);
			}
				mid.update();
		}
	}
	
	private boolean initSocket()
	{
		try
		{
			socket = new DatagramSocket(PORT);
			socket.setSoTimeout(10);
			buf = new byte[3];
			packet = new DatagramPacket(buf, buf.length);
		} catch (Exception e)
		{
			System.out.println("Could not open socket");
			return false;
		}
		return true;
	}
	
	private byte recieveStrums()
	{
		try
		{
			
			socket.receive(packet);

			// Handshake to prevent random traffic.
			if(buf[0] == 4 && buf[1] == 20)
			{
				return buf[2];
			}
			System.out.println("Bad Traffic!");
			return NO_NOTE;
		
		} catch (SocketTimeoutException e)
		{
			return NO_NOTE;
		}
		catch (Exception e)
		{
			System.out.println("Undefined socket error!");
		}
		try
		{
			socket.close();
		} catch( Exception e)
		{
			System.out.println("Could not close socket!");
		}	
		return ERROR_BYTE;
    }
 
}
