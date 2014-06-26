package patternSearching;

public class FiniteAutomata {
	
	
	private String PATTERN = "AABA";
	private int POSSIBLE_CHARS = 256;
	
	//Table to store the finite automata
	private int table[][] = new int[PATTERN.length()+1][POSSIBLE_CHARS];
	
	/**
	 * This method returns the next supposed character.
	 * 
	 * @param state
	 * @param pos
	 * @return
	 */
	int getNextChar(int state, int pos){
		int j = 0, i;
		if(state < PATTERN.length() && PATTERN.charAt(state) == (char)pos)
			return state + 1;
		else{
			for( i = state; i > 0; i--){
				if(PATTERN.charAt(i - 1) == (char)pos){
					for(j = 0; j < i - 1; j++)
						if(PATTERN.charAt(state - i + 1 + j) != PATTERN.charAt(j))
							break;
					if( i - 1 == j)
						return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * This method is used to pre process the finite automata.
	 */
	void preProccessStateAutomata(String text){
		int m = PATTERN.length();
		for(int i = 0; i <= m; i++){
			for( int j = 0; j < POSSIBLE_CHARS; j++){
				table[i][j] = getNextChar(i, j);
			}
		}
		
		int state = 0;
		for( int i = 0; i < text.length(); i++){
			state = table[state][text.charAt(i)];
			if(state == m){
				System.out.println("Pattern is found at "+ (i - m +1));
			}
		}
	}
	
	
	public static void main(String[] args) {
		new FiniteAutomata().preProccessStateAutomata("AABAACAADAABAAABAA");
	}
}
