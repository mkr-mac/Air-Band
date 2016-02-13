import java.util.ArrayList;
import java.util.Arrays;

public class ChordMap
{
	private static ArrayList<ArrayList<ArrayList<Integer>>> map;
	private static ArrayList<ArrayList<Integer>> domResPat;
	public ChordMap()
	{
		domResPat = new ArrayList<ArrayList<Integer>>();
		domResPat.add(new ArrayList<Integer>(Arrays.asList(0,4,7,12)));
		domResPat.add(new ArrayList<Integer>(Arrays.asList(0,4,7,10)));
		domResPat.add(new ArrayList<Integer>(Arrays.asList(0,3,7,12)));
		domResPat.add(new ArrayList<Integer>(Arrays.asList(0,3,7,10)));
		map = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		for(int i = 0; i < 24; i++)
		{
			map.add(new ArrayList<ArrayList<Integer>>());
		}
		
		//minor i
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//i
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,10)));//V7/iv
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(3,0,4,7,12)));//III
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,12)));//iv
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,12)));//IV
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,10)));//V7
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,12)));//V
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(8,0,4,7,12)));//VI
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(8,0,4,7,10)));//V7/II
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(9,0,3,7,10)));//vi#
		
		//c5th
		map.get(1).add(new ArrayList<Integer>(Arrays.asList(6,0,4,7,10)));//V7
		//minor II
		map.get(2).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,10)));//V7
		map.get(2).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,12)));//V
		map.get(2).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,12)));//iv
		map.get(2).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,10)));//i
		
		//minor III
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(8,0,4,7,12)));//vi
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(10,0,3,7,10)));//vii7
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(10,0,3,7,9)));//vii7-
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,12)));//iv
		
		//c5th
		map.get(4).add(new ArrayList<Integer>(Arrays.asList(9,0,3,7,10)));//V7
		//minor iv
		map.get(5).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,10)));//IV7
		map.get(5).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,12)));//V
		map.get(5).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,10)));//V7
		map.get(5).add(new ArrayList<Integer>(Arrays.asList(2,2,6,12,14)));//ii6
		map.get(5).add(new ArrayList<Integer>(Arrays.asList(7,0,4,6,12)));//V7
		
		//c5th
		map.get(6).add(new ArrayList<Integer>(Arrays.asList(11,0,4,7,10)));//V7
		
		//minor V
		map.get(7).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//i
		map.get(7).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,10)));//V7
		map.get(7).add(new ArrayList<Integer>(Arrays.asList(8,0,4,7,12)));//vi
		
		//minor vi
		map.get(8).add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,10)));//iii7
		map.get(8).add(new ArrayList<Integer>(Arrays.asList(3,4,7,12,16)));//ii6
		map.get(8).add(new ArrayList<Integer>(Arrays.asList(5,4,8,13,16)));//iv6
		map.get(8).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//iv6
		
		//c5th
		map.get(9).add(new ArrayList<Integer>(Arrays.asList(2,0,3,7,12)));//V7
		map.get(9).add(new ArrayList<Integer>(Arrays.asList(2,0,3,7,10)));//V7
		
		//minor vii
		map.get(10).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,12)));//V
		map.get(10).add(new ArrayList<Integer>(Arrays.asList(7,0,4,7,10)));//V7
		map.get(10).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,10)));//iv7
		map.get(10).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//i
		
		//c5th
		map.get(11).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,12)));//V7
		/*
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,12)));//I
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,10)));//V7/IV
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(2,0,3,7,12)));//iii
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,12)));//iv
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(3,0,4,7,12)));//IV
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,10)));//V7
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,12)));//V
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,12)));//vi
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,10)));//V7/ii
		*/
	}
	
	private static boolean isDominant(ArrayList<Integer> c)
	{
		return (c.equals(new ArrayList(Arrays.asList(0,4,7,10))) || c.equals(new ArrayList(Arrays.asList(0,3,7,10))) ||
				c.equals(new ArrayList(Arrays.asList(0,3,7,9))));
	}
	
	private static Chord resolveDominant(int root)
	{
		return (root>11) ? new Chord((root-7)%12,domResPat.get((int)Math.random()*4)):
		new Chord((root+5)%12,domResPat.get((int)Math.random()*4));
	}
	
	public static Chord getChord(int key, int root, ArrayList<Integer> pattern)
	{
		System.out.println(key + " " + root + " ");
		ArrayList<Integer> candidate = new ArrayList<Integer>(map.get(root).get((int)(Math.random()*map.get(root).size())));
		int newroot = candidate.remove(0);
		return isDominant(pattern)? resolveDominant(root): new Chord((key+newroot)%12,candidate);		
	}
}