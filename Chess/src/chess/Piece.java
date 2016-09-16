package chess;

/**Michael Alsbergas, 5104112
 *
 * The class that creates the Piece data type. Boards are 2D arrays of pieces.
 */
public class Piece {
    boolean white;
    char type;
    String name;
    
    public Piece (boolean w, char t, String n){
        white = w;
        type = t;
        name = n; 
    }
}
