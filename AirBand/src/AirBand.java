
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
	
    public static final byte PIANO_PLAY = 10;
    public static final byte GUITAR_STRUM = 20;
    public static final byte BASS_STRUM = 30;
    // a range.
    public static final byte SYNTH_LOW = 40;
    public static final byte SYNTH_HIGH= 47;

    public static final byte SAX_LOW = 50;
    public static final byte SAX_HIGH = 54;
    public static final byte SAX_OFF = 55;

    public static final byte VIOLIN_ON = 60;
    public static final byte VIOLIN_OFF = 61;

    public static final byte FLUTE_LOW = 70;
    public static final byte FLUTE_HIGH = 74;
    public static final byte FLUTE_OFF = 75;
	
	public static int pianoInversionCounter = 0;
	public static boolean pianoInversionUp = true;
	
	private DatagramPacket packet;
	private byte buf[];
	
	public static ChordMap cm;
	public static Chord c;
	
	public static void main (String args[])
	{
		
		AirBand air = new AirBand();
		Package p = new Package();
		p.frame.setVisible(true);
		
		cm = new ChordMap();
		c = new Chord(0,new ArrayList<Integer>(Arrays.asList(0,3,7,12)));
		
		if(!air.initSocket())
			return;
		
		System.out.println("Socket set up.");
		
		MidiRoutines mid = new MidiRoutines();
		System.out.println("MIDI Initalized.");		
		
		while(true)
		{		
			byte in = air.recieveStrums();
			
			//if(p.isAlive)
			{
				//p.soundCheckUpdate(noteToInst(in), true);
			}
			//else
			{
			if(in == ERROR_BYTE)
			{
				System.out.println("Socket Crashed");
				return;
			}
			if(in == BASS_STRUM)
			{
				mid.noteQueue(MidiRoutines.Instrument.BASS, 36 + c.getBass()); 
			}
			
			if(in == GUITAR_STRUM)
			{
				for(Integer interval : c.getFigure())
				{
					mid.noteQueue(MidiRoutines.Instrument.RHYTHM, 48 + c.getBass() + interval); 
				}
			}
			
			if(in >= SYNTH_LOW && in <= SYNTH_HIGH)
			{
				//get the steps above the lowest note.
				int position = in - SYNTH_LOW;
				//now, find out where the median is, which is randomized.
				int median = (int)(Math.random() * (c.getFigure().size() - 1));
				//To mix it up, I say position root = median.
				int note = median + position;
				
				//add 12 for each octave, and modulus.
				mid.noteQueue(MidiRoutines.Instrument.SYNTH, 48 + c.getBass() + 12*(note/c.getFigure().size()) + c.getFigure().get(note % c.getFigure().size())); 
			}
			
			//Invert upwards twice.
			if(in == PIANO_PLAY)
			{
				int note = 0;
				
				for(Integer interval : c.getFigure())
				{
					if(note < (pianoInversionCounter))
					{
						mid.noteQueue(MidiRoutines.Instrument.PIANO, 48 + c.getBass() + interval + 12);
					} else
					{
						mid.noteQueue(MidiRoutines.Instrument.PIANO, 48 + c.getBass() + interval);
					}
					note++;
				}
				if(pianoInversionUp)
				{
					pianoInversionCounter += 1;
					if(pianoInversionCounter == c.getFigure().size())
						pianoInversionUp = false;
				} else
				{
					pianoInversionCounter -= 1;
					if(pianoInversionCounter == 0)
						pianoInversionUp = true;
				}
			}
			

			if(in >= SAX_LOW && in <= SAX_HIGH)
			{
				int note = in - SAX_LOW;
				//Mic-based
				mid.noteQueue(MidiRoutines.Instrument.SAX,  48 + c.getBass() + (12)*(note /  c.getFigure().size()) + c.getFigure().get( note % c.getFigure().size())  ); 
			}
			if(in == SAX_OFF)
			{
				//sloppy
				for(int i = 40; i != 127;i++)
				{
					mid.stop(MidiRoutines.Instrument.SAX, i);
				}
			}
			if(in == VIOLIN_ON)
			{
				mid.noteQueue(MidiRoutines.Instrument.VIOLIN, 60 + c.getBass() + c.getFigure().get(2)); 
			}
			if(in == VIOLIN_OFF)
			{
				for(int i = 40; i != 127;i++)
				{
					mid.stop(MidiRoutines.Instrument.VIOLIN, i);
				}
			}
			if(in >= FLUTE_LOW && in <= FLUTE_HIGH)
			{
				int note = in - FLUTE_LOW;
				//Mic-based
				mid.noteQueue(MidiRoutines.Instrument.FLUTE,  72 + c.getBass() + (12)*(note /  c.getFigure().size()) + c.getFigure().get( note % c.getFigure().size())  ); 
			}
			if(in == FLUTE_OFF)
			{
				//sloppy
				for(int i = 40; i != 127;i++)
				{
					mid.stop(MidiRoutines.Instrument.FLUTE, i);
				}
			}

				
			mid.update();
			}
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
	
	
	private static int noteToInst(byte note)
	{
		if(note == PIANO_PLAY)
			return 1;
		if(note == GUITAR_STRUM)
			return 2;
		if(note == BASS_STRUM)
			return 3;
		//TODO
		return 0;
	}
}
