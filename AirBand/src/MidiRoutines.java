
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class MidiRoutines {
	
	public enum instruments{
		Bass,
		Rhythm,
		Lead,
		Percussion
		}
	
	public int bassInstrument = 35;
	public int rhythmInstrument = 27;
	public int leadInstrument = 30;
	
	static long time = 0;
	
	public static int bassChannel = 12;
	public static int rhythmChannel = 2;
	public static int leadChannel = 3;
	public static int percussionChannel = 9;

	static int volume = 127; // between 0 and 127
	//song tempo
	static double tempo = 100; 
	static long oldTime = System.nanoTime();
	static long newTime = System.nanoTime();
	static long delta = 0;
	
	//for nicer calculations
	static double tempoMod = tempo/120000000;
	
	//drum stuff
	static boolean hit0 = true;
	static boolean hit1 = true;
	static boolean hit2 = true;
	static boolean hit3 = true;
	
	static double beats = 0;
	
	public MidiRoutines(){
		
	}
	
	public static void Play(MidiChannel[] channels, int id, int noteValue){
		
		if (id == 3)
			channels[percussionChannel].noteOn(noteValue, volume);
		
		else if (id == 0)
			channels[bassChannel].noteOn(noteValue, volume);
		
		else if (id == 1)
			channels[rhythmChannel].noteOn(noteValue, volume);
		
		else if (id == 2)
			channels[leadChannel].noteOn(noteValue, volume);
	}
	
	public void Stop(MidiChannel[] channels, int id){
		channels[9].noteOff(id, volume);
	}
	
	public static void Update(MidiChannel[] channels){
			//getting the time delta
			newTime = System.nanoTime();
			delta += newTime - oldTime;

			//keep the delta small for calculation reasons
			while (delta >= 1000/tempoMod){
				delta -= 1000/tempoMod;
				hit0=hit1=hit2=hit3=true;
			}

			beats = Math.floor(delta/(250/tempoMod));
			
			//Play(0, 0);
			Drums(channels);
			oldTime = newTime;
	}
	
	public static void Drums(MidiChannel[] channels){

		//Each beat has defined hits
		if((beats == 0)&&(hit0)){
			Play(channels, 3, 42);
			Play(channels, 3, 36);
			hit0 = false;
		}
		if((beats == 1)&&(hit1)){
			Play(channels, 3, 42);
			hit1 = false;
		}
		if((beats == 2)&&(hit2)){
			Play(channels, 3, 42);
			Play(channels, 3, 38);
			hit2 = false;
		}
		if((beats == 3)&&(hit3)){
			Play(channels, 3, 42);
			hit3 = false;
		}
	}
}
