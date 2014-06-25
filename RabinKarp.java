package patternSearching;

/**
 * This is single pattern searching method of Rabin Karp for which it is
 * inferior to other contemporaries like KMP and Boyer Moore
 * 
 * @author ankit
 * 
 */
public class RabinKarp {
	
	//This is the number of possible characters in the string
	private int POSSIBLE_CHARACTERS = 256;
	
	void searchRabinKarp(String text, String pattern, int q){
		
		int m = pattern.length();
		int n = text.length();
		int i,j;
		int p = 0;
		int t = 0;
		int h = 1;
		// The value of h would be "pow(d, M-1)%q"
		for( i = 0; i < m - 1 ; i++)
			h = (h * POSSIBLE_CHARACTERS) % q;
		//Calculating the hash value of the pattern and the first slot of text
		for( i = 0; i < m ;i++){
			p = ((POSSIBLE_CHARACTERS * p) + pattern.charAt(i)) % q;
			t = ((POSSIBLE_CHARACTERS * t) + text.charAt(i)) % q;
		}
		for( i = 0; i < n; i++){
			
			if(p == t){
				for( j = 0 ; j < m; j++){
					if(text.charAt(i + j) != pattern.charAt(j))
						break;
				}
				if( j == m)
					System.out.println("Pattern exists at : "+ i);
			}
			// Calculate hash value by removing leading and adding trailing digit 
			if (i < (n - m)){
				
				t = (POSSIBLE_CHARACTERS * (t - text.charAt(i)*h) + text.charAt(i + m)) % q; 
			}
			if(t < 0)
				t += q;
		}
		
	}
	
	
	public static void main(String[] args) {
		new RabinKarp().searchRabinKarp("GEEKSFORGEEKS", "GEEK", 101);
	}
}
