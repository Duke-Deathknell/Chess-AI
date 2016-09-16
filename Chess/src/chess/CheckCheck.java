package chess;

/** Michael Alsbergas
 *
 * 
 */
public class CheckCheck {
    //Checks to see if a player is in check. Returns true if so.
    public boolean Check(Piece[][] board, boolean white){
        for (int x = 0 ; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                if (board[x][y].type != 'o' && board[x][y].white != white){
                    
                    if (board[x][y].type == 'b' || board[x][y].type == 'q'){
                        //Bishop & Queen
                        for(int z = 1 ; z+x < 8 && z+y < 8 ; z++){

                            if (board[x+z][y+z].type == 'k' && board[x+z][y+z].white == white){
                                return true;
                            }
                            if (board[x+z][y+z].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; z+x < 8 && y-z > -1 ; z++){

                            if (board[x+z][y-z].type == 'k' && board[x+z][y-z].white == white){
                                return true;
                            }
                            if (board[x+z][y-z].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; x-z > -1 && y-z > -1 ; z++){
                            
                            if (board[x-z][y-z].type == 'k' && board[x-z][y-z].white == white){
                                return true;
                            }
                            if (board[x-z][y-z].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; x-z > -1 && y+z < 8 ; z++){
                            
                            if (board[x-z][y+z].type == 'k' && board[x-z][y+z].white == white){
                                return true;
                            }
                            if (board[x-z][y+z].type != 'o'){ z = 8; }
                        }
                    }
                    
                    if (board[x][y].type == 'r' || board[x][y].type == 'q'){
                        //Rook & Queen
                        for(int z = 1 ; z+x < 8 ; z++){
                            
                            if (board[x+z][y].type == 'k' && board[x+z][y].white == white){
                                return true;
                            }
                            if (board[x+z][y].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; y-z > -1 ; z++){
                            
                            if (board[x][y-z].type == 'k' && board[x][y-z].white == white){
                                return true;
                            }
                            if (board[x][y-z].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; x-z > -1 ; z++){
                            
                            if (board[x-z][y].type == 'k' && board[x-z][y].white == white){
                                return true;
                            }
                            if (board[x-z][y].type != 'o'){ z = 8; }
                        }
                        for(int z = 1 ; y+z < 8 ; z++){
                            
                            if (board[x][y+z].type == 'k' && board[x][y+z].white == white){
                                return true;
                            }
                            if (board[x][y+z] != null){ z = 8; }
                        }
                    }
                    
                    if (board[x][y].type == 'n'){
                        //Knight
                        if(x+1 < 8 && y+2 < 8 && board[x+1][y+2].type == 'k' && board[x+1][y+2].white == white){
                            return true;  }
                        if(x+2 < 8 && y+1 < 8 && board[x+2][y+1].type == 'k' && board[x+2][y+1].white == white){
                            return true;  }
                        if(x-1 > -1 && y+2 < 8 && board[x-1][y+2].type == 'k' && board[x-1][y+2].white == white){
                            return true;  }
                        if(x+2 < 8 && y-1 > -1 && board[x+2][y-1].type == 'k' && board[x+2][y-1].white == white){
                            return true;  }
                        if(x-2 > -1 && y+1 < 8 && board[x-2][y+1].type == 'k' && board[x-2][y+1].white == white){
                            return true;  }
                        if(x-2 > -1 && y-1 > -1 && board[x-2][y-1].type == 'k' && board[x-2][y-1].white == white){
                            return true;  }
                        if(x+1 < 8 && y-2 > -1 && board[x+1][y-2].type == 'k' && board[x+1][y-2].white == white){
                            return true;  }
                        if(x-1 > -1 && y-2 > -1 && board[x-1][y-2].type == 'k' && board[x-1][y-2].white == white){
                            return true;  }
                    }
                    
                    else if (board[x][y].type == 'k'){
                        //King
                        if (x-1 > -1 && board[x-1][y].type == 'k' && board[x-1][y].white == white){
                            return true; }
                        if (x-1 > -1 && y-1 > -1 && board[x-1][y-1].type == 'k' && board[x-1][y-1].white == white){
                            return true; }
                        if (y-1 > -1 && board[x][y-1].type == 'k' && board[x][y-1].white == white){
                            return true; }
                        if (x+1 < 8 && y-1 > -1 && board[x+1][y-1].type == 'k' && board[x+1][y-1].white == white){
                            return true; }
                        if (x+1 < 8 && board[x+1][y].type == 'k' && board[x+1][y].white == white){
                            return true; }
                        if (x+1 < 8 && y+1 < 8 && board[x+1][y+1].type == 'k' && board[x+1][y+1].white == white){
                            return true; }
                        if (y+1 < 8 && board[x][y+1].type == 'k' && board[x][y+1].white == white){
                            return true; }
                        if (x-1 > -1 && y+1 < 8 && board[x-1][y+1].type == 'k' && board[x+1][y+1].white == white){
                            return true; }
                    } 
                    
                    else if (board[x][y].type == 'p'){
                        //Pawn
                        if (board[x][y].white){
                            if (x-1 > -1 && y-1 > -1 && board[x-1][y-1].type == 'k' && board[x-1][y-1].white == white){
                                return true; }
                            if (x-1 > -1 && y+1 < 8 && board[x-1][y+1].type == 'k' && board[x+1][y+1].white == white){
                                return true; }
                        }
                        else {
                            if (x+1 < 8 && y-1 > -1 && board[x+1][y-1].type == 'k' && board[x+1][y-1].white == white){
                                return true; }
                            if (x+1 < 8 && y+1 < 8 && board[x+1][y+1].type == 'k' && board[x+1][y+1].white == white){
                                return true; }
                        }
                    }
                }
            }
        }
        return false;
    }
    //Checks to see if a users input move is valid. Returns true if so.
    public boolean ValidMove(Piece[][] board, int x, int y, int a, int b){
        boolean valid = false;
        CheckCheck test = new CheckCheck();
        
        if (board[x][y].type == 'b' || board[x][y].type == 'q'){
            //Bishop & Queen
            for(int z = 1 ; z+x < 8 && z+y < 8 ; z++){

                if (x+z == a && y+z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x+z][y+z].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; z+x < 8 && y-z > -1 ; z++){

                if (x+z == a && y-z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x+z][y-z].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; x-z > -1 && y-z > -1 ; z++){

                if (x-z == a && y-z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x-z][y-z].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; x-z > -1 && y+z < 8 ; z++){

                if (x-z == a && y+z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x-z][y+z].type != 'o'){ z = 8; }
            }
        }

        if (board[x][y].type == 'r' || board[x][y].type == 'q'){
            //Rook & Queen
            for(int z = 1 ; z+x < 8 ; z++){

                if (x+z == a && y==b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x+z][y].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; y-z > -1 ; z++){

                if (x==a && y-z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x][y-z].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; x-z > -1 ; z++){

                if (x-z == a && y==b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x-z][y].type != 'o'){ z = 8; }
            }
            for(int z = 1 ; y+z < 8 ; z++){

                if (x==a && y+z == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
                if (board[x][y+z] != null){ z = 8; }
            }
        }

        if (board[x][y].type == 'n'){
            //Knight
            if(x+1 == a && y+2 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x+2 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x-1 == a && y+2 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x+2 == a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x-2 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x-2 == a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x+1 == a && y-2 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
            if(x-1 == a && y-2 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true;  }
        }

        else if (board[x][y].type == 'k'){
            //King
            if (x-1 == a && y==b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x-1 == a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x==a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x+1 == a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x+1 == a && y==b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x+1 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x==a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
            if (x-1 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                board[a][b] = board[x][y];
                board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
        } 

        else if (board[x][y].type == 'p'){
            //Pawn
            if (board[x][y].white){
                if (x-1 == a && y-1 == b && ((board[a][b].type == 'o' || board[a][b].white != board[x][y].white))){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
                if (x-1 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
                if (x-1 == a && y==b && board[a][b].type == 'o'){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;}
                if (x-2 == a && y==b && board[a][b].type == 'o'){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
            }
            else {
                if (x+1 == a && y-1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
                if (x+1 == a && y+1 == b && (board[a][b].type == 'o' || board[a][b].white != board[x][y].white)){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
                if (x-1 == a && board[a][b].type == 'o'){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true; }
                if (x-2 == a && board[a][b].type == 'o'){
                    board[a][b] = board[x][y];
                    board[x][y] = new Piece(false,'o',"  ");
                    valid = true;
                }
            }
        }
        
        if (valid){ return !test.Check(board, board[a][b].white); }
        else { return false; }
    }
}
