import java.util.ArrayList;
import java.util.Arrays;

public class Chord
{
	private int bass;
	private ArrayList<Integer> figure;
	
	public Chord(int bass, ArrayList<Integer> figure)
	{
		this.bass = bass;
		this.figure = figure;
	}
	
	public static String translate(int fig)
	{
		switch (fig)
		{
			case 1:
				return "C";
			case 2:
				return "C#/Db";
			case 3:
				return "D";
			case 4:
				return "D#/Eb";
			case 5:
				return "E";
			case 6:
				return "F";
			case 7:
				return "F#/Gb";
			case 8:
				return "G";
			case 9:
				return "G#/Ab";
			case 10:
				return "A";
			case 11:
				return "A#/Bb";
			case 12:
				return "B";
			default:
				return "Err";
		}
	}
	
	public int getBass()
	{
		return bass;
	}

	public ArrayList<Integer> getFigure()
	{
		return figure;
	}	
	
	public static Chord updateChord(int key, boolean mode, Chord oldChord)
	{
		return ChordMap.getChord(key,(oldChord.getBass()-key+12)%12+12*(mode?1:0),oldChord.getFigure());		
	}
}