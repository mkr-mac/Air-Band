
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	public static final byte PIANO_BYTE = 3;
	public static final byte SYNTH_BYTE = 4;
	public static final byte BRASS_BYTE = 5;
	public static final byte FLUTE_BYTE = 6;
	
	private DatagramPacket packet;
	private byte buf[];
	
	public static ChordMap cm;
	public static Chord c;
	
	public static void main (String args[])
	{
		
		AirBand air = new AirBand();
		cm = new ChordMap();
		c = new Chord(0,new ArrayList<Integer>(Arrays.asList(0,3,7,12)));
		
		if(!air.initSocket())
			return;
		
		System.out.println("Socket set up.");
		
		MidiRoutines mid = new MidiRoutines();
		System.out.println("MIDI Initalized.");		
		
		ProcessBuilder pb = new ProcessBuilder("exec/cv.exe");
		Process p;  
		BufferedReader bri ;
		try
		{
			p = pb.start();  
		 bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Failed to start openCV, aborting");
			return;
		}
		System.out.println("openCV started.");	
		String line;
		
		while(true)
		{
			try{
				line = bri.readLine();
			} catch (Exception e)
			{
				System.out.println("Invalid openCV output");
				return;
			}
			
			if(line != null)
				System.out.println("OpenCv says:" + line);
		
			byte in = air.recieveStrums();
			if(in == ERROR_BYTE)
			{
				System.out.println("Socket Crashed");
				return;
			}
			if(in == BASS_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.BASS, 36 + c.getBass()); 
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
			if(in == PIANO_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.PIANO, 60 + c.getBass() + c.getFigure().get((int)(Math.random()*c.getFigure().size()))); 
			}
			if(in == SYNTH_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.SYNTH, 60 + c.getBass() + c.getFigure().get((int)(Math.random()*c.getFigure().size()))); 
			}
			if(in == BRASS_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.TRUMPET, 60 + c.getBass() + c.getFigure().get((int)(Math.random()*c.getFigure().size()))); 
			}
			if(in == FLUTE_BYTE)
			{
				mid.noteQueue(MidiRoutines.Instrument.FLUTE, 60 + c.getBass() + c.getFigure().get((int)(Math.random()*c.getFigure().size()))); 
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

				System.out.println("test");
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
