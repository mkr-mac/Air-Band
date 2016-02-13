import java.util.ArrayList;
public class Chord
{
	private int bass;
	private ArrayList<Integer> figure;
	public static string translate(int fig)
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


	public static Chord updateChord(int key, Chord oldChord, int pos, boolean classical)
	{
		return  
