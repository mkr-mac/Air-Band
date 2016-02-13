
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiDevice;

public class MidiRoutines {
	
	enum Instrument { BASS, RHYTHM, LEAD, PERCUSSION};
	
	private int bassChannel = 1;
	private int rhythmChannel = 2;
	private int leadChannel = 3;
	private int percussionChannel = 9;

	private int volume = 127; // between 0 and 127
	
	//song tempo
	private double tempo = 100; 
	private long oldTime = System.nanoTime();
	private long newTime = System.nanoTime();
	private long delta = 0;
	
	//for nicer calculations
	private double tempoMod = tempo/120000000;
	
	private int beats = 0;
	private double oldBeats = 3;

	private MidiDevice device;
	private Receiver reciever;
	
	private int[] noteQueue = new int[100];
	private Instrument[] instrumentQueue = new Instrument[100];
	private int queueCount = 0;
	
	public MidiRoutines()
	{
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for(MidiDevice.Info inf : infos)
		{
			if(inf.getName().equals("NeutralPort"))
			{
				try
				{
					device = MidiSystem.getMidiDevice(inf);
		    		device.open();
		    		reciever = device.getReceiver();
				} catch (Exception e)
				{
					device.close();
					continue;
				}
		     }
		}
	}
	
	public static void main(String args[]){
		MidiRoutines m = new MidiRoutines();

		while (true){
			m.update();
		}
	}
	public void noteQueue(Instrument i, int noteValue){
		noteQueue[queueCount] = noteValue;
		instrumentQueue[queueCount] = i;
		queueCount++;
	}
	
	private void play(Instrument i, int noteValue){
		
		int channel = 0;
		switch (i){
		case BASS:
			channel = bassChannel;
			break;
		case RHYTHM:
			channel = rhythmChannel;
			break;
		case LEAD:
			channel = leadChannel;
			break;
		case PERCUSSION:
			channel = percussionChannel;
			break;
		}
	
		try {
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, channel, noteValue, volume);
			reciever.send(myMsg, -1);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	

	public void update(){
			//getting the time delta
			newTime = System.nanoTime();
			delta += newTime - oldTime;

			//keep the delta small for calculation reasons
			while (delta >= 1000/tempoMod){
				delta -= 1000/tempoMod;
			}
			oldBeats = beats;
			beats = (int) Math.floor(delta/(125/(tempoMod)));
			
			if(beats != oldBeats){
				while (queueCount > 0){
					play(instrumentQueue[queueCount - 1],noteQueue[queueCount - 1]);
					queueCount--;
				}
				drums();
			}
			
			oldTime = newTime;
	}
	
	private void drums(){

		switch (beats){
		
		case 0:
			play(Instrument.PERCUSSION, 42);
			play(Instrument.PERCUSSION, 36);
			break;
		case 2:
			play(Instrument.PERCUSSION, 42);
			break;
		case 4:
			play(Instrument.PERCUSSION, 42);
			play(Instrument.PERCUSSION, 38);
			break;
		case 6:
			play(Instrument.PERCUSSION, 42);
			break;
		case 8:
			play(Instrument.PERCUSSION, 42);
			play(Instrument.PERCUSSION, 36);
			break;
		case 10:
			play(Instrument.PERCUSSION, 42);
			break;
		case 12:
			play(Instrument.PERCUSSION, 42);
			play(Instrument.PERCUSSION, 38);
			break;
		case 14:
			play(Instrument.PERCUSSION, 42);
			break;
		}
	}
}
