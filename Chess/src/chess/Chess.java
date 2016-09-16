package chess;

/**Michael Alsbergas, 5104112
 *
 * This is the main class for a chess playing AI using a min-max tree with 
 * alpha beta pruning.
 */

import java.util.Scanner;

public class Chess {
    Piece[][] bigBoard;
    Piece[][][] moves;
    boolean cpuWhite;
    boolean stopz;
    int posMoves;
    int alpha;
    int beta;
    int maxPly;    

    //This class is where the user interacts with the AI
    public Chess(){
        Scanner console = new Scanner(System.in);
        bigBoard = new Piece[8][8];
        moves = new Piece[200][8][8];
        
        System.out.print("Are you b or w player?");
        if (console.next().matches("b")){
            cpuWhite = true;
        }
        else {
            cpuWhite = false;
        }
        
        System.out.print("Enter max ply for difficulty: ");
        maxPly = console.nextInt();
        
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                bigBoard[i][j] = new Piece(false,'o',"  ");
            }
        }
        
        System.out.print("Test board? y or n: ");
        if (console.next().matches("y")){
            testBoard();
        }
        else {
            //Initial Board
            bigBoard[0][0].name = "r1";
            bigBoard[0][0].type = 'r';
            bigBoard[0][0].white = false;
                bigBoard[0][1].name = "n1";
                bigBoard[0][1].type = 'n';
                bigBoard[0][1].white = false;
            bigBoard[0][2].name = "b1";
            bigBoard[0][2].type = 'b';
            bigBoard[0][2].white = false;
                bigBoard[0][3].name = "qb";
                bigBoard[0][3].type = 'q';
                bigBoard[0][3].white = false;
            bigBoard[0][4].name = "kb";
            bigBoard[0][4].type = 'k';
            bigBoard[0][4].white = false;
                bigBoard[0][5].name = "b2";
                bigBoard[0][5].type = 'b';
                bigBoard[0][5].white = false;
            bigBoard[0][6].name = "n2";
            bigBoard[0][6].type = 'n';
            bigBoard[0][6].white = false;
                bigBoard[0][7].name = "r2";
                bigBoard[0][7].type = 'r';
                bigBoard[0][7].white = false;
            bigBoard[1][0].name = "z1";
            bigBoard[1][0].type = 'p';
            bigBoard[1][0].white = false;
                bigBoard[1][1].name = "z2";
                bigBoard[1][1].type = 'p';
                bigBoard[1][1].white = false;
            bigBoard[1][2].name = "z3";
            bigBoard[1][2].type = 'p';
            bigBoard[1][2].white = false;
                bigBoard[1][3].name = "z4";
                bigBoard[1][3].type = 'p';
                bigBoard[1][3].white = false;
            bigBoard[1][4].name = "z5";
            bigBoard[1][4].type = 'p';
            bigBoard[1][4].white = false;
                bigBoard[1][5].name = "z6";
                bigBoard[1][5].type = 'p';
                bigBoard[1][5].white = false;
            bigBoard[1][6].name = "z7";
            bigBoard[1][6].type = 'p';
            bigBoard[1][6].white = false;
                bigBoard[1][7].name = "z8";
                bigBoard[1][7].type = 'p';
                bigBoard[1][7].white = false;

            bigBoard[7][0].name = "R1";
            bigBoard[7][0].type = 'r';
            bigBoard[7][0].white = true;
                bigBoard[7][1].name = "N1";
                bigBoard[7][1].type = 'n';
                bigBoard[7][1].white = true;
            bigBoard[7][2].name = "B1";
            bigBoard[7][2].type = 'b';
            bigBoard[7][2].white = true;
                bigBoard[7][3].name = "Qw";
                bigBoard[7][3].type = 'q';
                bigBoard[7][3].white = true;
            bigBoard[7][4].name = "Kw";
            bigBoard[7][4].type = 'k';
            bigBoard[7][4].white = true;
                bigBoard[7][5].name = "B2";
                bigBoard[7][5].type = 'b';
                bigBoard[7][5].white = true;
            bigBoard[7][6].name = "N2";
            bigBoard[7][6].type = 'n';
            bigBoard[7][6].white = true;
                bigBoard[7][7].name = "R2";
                bigBoard[7][7].type = 'r';
                bigBoard[7][7].white = true;
            bigBoard[6][0].name = "Z1";
            bigBoard[6][0].type = 'p';
            bigBoard[6][0].white = true;
                bigBoard[6][1].name = "Z2";
                bigBoard[6][1].type = 'p';
                bigBoard[6][1].white = true;
            bigBoard[6][2].name = "Z3";
            bigBoard[6][2].type = 'p';
            bigBoard[6][2].white = true;
                bigBoard[6][3].name = "Z4";
                bigBoard[6][3].type = 'p';
                bigBoard[6][3].white = true;
            bigBoard[6][4].name = "Z5";
            bigBoard[6][4].type = 'p';
            bigBoard[6][4].white = true;
                bigBoard[6][5].name = "Z6";
                bigBoard[6][5].type = 'p';
                bigBoard[6][5].white = true;
            bigBoard[6][6].name = "Z7";
            bigBoard[6][6].type = 'p';
            bigBoard[6][6].white = true;
                bigBoard[6][7].name = "Z8";
                bigBoard[6][7].type = 'p';
                bigBoard[6][7].white = true;
        }
        
        if (cpuWhite){
            alpha = -999;
            beta = 999;
            TreeN leaf = new TreeN(bigBoard);
            bigBoard = minMax(leaf, true, cpuWhite, 1);
            Print(bigBoard);
        }
        
        while (true){
            CheckCheck test = new CheckCheck();
            int x;
            int y; 
            int a;
            int b;
            
            System.out.print("Enter x co-ordinate for piece to move: ");
            x = console.nextInt();
            System.out.print("Enter y co-ordinate for piece to move: ");
            y = console.nextInt();
            
            if (x>-1 && x<8 && y>-1 && y<8 && bigBoard[x][y].type != 'o' && bigBoard[x][y].white != cpuWhite){
                System.out.print("Enter x co-ordinate to move piece to: ");
                a = console.nextInt();
                System.out.print("Enter y co-ordinate to move piece to: ");
                b = console.nextInt();
                
                Piece[][] tempBoard = new Piece[8][8];
                for (int i = 0 ; i < 8 ; i++){
                    for (int j = 0 ; j < 8 ; j++){
                        tempBoard[i][j] = bigBoard[i][j];
                    }
                }
                
                if (test.ValidMove(tempBoard, x, y, a, b)){
                    bigBoard[a][b] = bigBoard[x][y];
                    bigBoard[x][y] = new Piece(false,'o',"  ");
                    StaleCheck();
                    
                    System.out.println("My Move.");
                    alpha = -999;
                    beta = 999;
                    TreeN leaf = new TreeN(bigBoard);
                    bigBoard = minMax(leaf, true, cpuWhite, 1);
                    Print(bigBoard);
                    
                    MateCheck(bigBoard, !cpuWhite);
                    StaleCheck();
                    System.out.println("Your move.");
                }
                else { System.out.println("Invalid move"); }
            }
            else { System.out.println("Invalid co-ordinates"); }
        }
        
    }
    
    //A recusive function that implements a min-max game tree and returns the best board 
    private Piece[][] minMax(TreeN root, boolean max, boolean cpuPlayer, int ply){
        moves = new Piece[200][8][8];
        posMoves = 0;
        stopz = false;
        
        for (int x = 0 ; x < 200 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                for (int z = 0 ; z < 8 ; z++){
                    moves[x][y][z] = new Piece(false, 'o', "  ");
                }
            }
        }
        
        if (ply == maxPly){
            int temp = Evaluate(root.state);
            if (temp > alpha){ alpha = temp; }
            if (temp < beta){ beta = temp; }
            return root.state; 
        }
        
        for (int x = 0; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                if (root.state[x][y].white == cpuPlayer){
                    if (root.state[x][y].type == 'k' && !stopz){
                        //King
                        moveKing(root.state, x, y, ply, max);
                    } else if (root.state[x][y].type == 'q' && !stopz){
                        //Queen
                        moveBishop(root.state, x, y, ply, max);
                        if (!stopz){ moveRook(root.state, x, y, ply, max); }
                    } else if (root.state[x][y].type == 'b' && !stopz){
                        //Bishop
                        moveBishop(root.state, x, y, ply, max);
                    } else if (root.state[x][y].type == 'n' && !stopz){
                        //Knight
                        moveKnight(root.state, x, y, ply, max);
                    } else if (root.state[x][y].type == 'r' && !stopz){
                        //Rook
                        moveRook(root.state, x, y, ply, max);
                    } else if (root.state[x][y].type == 'p' && !stopz){
                        //Pawn
                        movePawn(root.state, x, y, ply, max);
                    } 
                }
            }
        }
        
        if (posMoves > 0){
            root.Grow(moves, posMoves);
        }
        else {
            if (ply == 1){ Loss(); }
            
            //Return a loss condition board state to represent Check-mate.
            Piece[][] badBoard = new Piece[8][8];
            for(int i = 0 ; i < 8 ; i++){
                for(int j = 0 ; j < 8 ; j++){
                    badBoard[i][j] = new Piece(false,'o',"  ");
                }
            }
            badBoard[0][0].name = "X";
            badBoard[0][0].type = 'k';
            badBoard[0][0].white = !cpuPlayer;
            return badBoard;
        }
        
        if (max){
            int bestVal = -999;
            int bestBoardi = 0;
            Piece[][] bestBoard = null;
            for (int x = 0 ; x < root.branch.length ; x++){
                bestBoard = minMax(root.branch[x], !max, !cpuPlayer, ply+1);
                if (Evaluate(bestBoard) > bestVal){
                    bestVal = Evaluate(bestBoard);
                    bestBoardi = x;
                }
            }
            if (ply == 1){ return root.branch[bestBoardi].state; }
            else { return bestBoard; }
        }
        else {
            int worstVal = 999;
            int worstBoardi = 0;
            Piece[][] worstBoard = null;
            for (int x = 0 ; x < root.branch.length ; x++){
                worstBoard = minMax(root.branch[x], !max, !cpuPlayer, ply+1);
                if (Evaluate(worstBoard) > worstVal){
                    worstVal = Evaluate(worstBoard);
                    worstBoardi = x;
                }
            }
            if (ply == 1){ return root.branch[worstBoardi].state; }
            else { return worstBoard; }
        }
        
    }
    
    //The evaluation function is the value of the white pieces minus the value of the black pieces
    private int Evaluate(Piece[][] board){
        int sum = 0;
        
        for (int x = 0 ; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                if (board[x][y].type == 'k'){
                    if (board[x][y].white == cpuWhite){
                        //Worth more than all other pieces combined.
                        sum = sum + 104;
                    } else {sum = sum - 104;}
                } else if (board[x][y].type == 'q'){
                    if (board[x][y].white == cpuWhite){
                        sum = sum + 9;
                    } else {sum = sum - 9;}
                } else if (board[x][y].type == 'r'){
                    if (board[x][y].white == cpuWhite){
                        sum = sum + 5;
                    } else {sum = sum - 5;}
                } else if (board[x][y].type == 'b' || board[x][y].type == 'n'){
                    if (board[x][y].white == cpuWhite){
                        sum = sum + 3;
                    } else {sum = sum - 3;}
                } else if (board[x][y].type == 'p'){
                    if (board[x][y].white == cpuWhite){
                        sum = sum + 1;
                    } else {sum = sum - 1;}
                }
            }
        }
        
        return sum;
    }
    
    //Ends the game with a loss output
    private void Loss(){
        Print(bigBoard);
        System.out.println("Congradulations! You Win!");
        System.exit(0);
    }
    //Ends the game with a winning output
    private void Win() {
        Print(bigBoard);
        System.out.println("Sorry, you lose. I WIN!");
        System.exit(0);
    }
    
    /*Here are the functions that determine all possible moves for each piece
    *The alpha/beta pruning is implemented here.
    * All potential moves found are put into the array "moves". If a move is found that
    * signals pruning to start, no more moves are added to the array after that move.
    */
    private void moveKing(Piece[][] board, int x, int y, int ply, boolean max){
        Piece temp;
        CheckCheck test = new CheckCheck(); 
        
        if (x-1 > -1 && (board[x-1][y].type == 'o' || board[x-1][y].type != 'o' && board[x-1][y].white != board[x][y].white)){
            temp = board[x-1][y];
            board[x-1][y] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-1][y].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-1][y]; 
            board[x-1][y] = temp;
            if (stopz){ return; }
        }
        if (x-1 > -1 && y-1 > -1 && (board[x-1][y-1].type == 'o' || board[x-1][y-1].type != 'o' && board[x-1][y-1].white != board[x][y].white)){
            temp = board[x-1][y-1];
            board[x-1][y-1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-1][y-1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-1][y-1]; 
            board[x-1][y-1] = temp;
            if (stopz){ return; }
        }
        if (y-1 > -1 && (board[x][y-1].type == 'o' || board[x][y-1].type != 'o' && board[x][y-1].white != board[x][y].white)){
            temp = board[x][y-1];
            board[x][y-1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x][y-1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x][y-1]; 
            board[x][y-1] = temp;
            if (stopz){ return; }
        }
        if (x+1 < 8 && y-1 > -1 && (board[x+1][y-1].type == 'o' || board[x+1][y-1].type != 'o' && board[x+1][y-1].white != board[x][y].white)){
            temp = board[x+1][y-1];
            board[x+1][y-1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+1][y-1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+1][y-1]; 
            board[x+1][y-1] = temp;
            if (stopz){ return; }
        }
        if (x+1 < 8 && (board[x+1][y].type == 'o' || board[x+1][y].type != 'o' && board[x+1][y].white != board[x][y].white)){
            temp = board[x+1][y];
            board[x+1][y] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+1][y].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+1][y]; 
            board[x+1][y] = temp;
            if (stopz){ return; }
        }
        if (x+1 < 8 && y+1 < 8 && (board[x+1][y+1].type == 'o' || board[x+1][y+1].type != 'o' && board[x+1][y+1].white != board[x][y].white)){
            temp = board[x+1][y+1];
            board[x+1][y+1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+1][y+1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+1][y+1]; 
            board[x+1][y+1] = temp;
            if (stopz){ return; }
        }
        if (y+1 < 8 && (board[x][y+1].type == 'o' || board[x][y+1].type != 'o' && board[x][y+1].white != board[x][y].white)){
            temp = board[x][y+1];
            board[x][y+1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x][y+1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x][y+1]; 
            board[x][y+1] = temp;
            if (stopz){ return; }
        }
        if (x-1 > -1 && y+1 < 8 && (board[x-1][y+1].type == 'o' || board[x-1][y+1].type != 'o' && board[x-1][y+1].white != board[x][y].white)){
            temp = board[x-1][y+1];
            board[x-1][y+1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-1][y+1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-1][y+1]; 
            board[x-1][y+1] = temp;
             
        }
    }
    
    private void moveKnight(Piece[][] board, int x, int y, int ply, boolean max){
        Piece temp;
        CheckCheck test = new CheckCheck(); 
        
        if (x-1 > -1 && y-2 > -1 && (board[x-1][y-2].type == 'o' || board[x-1][y-2].type != 'o' && board[x-1][y-2].white != board[x][y].white)){
            temp = board[x-1][y-2];
            board[x-1][y-2] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-1][y-2].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-1][y-2]; 
            board[x-1][y-2] = temp;
            if (stopz){ return; }
        }
        if (x-2 > -1 && y-1 > -1 && (board[x-2][y-1].type == 'o' || board[x-2][y-1].type != 'o' && board[x-2][y-1].white != board[x][y].white)){
            temp = board[x-2][y-1];
            board[x-2][y-1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-2][y-1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-2][y-1]; 
            board[x-2][y-1] = temp;
            if (stopz){ return; }
        }
        if (x+2 < 8 && y-1 > -1 && (board[x+2][y-1].type == 'o' || board[x+2][y-1].type != 'o' && board[x+2][y-1].white != board[x][y].white)){
            temp = board[x+2][y-1];
            board[x+2][y-1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+2][y-1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+2][y-1]; 
            board[x+2][y-1] = temp;
            if (stopz){ return; }
        }
        if (x+1 < 8 && y-2 > -1 && (board[x+1][y-2].type == 'o' || board[x+1][y-2].type != 'o' && board[x+1][y-2].white != board[x][y].white)){
            temp = board[x+1][y-2];
            board[x+1][y-2] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+1][y-2].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+1][y-2]; 
            board[x+1][y-2] = temp;
            if (stopz){ return; }
        }
        if (x+1 < 8 && y+2 < 8 && (board[x+1][y+2].type == 'o' || board[x+1][y+2].type != 'o' && board[x+1][y+2].white != board[x][y].white)){
            temp = board[x+1][y+2];
            board[x+1][y+2] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+1][y+2].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+1][y+2]; 
            board[x+1][y+2] = temp;
            if (stopz){ return; }
        }
        if (x+2 < 8 && y+1 < 8 && (board[x+2][y+1].type == 'o' || board[x+2][y+1].type != 'o' && board[x+2][y+1].white != board[x][y].white)){
            temp = board[x+2][y+1];
            board[x+2][y+1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x+2][y+1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x+2][y+1]; 
            board[x+2][y+1] = temp;
            
            if (stopz){ return; }
        }
        if (x-2 > -1 && y+1 < 8 && (board[x-2][y+1].type == 'o' || board[x-2][y+1].type != 'o' && board[x-2][y+1].white != board[x][y].white)){
            temp = board[x-2][y+1];
            board[x-2][y+1] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-2][y+1].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                } 
            }
            board[x][y] = board[x-2][y+1]; 
            board[x-2][y+1] = temp;
            if (stopz){ return; }
        }
        if (x-1 > -1 && y+2 < 8 && (board[x-1][y+2].type == 'o' || board[x-1][y+2].type != 'o' && board[x-1][y+2].white != board[x][y].white)){
            temp = board[x-1][y+2];
            board[x-1][y+2] = board[x][y];
            board[x][y] = new Piece(false, 'o', "  "); 
            
            if (!test.Check(board, board[x-1][y+2].white)){
                Copy(board);
                posMoves = posMoves + 1;
                if (max && ply == maxPly-1 && Evaluate(board) > beta){
                    stopz = true;
                }
                if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                    stopz = true;
                }
            }
            board[x][y] = board[x-1][y+2]; 
            board[x-1][y+2] = temp;
        }
    }
    
    private void movePawn(Piece[][] board, int x, int y, int ply, boolean max){
        Piece temp;
        CheckCheck test = new CheckCheck(); 
        
        if (board[x][y].white){
            if (x-1 > -1 && board[x-1][y].type == 'o'){
                temp = board[x-1][y];
                board[x-1][y] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-1][y].white)){
                    if (x-1 == 0){ board[x-1][y].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x-1 == 0){ board[x-1][y].type = 'p'; }
                board[x][y] = board[x-1][y]; 
                board[x-1][y] = temp;
                if (stopz){ return; }
                
                if (x == 6 && board[x-2][y].type == 'o'){
                    temp = board[x-2][y];
                    board[x-2][y] = board[x][y];
                    board[x][y] = new Piece(false, 'o', "  "); 

                    if (!test.Check(board, board[x-2][y].white)){
                        Copy(board);
                        posMoves = posMoves + 1;
                        if (max && ply == maxPly-1 && Evaluate(board) > beta){
                            stopz = true;
                        }
                        if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                            stopz = true;
                        }
                    }
                    board[x][y] = board[x-2][y]; 
                    board[x-2][y] = temp;
                    if (stopz){ return; }
                }
            }
            
            if (x-1 > -1 && y-1 > -1 && board[x-1][y-1].type != 'o' && board[x-1][y-1].white != board[x][y].white){
                temp = board[x-1][y-1];
                board[x-1][y-1] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-1][y-1].white)){
                    if (x-1 == 0){ board[x-1][y-1].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x-1 == 0){ board[x-1][y-1].type = 'p'; }
                board[x][y] = board[x-1][y-1]; 
                board[x-1][y-1] = temp;
                if (stopz){ return; }
            }
            if (x-1 > -1 && y+1 < 8 && board[x-1][y+1].type != 'o' && board[x-1][y+1].white != board[x][y].white){
                temp = board[x-1][y+1];
                board[x-1][y+1] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-1][y+1].white)){
                    if (x-1 == 0){ board[x-1][y+1].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x-1 == 0){ board[x-1][y+1].type = 'p'; }
                board[x][y] = board[x-1][y+1]; 
                board[x-1][y+1] = temp;
            }
        }
        else {
            if (x+1 < 8 && board[x+1][y].type == 'o'){
                temp = board[x+1][y];
                board[x+1][y] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+1][y].white)){
                    if (x+1 == 7){ board[x+1][y].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x+1 == 7){ board[x+1][y].type = 'p'; }
                board[x][y] = board[x+1][y]; 
                board[x+1][y] = temp;
                if (stopz){ return; }
                
                if (x == 1 && board[x+2][y].type == 'o'){
                    temp = board[x+2][y];
                    board[x+2][y] = board[x][y];
                    board[x][y] = new Piece(false, 'o', "  "); 

                    if (!test.Check(board, board[x+2][y].white)){
                        Copy(board);
                        posMoves = posMoves + 1;
                        if (max && ply == maxPly-1 && Evaluate(board) > beta){
                            stopz = true;
                        }
                        if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                            stopz = true;
                        }
                    }
                    board[x][y] = board[x+2][y]; 
                    board[x+2][y] = temp;
                    if (stopz){ return; }
                }
            }
            
            if (x+1 < 8 && y-1 > -1 && board[x+1][y-1].type != 'o' && board[x+1][y-1].white != board[x][y].white){
                temp = board[x+1][y-1];
                board[x+1][y-1] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+1][y-1].white)){
                    if (x+1 == 7){ board[x+1][y-1].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x+1 == 7){ board[x+1][y-1].type = 'p'; }
                board[x][y] = board[x+1][y-1]; 
                board[x+1][y-1] = temp;
                if (stopz){ return; }
            }
            if (x+1 < 8 && y+1 < 8 && board[x+1][y+1].type != 'o' && board[x+1][y+1].white != board[x][y].white){
                temp = board[x+1][y+1];
                board[x+1][y+1] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+1][y+1].white)){
                    if (x+1 == 7){ board[x+1][y+1].type = 'q'; }
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                if (x+1 == 7){ board[x+1][y+1].type = 'p'; }
                board[x][y] = board[x+1][y+1]; 
                board[x+1][y+1] = temp;
            }
        }
    }
    
    private void moveRook(Piece[][] board, int x, int y, int ply, boolean max){
        Piece temp;
        CheckCheck test = new CheckCheck(); 

        for(int z = 1 ; x-z > -1 ; z++){
            if (board[x-z][y].type == 'o' || board[x-z][y].white != board[x][y].white){
                temp = board[x-z][y];
                board[x-z][y] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-z][y].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x-z][y]; 
                board[x-z][y] = temp;
                if (stopz){ return; }
            }
            else if (board[x-z][y].type != 'o'){ z=8; }

        }

        for(int z = 1 ; x+z < 8 ; z++){
            if (board[x+z][y].type == 'o' || board[x+z][y].white != board[x][y].white){
                temp = board[x+z][y];
                board[x+z][y] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+z][y].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x+z][y]; 
                board[x+z][y] = temp;
                if (stopz){ return; }
            }
            if (board[x+z][y].type != 'o'){ z=8; }

        }

        for(int z = 1 ; y-z > -1 ; z++){
            if (board[x][y-z].type == 'o' || board[x][y-z].white != board[x][y].white){
                temp = board[x][y-z];
                board[x][y-z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x][y-z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x][y-z]; 
                board[x][y-z] = temp;
                if (stopz){ return; }
            }
            if (board[x][y-z].type != 'o'){ z=8; }

        }

        for(int z = 1 ; y+z < 8 ; z++){
            if (board[x][y+z].type == 'o' || board[x][y+z].white != board[x][y].white){
                temp = board[x][y+z];
                board[x][y+z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x][y+z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x][y+z]; 
                board[x][y+z] = temp;
                if (stopz){ return; }
            }
            if (board[x][y+z].type != 'o'){ z=8; }

        }
     }

    private void moveBishop(Piece[][] board, int x, int y, int ply, boolean max){
        Piece temp;
        CheckCheck test = new CheckCheck(); 

        for(int z = 1 ; x-z > -1 && y-z > -1 ; z++){
            if (board[x-z][y-z].type == 'o' || board[x-z][y-z].white != board[x][y].white){
                temp = board[x-z][y-z];
                board[x-z][y-z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-z][y-z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x-z][y-z]; 
                board[x-z][y-z] = temp;
                if (stopz){ return; }
            }
            if (board[x-z][y-z].type != 'o'){ z=8; }

        }

        for(int z = 1 ; x+z < 8 && y+z < 8 ; z++){
            if (board[x+z][y+z].type == 'o' || board[x+z][y+z].white != board[x][y].white){
                temp = board[x+z][y+z];
                board[x+z][y+z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+z][y+z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x+z][y+z]; 
                board[x+z][y+z] = temp;
                if (stopz){ return; }
            }
            if (board[x+z][y+z].type != 'o'){ z=8; }

        }

        for(int z = 1 ; x+z < 8 && y-z > -1 ; z++){
            if (board[x+z][y-z].type == 'o' || board[x+z][y-z].white != board[x][y].white){
                temp = board[x+z][y-z];
                board[x+z][y-z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x+z][y-z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x+z][y-z]; 
                board[x+z][y-z] = temp;
                if (stopz){ return; }
            }
            if (board[x+z][y-z].type != 'o'){ z=8; }

        }

        for(int z = 1 ; x-z > -1 && y+z < 8 ; z++){
            if (board[x-z][y+z].type == 'o' || board[x-z][y+z].white != board[x][y].white){
                temp = board[x-z][y+z];
                board[x-z][y+z] = board[x][y];
                board[x][y] = new Piece(false, 'o', "  "); 

                if (!test.Check(board, board[x-z][y+z].white)){
                    Copy(board);
                    posMoves = posMoves + 1;
                    if (max && ply == maxPly-1 && Evaluate(board) > beta){
                        stopz = true;
                    }
                    if (!max && ply == maxPly-1 && Evaluate(board) < alpha){
                        stopz = true;
                    }
                }
                board[x][y] = board[x-z][y+z]; 
                board[x-z][y+z] = temp;
                if (stopz){ return; }
            }
            if (board[x-z][y+z].type != 'o'){ z=8; }

        }
     }

    //Here is where a user-defined test board is made. Only 1 king for each side is required.
    private void testBoard(){
        Scanner console = new Scanner(System.in);
        boolean bKing = false;
        boolean wKing = false;
        boolean inColor;
        boolean valid;
        String inType;

        for (int x = 0 ; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                inColor = false;
                valid = false;
                System.out.print("Enter type at location " + x + ", " + y + ": ");
                inType = console.next();
                System.out.print("Is this a white piece? (y or n) ");
                if (console.next().matches("y")){ inColor = true; }

                switch (inType) {
                    case "k":
                        bigBoard[x][y].type = 'k';
                        if (inColor && !wKing){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Kw";
                            wKing = true;
                            valid = true;  }
                        else if (!inColor && !bKing){
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "kb";
                            bKing = true;
                            valid = true;  }
                        else {
                            System.out.println("INVALID PIECE!");
                            x = x -1;
                            y = y -1;
                        }
                        break;
                    case "q":
                        bigBoard[x][y].type = 'q';
                        if (inColor){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Qw";
                            valid = true;  }
                        else {
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "qb";
                            valid = true;  }
                        break;
                    case "b":
                        bigBoard[x][y].type = 'b';
                        if (inColor){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Bw";
                            valid = true;  }
                        else {
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "bb";
                            valid = true;  }
                        break;
                    case "n":
                        bigBoard[x][y].type = 'n';
                        if (inColor){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Nw";
                            valid = true;  }
                        else {
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "nb";
                            valid = true;  }
                        break;
                    case "r":
                        bigBoard[x][y].type = 'r';
                        if (inColor){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Rw";
                            valid = true;  }
                        else {
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "rb";
                            valid = true;  }
                        break;
                    case "p":
                        bigBoard[x][y].type = 'p';
                        if (inColor){ 
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "Zw";
                            valid = true;  }
                        else {
                            bigBoard[x][y].white = inColor;
                            bigBoard[x][y].name = "zb";
                            valid = true;  }
                        break;
                    case "o":
                        valid = true;
                        break;
                }
                if (!valid){
                    System.out.println("INVALID PIECE!");
                    x = x -1;
                    y = y -1;
                }
            }
            if (x==7 && !wKing && !bKing){
                System.out.print("INVALID BOARD! Start over.");
                x = 0;
            }
        }

     }

    //Checks to see if a Stalemate is found.
    private void StaleCheck(){
         for (int x = 0 ; x < 8 ; x++){
             for (int y = 0 ; y < 8 ; y++){
                 if (bigBoard[x][y].type != 'k' && bigBoard[x][y].type != 'o'){
                     return;
                 }
             }
         }
         Print(bigBoard);
         System.out.print("STALEMATE");
         System.exit(0);
     }

    //Checks to see if the player has any possible moves. If not, check-mate.
    private void MateCheck(Piece[][] root, boolean playColor){
         moves = new Piece[200][8][8];
        posMoves = 0;
        stopz = false;
        
        for (int x = 0 ; x < 200 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                for (int z = 0 ; z < 8 ; z++){
                    moves[x][y][z] = new Piece(false, 'o', "  ");
                }
            }
        }
                
        for (int x = 0; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                if (root[x][y].white == playColor){
                    if (root[x][y].type == 'k' && !stopz){
                        //King
                        moveKing(root, x, y, 1, false);
                    } else if (root[x][y].type == 'q' && !stopz){
                        //Queen
                        moveBishop(root, x, y, 1, false);
                        if (!stopz){ moveRook(root, x, y, 1, false); }
                    } else if (root[x][y].type == 'b' && !stopz){
                        //Bishop
                        moveBishop(root, x, y, 1, false);
                    } else if (root[x][y].type == 'n' && !stopz){
                        //Knight
                        moveKnight(root, x, y, 1, false);
                    } else if (root[x][y].type == 'r' && !stopz){
                        //Rook
                        moveRook(root, x, y, 1, false);
                    } else if (root[x][y].type == 'p' && !stopz){
                        //Pawn
                        movePawn(root, x, y, 1, false);
                    } 
                }
            }
        }
        
        if (posMoves == 0){ Win(); }
     }
     
     //This simply prints the given board state.
    private void Print(Piece[][] puzzle){
        String line;
        
        for (int out=0 ; out< puzzle.length ; out++){
            line = "";
            for (int inner=0 ; inner< puzzle[0].length ; inner++){
                line = line +"["+ puzzle[out][inner].name +"]"; 
            }
            System.out.println(line);
        }
    }
    
    //Copies a board to the "moves" array of boards.
    private void Copy(Piece[][] state){
        for (int x = 0 ; x < 8 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                moves[posMoves][x][y].name = state[x][y].name;
                moves[posMoves][x][y].type = state[x][y].type;
                moves[posMoves][x][y].white = state[x][y].white;
            }
        }
    }
    
    public static void main(String[] args) {Chess c = new Chess();}
}
