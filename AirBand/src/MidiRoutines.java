
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class MidiRoutines {
	
	public int bassInstrument = 35;
	public int rhythmInstrument = 27;
	public int leadInstrument = 30;
	
	long time = 0;
	
	public int bassChannel = 12;
	public int rhythmChannel = 2;
	public int leadChannel = 3;
	public int percussionChannel = 9;

	int volume = 127; // between 0 and 127
	//song tempo
	double tempo = 100; 
	long oldTime = System.nanoTime();
	long newTime = System.nanoTime();
	long delta = 0;
	
	//for nicer calculations
	double tempoMod = tempo/120000000;
	
	int beats = 0;
	double oldBeats = 3;

	private Synthesizer n;
	private MidiChannel[] c;
	
	int[] queue = new int[100];
	int queueCount = 0;
	
	public MidiRoutines(){
		try{
			n = MidiSystem.getSynthesizer();
			c = n.getChannels();
			n.open();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String args[]){
		MidiRoutines m = new MidiRoutines();

		while (true){
			m.update();
		}
	}
	*/
	public void noteQueue(int id, int noteValue){
		queue[queueCount] = id;
		queueCount++;
		queue[queueCount] = noteValue;
		queueCount++;
	}
	
	private void play(int id, int noteValue){
		
		switch (id){
		
		case 3:
			c[percussionChannel].noteOn(noteValue, volume);
			break;
		
		case 0:
			c[bassChannel].noteOn(noteValue, volume);
			break;
		
		case 1:
			c[rhythmChannel].noteOn(noteValue, volume);
			break;
		
		case 2:
			c[leadChannel].noteOn(noteValue, volume);
			break;
		}
	}
	
	public void stop(int id){
		c[9].noteOff(id, volume);
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
			beats = (int) Math.floor(delta/(125/(2*tempoMod)));
			
			if(beats != oldBeats){
				while (queueCount > 0){
					play(queue[queueCount - 2],queue[queueCount - 1]);
					queueCount -= 2;
				}
				drums();
			}
			
			oldTime = newTime;
	}
	
	public void drums(){

		switch (beats){
		
		case 0:
			play(3, 42);
			play(3, 36);
			break;
		case 2:
			play(3, 42);
			break;
		case 4:
			play(3, 42);
			play(3, 38);
			break;
		case 6:
			play(3, 42);
			break;
		case 8:
			play(3, 42);
			play(3, 36);
			break;
		case 10:
			play(3, 42);
			break;
		case 12:
			play(3, 42);
			play(3, 38);
			break;
		case 14:
			play(3, 42);
			break;
		}
	}
}
