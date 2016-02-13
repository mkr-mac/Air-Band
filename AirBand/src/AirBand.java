
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;



public class AirBand {
	
	public static final int PORT = 45320; 
	public DatagramSocket socket = null;
	
	// This is returned from the socket if something bad happened and it crashed.
	public static final byte ERROR_BYTE = -1;
	// This is returned if there was no note.
	public static final byte NO_NOTE = -2;
	public static final byte BASS_BYTE = 0;
	public static final byte GUITAR_BYTE = 1;
	public static final byte LEAD_BYTE = 2;
	
	private DatagramPacket packet;
	private byte buf[];
	
	public static void main (String args[])
	{
		
		AirBand air = new AirBand();
		ChordMap cm = new ChordMap();
		Chord c = new Chord(0,new ArrayList<Integer>(Arrays.asList(0,3,7,10)));
		
		if(!air.initSocket())
			return;
		
		System.out.println("Socket set up.");
		
		MidiRoutines mid = new MidiRoutines();
		System.out.println("MIDI Initalized.");		
		
		while(true)
		{
			byte in = air.recieveStrums();
			if(in == ERROR_BYTE)
			{
				System.out.println("Socket Crashed");
				return;
			}
			if(in == BASS_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.BASS, 24 + c.getBass()); 
			}
			if(in == GUITAR_BYTE)
			{
				for(Integer interval : c.getFigure())
				{
					mid.noteQueue(MidiRoutines.Instrument.RHYTHM, 48 + c.getBass()+ interval); 
				}
			}
			if(in == LEAD_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.LEAD, 60 + c.getBass() + c.getFigure().get((int)(Math.random()*c.getFigure().size()))); 
			}

				//c = Chord.updateChord(0,false,c);
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
