package patternSearching;

public class KMP {

	int[] computeOverlap(String pattern){
		int m = pattern.length();
		int[] overlap = new int[m];
		char c;
		int v;
		for(int i = 0; i < m ; i++)
			overlap[i] = -1;
		for(int k = 0; k < m-1; k++){
			c = pattern.charAt(k+1);
			v = overlap[k];
			while( v != -1 && pattern.charAt(v+1) != c) v = overlap[v];
			if(k!=0 &&  pattern.charAt(v + 1) == c)
				overlap[k+1] = v + 1;
			else
				overlap[k+1] = -1;
				
		}
		for(int i = 0; i < m ; i++)
			overlap[i] += 1;
		return overlap;
	}
	
	void searchPattern(String text, String pattern){
		int n = text.length();
		int m = pattern.length();
		int overlap[] = computeOverlap(pattern);
		int i = 0, j = 0, k = 0;
		while(n-k >= m){
			
			while( j < m && text.charAt(i) == pattern.charAt(j)) {
				i++; j++;
			}
			if(j > (m-1)) System.out.println(k);
			if(j > 0 && overlap[j-1] > 0)
				k = i - overlap[j-1];// new start of comparison in T
			else{
				if( i == k ) i++;
				k = i;
			}
			if( j > 0) j = overlap[j-1] + 1; 
		}
		
	}
	
	
	public static void main(String[] args) {
		String text = "ATCGCACATTATACATTATTATACAT";
		String pattern = "ATTATACA";
		new KMP().searchPattern(text, pattern);
	}
	
	
}
