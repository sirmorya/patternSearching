package patternSearching;

/**
 * This is the best pattern matching algorithm. It has an average sub-linear time complexity.
 * In average case this algo takes about O(n/m) time complexity
 * However, in worst case when most of the pattern matches the text the complexity willl be of order O(mn)
 * eg. 
 * m = pattern.length(); 
 * n = text.length();
 * 
 * @author ankitsirmorya
 *
 */
public class BoyerMooreHoorspool {

	private int POSSIBLE_CHARS = 256;
	
	int table[] = new int[POSSIBLE_CHARS];
	
	/**
	 * Preprocessing the possible characters; i.e. 256. 
	 * @param pattern
	 * @param m
	 */
	void initializePreProcessedCharArray(String pattern, int m){
		
		for(int i = 0; i < POSSIBLE_CHARS; i++){
			table[i] = m;
		}
		
		for( int j = 0; j < m-1; j++)
			table[pattern.charAt(j)] = m - j -1;
	}
	
	/**
	 * Search function for Boyer-Moore Algo
	 * @param text
	 * @param pattern
	 */
	void search(String text, String pattern){
		int n = text.length();
		int m = pattern.length(); 
		initializePreProcessedCharArray(pattern, m);
		int i = 0,k;
		while( i <= n - m  ){//Loop runs until the space is left for atleast one pattern
			
			if(text.charAt(i + m -1) != pattern.charAt(m-1))
				i += table[text.charAt(i + m -1)];//Shifting teh cursor based on the preprocessed value
			else{
				for( k = m-1; k >=0; k--)
					if(text.charAt(i+k) != pattern.charAt(k))
						break;
				if( k == -1){
					System.out.println("Pattern is available at : "+ i);
					i += m;//Shifting the cursor in case of complete match
				}else{
					i += table[text.charAt(i + m -1)];//Shifting teh cursor based on the preprocessed value
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		//new BoyerMooreHoorspool().search("GEEKSFORGEEKS", "GEEKS");
		
		new BoyerMooreHoorspool().search("LOVEFORLOVE", "LOVE");
	}
}
