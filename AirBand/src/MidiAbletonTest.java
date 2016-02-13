import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiAbletonTest {

	public static void main(String args[])
	{
		 MidiDevice device;
	     MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
	     
	     int j = 0;
	     for(MidiDevice.Info inf : infos)
	     {
	    	 j++;
	    	 System.out.println(inf.getName());
	    	 if(j == 6)
	    	 {
	    		 try
	    		 {
	    			 device = MidiSystem.getMidiDevice(inf);
	    			 device.open();
	    			 Receiver r = device.getReceiver();
	    			 
	    			 
	    			 for(int i = 0; i != 100; i++)
	    			 {
	    				 System.out.println("ayy, lmao!");
	    				 ShortMessage myMsg = new ShortMessage();
	    				 // Start playing the note Middle C (60), 
	    				 // moderately loud (velocity = 93).
	    				 myMsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);
	    				 r.send(myMsg, -1);
	    				 Thread.sleep(1000);
	    			 }
	    		 } catch (Exception e)
	    		 {
	    			 e.printStackTrace();
	    			 //get rekt lel
	    		 }
	    		 
	    	 }
	     }
	}
}
