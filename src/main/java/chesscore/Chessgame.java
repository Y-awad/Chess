/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import java.util.ArrayList;

/**
 *
 * @author Youssef
 */
public class Chessgame {
ChessBoard board;
    ArrayList<Square> Valid;
    Square prev_curr_sq=null;
    Square prev_next_sq=null;
    public Chessgame(ChessBoard board) {
        this.board = board;
    }

    public boolean isValidMove(Square current, Square next) {

        if (current == null || !current.isOccupied()) {
            return false;
        }
        
        
        Piece nextPiece=next.getPiece();
        Piece currentPiece = current.getPiece();
        if (currentPiece == null) {
            return false;
        }








        if(currentPiece.getType()==PieceType.PAWN){
            if(!currentPiece.isValidMove(current, next)&&!isEnpassant(current,next))
                return false;
            
            
        }
        if (currentPiece.getType() != PieceType.KING) {
            
            if (currentPiece.isValidMove(current, next)) {
                
                if (currentPiece.getColor() == PieceColor.WHITE) {
                    
                    next.setPiece(currentPiece);
                    current.setPiece(null);
                    if (iswhiteincheck()) {
                        current.setPiece(currentPiece);
                        next.setPiece(nextPiece);
                        return false;
                    }
                    current.setPiece(currentPiece);
                    next.setPiece(nextPiece);
                    return true;
                }

                else {

                    next.setPiece(currentPiece);
                    current.setPiece(null);                                  
                    if (isblackincheck()) {
                        current.setPiece(currentPiece);
                        next.setPiece(nextPiece);
                        return false;
                    }
                    current.setPiece(currentPiece);
                     next.setPiece(nextPiece);
                     return true;
                }

            }
            else return false;
            
        } else if (currentPiece.getType() == PieceType.KING) {
            if (current.getPiece().isValidMove(current, next)) {// see if ur king will be incheck after move
                
                next.setPiece(currentPiece);
                current.setPiece(null);
                
                if (next.getPiece().getColor() == PieceColor.WHITE) {
                 
                    if (iswhiteincheck()) {
                        
                        current.setPiece(currentPiece);
                        next.setPiece(nextPiece);
                        return false;
                    }
                        current.setPiece(currentPiece); 
                        next.setPiece(nextPiece);
                       
                } else {
                    
                    if (isblackincheck()) {
                        
                        current.setPiece(currentPiece);
                        next.setPiece(nextPiece);
                        return false;
                    }
                    current.setPiece(currentPiece);
                        next.setPiece(nextPiece);
                }       
            }
            else return false;
        }
                   

        return true;

    }
    

