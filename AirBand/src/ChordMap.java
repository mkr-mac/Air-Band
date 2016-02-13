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
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));//i
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,10)));//V7/iv
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(2,0,4,7,12)));//III
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,12)));//iv
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(3,0,4,7,12)));//IV
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,10)));//V7
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,12)));//V
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,12)));//VI
		map.get(0).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,10)));//V7/II
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,12)));
		map.get(3).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,10)));
		map.get(4).add(new ArrayList<Integer>(Arrays.asList(0,0,3,7,12)));
		
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,12)));//I
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(0,0,4,7,10)));//V7/IV
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(2,0,3,7,12)));//iii
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,12)));//iv
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(3,0,4,7,12)));//IV
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,10)));//V7
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(4,0,4,7,12)));//V
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(5,0,3,7,12)));//vi
		map.get(12).add(new ArrayList<Integer>(Arrays.asList(5,0,4,7,10)));//V7/ii
	}
	
	private static boolean isDominant(ArrayList<Integer> c)
	{
		return (c.equals(new ArrayList(Arrays.asList(0,4,7,10))) || c.equals(new ArrayList(Arrays.asList(0,3,7,10))));
	}
	
	private static Chord resolveDominant(int root)
	{
		return (root>11) ? new Chord((root-7)%12,domResPat.get((int)Math.random()*4)):
		new Chord((root-4)%12,domResPat.get((int)Math.random()*4));
	}
	
	public static Chord getChord(int key, int root, ArrayList<Integer> pattern)
	{
		System.out.println(key + " " + root + " ");
		ArrayList<Integer> candidate = new ArrayList<Integer>(map.get(root).get((int)(Math.random()*map.get(root).size())));
		int newroot = candidate.remove(0);
		return isDominant(pattern)? resolveDominant(root): new Chord((key+newroot)%12,candidate);		
	}
}