
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
	
	//drum stuff
	boolean hit0 = true;
	boolean hit1 = true;
	boolean hit2 = true;
	boolean hit3 = true;
	
	double beats = 0;

	private Synthesizer n;
	private MidiChannel[] c;
	
	public MidiRoutines(){
		try{
			n = MidiSystem.getSynthesizer();
			c = n.getChannels();
			n.open();
			while (true){
				Update();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] arg)
	{
		MidiRoutines m = new MidiRoutines();
	}
	public void Play(int id, int noteValue){
		
		if (id == 3)
			c[percussionChannel].noteOn(noteValue, volume);
		
		else if (id == 0)
			c[bassChannel].noteOn(noteValue, volume);
		
		else if (id == 1)
			c[rhythmChannel].noteOn(noteValue, volume);
		
		else if (id == 2)
			c[leadChannel].noteOn(noteValue, volume);
	}
	
	public void Stop(int id){
		c[9].noteOff(id, volume);
	}
	
	public void Update(){
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
			Drums();
			oldTime = newTime;
	}
	
	public void Drums(){

		//Each beat has defined hits
		if((beats == 0)&&(hit0)){
			Play(3, 42);
			Play(3, 36);
			hit0 = false;
		}
		if((beats == 1)&&(hit1)){
			Play(3, 42);
			hit1 = false;
		}
		if((beats == 2)&&(hit2)){
			Play(3, 42);
			Play(3, 38);
			hit2 = false;
		}
		if((beats == 3)&&(hit3)){
			Play(3, 42);
			hit3 = false;
		}
	}
}
