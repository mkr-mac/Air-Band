
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class PlayMidi {

	public static void main( String[] args ) {

		try {
			//Opening the synth
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			while (true){
				MidiRoutines.Update(channels);
			}
			

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

