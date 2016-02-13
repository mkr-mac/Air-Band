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
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//I
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
		map.get(2).add(new ArrayList<Integer>(Arrays.asList(7,0,4,6,12)));//V7
		
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
		map.get(7).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//i
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
		
		//Major I
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//I
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//I
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,10)));//V7/iv
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,12)));//iii
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(15,0,3,7,12)));//iv
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(15,0,4,7,12)));//IV
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,10)));//V7
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,12)));//V
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(20,0,4,7,12)));//VI
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(20,0,4,7,10)));//V7/II
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(23,0,3,6,8)));//vi#
		
		//c5th
		map.get(13).add(new ArrayList<Integer>(Arrays.asList(18,0,4,7,10)));//V7
		//Major ii
		map.get(14).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,10)));//V7
		map.get(14).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,12)));//V
		map.get(14).add(new ArrayList<Integer>(Arrays.asList(23,0,3,6,8)));//viio
		map.get(14).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,10)));//i
		map.get(14).add(new ArrayList<Integer>(Arrays.asList(19,0,4,6,12)));//V6-4
		
		//minor III
		map.get(15).add(new ArrayList<Integer>(Arrays.asList(20,0,4,7,12)));//vi
		map.get(15).add(new ArrayList<Integer>(Arrays.asList(22,0,4,7,10)));//vii7
		map.get(15).add(new ArrayList<Integer>(Arrays.asList(22,0,3,6,8)));//vii7-
		map.get(15).add(new ArrayList<Integer>(Arrays.asList(17,0,4,7,12)));//iv
		
		//c5th
		map.get(16).add(new ArrayList<Integer>(Arrays.asList(21,0,4,7,10)));//V7
		//Major iv
		map.get(17).add(new ArrayList<Integer>(Arrays.asList(17,0,4,7,10)));//IV7
		map.get(17).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,12)));//V
		map.get(17).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,10)));//V7
		map.get(17).add(new ArrayList<Integer>(Arrays.asList(14,2,6,12,14)));//ii6
		map.get(17).add(new ArrayList<Integer>(Arrays.asList(19,0,4,6,12)));//V6-4
		
		//c5th
		map.get(18).add(new ArrayList<Integer>(Arrays.asList(23,0,4,7,10)));//V7
		
		//major V
		map.get(19).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//i
		map.get(19).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//i
		map.get(19).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,10)));//V7
		map.get(19).add(new ArrayList<Integer>(Arrays.asList(20,0,3,7,12)));//vi
		
		//major vi
		map.get(20).add(new ArrayList<Integer>(Arrays.asList(15,0,4,7,10)));//iii7
		map.get(20).add(new ArrayList<Integer>(Arrays.asList(17,4,7,12,16)));//ii6
		map.get(20).add(new ArrayList<Integer>(Arrays.asList(20,4,8,13,16)));//iv6
		map.get(20).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//iv6
		
		//c5th
		map.get(21).add(new ArrayList<Integer>(Arrays.asList(14,0,4,7,12)));//V7
		map.get(21).add(new ArrayList<Integer>(Arrays.asList(14,0,4,7,10)));//V7
		
		//major vii
		map.get(22).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,12)));//V
		map.get(22).add(new ArrayList<Integer>(Arrays.asList(19,0,4,7,10)));//V7
		map.get(22).add(new ArrayList<Integer>(Arrays.asList(17,0,4,7,10)));//iv7
		map.get(22).add(new ArrayList<Integer>(Arrays.asList(12,0,4,7,12)));//i
		
		//c5th
		map.get(23).add(new ArrayList<Integer>(Arrays.asList(17,0,4,7,12)));//V7
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