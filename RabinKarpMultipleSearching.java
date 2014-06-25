package patternSearching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is used for searching multiple patterns in a text string.
 * 
 * @author ankitsirmorya
 *
 */
public class RabinKarpMultipleSearching {
	
	//This is the number of possible characters in the string
	private int POSSIBLE_CHARACTERS = 256;
		
	void searchMultiplePattern(String text, List<String> patterns, int q, int m){
		
		int i, j;
		int h = 1;
		int p = 0;
		int t = 0;
		int n = text.length();
		HashMap<Integer, List<String>> rkpMap = new HashMap<Integer, List<String>>();
		List<String> rkpList;
		boolean computeOnce = true;
		//Calculating the value of "h"
		for( i = 0; i < m-1; i++)
			h = (h * POSSIBLE_CHARACTERS) % q;
		
		//Storing the hash value of the patterns in the map
		for(String pattern : patterns){
			p = 0;
			for( i = 0; i < m ; i++){
				if(computeOnce)
					t = ((POSSIBLE_CHARACTERS * t) + text.charAt(i)) % q;
				p = ((POSSIBLE_CHARACTERS * p) + pattern.charAt(i)) % q;
			}
			computeOnce = false;
			if(rkpMap.containsKey(p))
				 rkpMap.get(p).add(pattern);
			else{
				rkpList = new ArrayList<String>();
				rkpList.add(pattern);
				rkpMap.put(p, rkpList);
			}
		}
		
		
		for( i = 0; i < n ; i++){
			
			if(rkpMap.containsKey(t)){
				//Matching the text character by character if pattern matches
				for(String pattern : rkpMap.get(t)){
					for( j = 0; j < m; j++)
						if(text.charAt(i+j) != pattern.charAt(j))
							break;
					if(j == m)
						System.out.println("Pattern : "+ pattern+" is present at : "+i);
				}
			}
			
			// Calculate hash value by removing leading and adding trailing digit 
			if (i < (n - m)){
				
				t = (POSSIBLE_CHARACTERS * (t - text.charAt(i)*h) + text.charAt(i + m)) % q; 
			}
			//Modifying the value if the hash value of text comes to be negative
			if(t < 0)
				t += q;
			
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("GEEK");
		list.add("LOVE");
		new RabinKarpMultipleSearching().searchMultiplePattern("LOVEGEEKSFORGEEKS", list, 101, 4);
	}
}