    public ArrayList<Square> getAllValidMovesfromSquare(Square current) {
        int i, j;
        
        ArrayList<Square> Valid = new ArrayList<>();
        Valid.clear();
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++) {
                    if (isValidMove(current, ChessBoard.board[i][j])) {
                        
                        Valid.add(ChessBoard.board[i][j]);
                }
            }
        return Valid;
    }

    public void Move(Square current, Square next) {
        prev_curr_sq=current;
        prev_next_sq=next;
        next.setPiece(current.getPiece());
        current.setPiece(null);
    }

    public boolean iswhiteincheck() {
        int i, j;
        
        Square King = getwhiteking();
        
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++) {
                Piece piece = ChessBoard.board[i][j].getPiece();
                
                if (piece!=null)
                {
                    if(piece.isValidMove(ChessBoard.board[i][j], King))
                    {
                        return true;
                    }
                }
                    
            }
        return false;
    }

    public boolean isblackincheck() {
        int i, j;
        Square King = getblackKing();
        if (King == null) {
            return false; 
        }
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++) {
                Piece piece = ChessBoard.board[i][j].getPiece();
                if (piece != null) {
                    if(piece.isValidMove(ChessBoard.board[i][j], King))
                    {
                        
                        return true;
                    }
                }
            }
        return false;
    }

    public Square getwhiteking() {
        int i, j, flag = 0;
        Square W_King = null;

        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                 Piece currPiece = ChessBoard.board[i][j].getPiece();
                if (currPiece!=null && currPiece.getType()==PieceType.KING
                        && currPiece.getColor()==PieceColor.WHITE) {
                    W_King = ChessBoard.board[i][j];
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        return W_King;
    }

    public Square getblackKing() {
        int i, j, flag = 0;
        Square B_King = null;
        
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                Piece currPiece = ChessBoard.board[i][j].getPiece();
                if (currPiece != null && currPiece.getType() == PieceType.KING
                        && currPiece.getColor() == PieceColor.BLACK) {
                    B_King = ChessBoard.board[i][j];
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        return B_King;
    }

    public boolean iswhitecheckmated() {
        Square W_king = getwhiteking();
       
        if (iswhiteincheck()) {
            ArrayList<Square> kingValidMoves =getAllValidMovesfromSquare(W_king); 
            if (kingValidMoves.isEmpty()) {
                
                //  all  pieces
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Square currentSquare = ChessBoard.board[i][j];
                        Piece piece = currentSquare.getPiece();

                        // Check if the piece is white 
                        if (piece != null && piece.getColor() == PieceColor.WHITE) {
                            // go through all valid moves for the current white piece
                            ArrayList<Square> validMoves = getAllValidMovesfromSquare(currentSquare);
                            for (int k = 0; k < validMoves.size(); k++) {
                                Square validMove = validMoves.get(k);

                                // do the move
                                Piece capturedPiece = validMove.getPiece();
                                currentSquare.setPiece(null);
                                validMove.setPiece(piece);

                                // Check if the white king is still in check after the move
                                if (!iswhiteincheck()) {
                                    // The move  gets the king out of check
                                    
                                    currentSquare.setPiece(piece);
                                    validMove.setPiece(capturedPiece);
                                    return false; // White is not checkmated
                                }

                                // Undo the move
                                currentSquare.setPiece(piece);
                                validMove.setPiece(capturedPiece);
                            }
                        }
                    }
                }

                // If no move can get the king out of check, white is checkmated
               // System.out.println("White is Checkmated");
                return true;
            }
        }

        return false; // White is not checkmated
    }

    public boolean isblackcheckmated() {
        Square B_king = getblackKing();
        
        
        if (iswhiteincheck()) {
            
            ArrayList<Square> kingValidMoves = getAllValidMovesfromSquare(B_king);
            if (kingValidMoves.isEmpty()) {
                // //  all  pieces
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Square currentSquare = ChessBoard.board[i][j];
                        Piece piece = currentSquare.getPiece();

                        // Check if the piece is Black
                        if (piece != null && piece.getColor() == PieceColor.BLACK) {
                            // go through all valid moves for the current BLACK piece
                            ArrayList<Square> validMoves = getAllValidMovesfromSquare(currentSquare);
                            for (int k = 0; k < validMoves.size(); k++) {
                                Square validMove = validMoves.get(k);

                                // do the move
                                Piece capturedPiece = validMove.getPiece();
                                currentSquare.setPiece(null);
                                validMove.setPiece(piece);

                                // Check if the BLACK king is still in check after the move
                                if (!iswhiteincheck()) {
                                    // The move successfully gets the king out of check
                                   
                                    currentSquare.setPiece(piece);
                                    validMove.setPiece(capturedPiece);
                                    return false; // BLACK is not checkmated
                                }

                                // Undo the move
                                currentSquare.setPiece(piece);
                                validMove.setPiece(capturedPiece);
                            }
                        }
                    }
                }

                // If no move can get the king out of check, BLACK is checkmated
                //System.out.println("Black is Checkmated");
                return true;
            }
        }

        return false; // BLACK is not checkmated
    }

    public void Promotion(Square p, PieceType T) {
        if (p.getPiece().getType() == PieceType.PAWN) {
            p.getPiece().setType(T);
        }
    }

    public boolean movecasteling(Square current,Square next)
           
    { 
        int currX = current.getRow();
        int currY = current.getColumn();
        int nextX = next.getRow();
        int nextY = next.getColumn();
        
         if(currX==0 && currY==4 && nextX==0 && nextY==2)
        {
           next.setPiece(current.getPiece());
           ChessBoard.board[0][3].setPiece(ChessBoard.board[0][0].getPiece());
           current.setPiece(null);
           ChessBoard.board[0][0].setPiece(null);
           return true;
           
        }
        else if(currX==0 && currY==4 && nextX==0 && nextY==6)
        {
           next.setPiece(current.getPiece());
           ChessBoard.board[0][5].setPiece(ChessBoard.board[0][7].getPiece());
           current.setPiece(null);
           ChessBoard.board[0][7].setPiece(null);
           return true;
        }
        else if(currX==7 && currY==4 && nextX==7 && nextY==2)
        {
           next.setPiece(current.getPiece());
           ChessBoard.board[7][3].setPiece(ChessBoard.board[7][0].getPiece());
           current.setPiece(null);
           ChessBoard.board[7][0].setPiece(null);
           return true;
        }
        else if(currX==7 && currY==4 && nextX==7 && nextY==6)
        {
            next.setPiece(current.getPiece());
           ChessBoard.board[7][5].setPiece(ChessBoard.board[7][7].getPiece());
           current.setPiece(null);
           ChessBoard.board[7][7].setPiece(null);
           return true;
        }
         return false;
    }

    public boolean isStalemate() {
        Square w_king = getwhiteking();
        Square b_king = getblackKing();
       
        if (getAllValidMovesfromSquare(w_king).isEmpty() && !iswhiteincheck()) {
           
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Square currentSquare = ChessBoard.board[i][j];
                    Piece piece = currentSquare.getPiece();
    
                   
                    if (piece != null && piece.getColor() == PieceColor.WHITE) {
                        
                        ArrayList<Square> validMoves = getAllValidMovesfromSquare(currentSquare);
    
                        
                        if (!validMoves.isEmpty()) {
                            return false;
                        }
                    }
                }
            }
    
            
            return true;
        } else if (getAllValidMovesfromSquare(b_king).isEmpty() && !isblackincheck()) {
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Square currentSquare = ChessBoard.board[i][j];
                    Piece piece = currentSquare.getPiece();
    
                
                    if (piece != null && piece.getColor() == PieceColor.BLACK) {
                        
                        ArrayList<Square> validMoves = getAllValidMovesfromSquare(currentSquare);
                      
                       
                        if (!validMoves.isEmpty()) {
                            return false;
                        }
                    }
                }
            }
    
            
            return true;
        }
    
       
        return false;
        

    }
    
    public boolean isDrawInsuffMat(){
          
        if (isOnlyKingsLeft()) {
            
            return true;
        }
        
        else if (!hasSufficientMaterial(PieceColor.WHITE) && !hasSufficientMaterial(PieceColor.BLACK)) {
        return true;
    }

    return false;
    }
    private boolean isOnlyKingsLeft() {
        // Feh ay peices 8er el kings
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = ChessBoard.board[i][j].getPiece();
                if (piece != null && piece.getType() != PieceType.KING) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean hasSufficientMaterial(PieceColor color) {
        //  feh ay peices 8er el kings wel knights
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = ChessBoard.board[i][j].getPiece();
                if (piece != null && piece.getColor() == color &&
                    piece.getType() != PieceType.KING && piece.getType() != PieceType.KNIGHT) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    boolean isEnpassant(Square current,Square next){
        
        
        Piece curr_piece=current.getPiece();
        
        
       
            
        if(prev_curr_sq.getRow()==1&&prev_next_sq.getRow()==3&&prev_next_sq.piece.getType()==PieceType.PAWN){
            
        if(current.getRow()==3&&(current.getColumn()+1==prev_next_sq.getColumn()||current.getColumn()-1==prev_next_sq.getColumn())){
           
            if(curr_piece.getColor()!=prev_next_sq.getPiece().getColor()){
              
                if(next.getRow()==2&&(next.getColumn()==current.getColumn()+1)||(next.getColumn()==current.getColumn()-1)){
                    
                    board.getSquare(prev_curr_sq.getRow(), prev_curr_sq.getColumn()).setPiece(null);
                    
                    System.out.println("Enpassant");
                    System.out.println("Captured Pawn");
                    return true;
                }
               
                
            } 
            
        } 
    }
         if(prev_curr_sq.getRow()==6&&prev_next_sq.getRow()==4&&prev_next_sq.piece.getType()==PieceType.PAWN){
        if(current.getRow()==4&&(current.getColumn()+1==prev_next_sq.getColumn()||current.getColumn()-1==prev_next_sq.getColumn())){
            if(curr_piece.getColor()!=prev_next_sq.getPiece().getColor()){
                if(next.getRow()==5&&(next.getColumn()==current.getColumn()+1)||(next.getColumn()==current.getColumn()-1)){
                   
                    board.getSquare(prev_curr_sq.getRow(), prev_curr_sq.getColumn()).setPiece(null);
                    System.out.println("Enpassant");
                    System.out.println("Captured Pawn");
                    return true;
                }
            }
        }
    }      
     
        
                
        
         return false;
    
    }


}

    
    

