package chess;

/** Michael Alsbergas, 5104112
 *
 * This creates the nodes used for the game tree.
 */
public class TreeN {
    Piece[][] state; 
    TreeN [] branch; 
    
    public TreeN (Piece[][] s){
        state = new Piece[8][8]; 
        
        for(int x = 0 ; x < 8 ; x++){
            for(int y = 0 ; y < 8 ; y++){
                if (s[x][y] != null){
                    state[x][y] = s[x][y];
                }
            }
        }
    }
    //This grow class takes the "moves" array of boards and makes them states for branch nodes.
    public void Grow(Piece[][][] b, int len){
        branch = new TreeN[len];
        
        for(int x = 0 ; x < len ; x++){
            branch[x] = new TreeN(b[x]);
        }
    }
    
}
