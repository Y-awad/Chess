/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import static java.lang.Math.abs;

/**
 *
 * @author Youssef
 */
public class King extends Piece{

    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Square currentSquare, Square nextSquare) {
        int currX = currentSquare.getRow();
        int currY = currentSquare.getColumn();
        int nextX = nextSquare.getRow();
        int nextY = nextSquare.getColumn();
        
        if (nextX < 0 || nextX >= 8 || nextY < 0 || nextY >= 8) {
            
            return false;
        }
        
        int x=abs(currX-nextX);
        int y=abs(currY-nextY);
        if((x==1 && y==1) || (x==1 && y==0) || (x==0 && y==1) )
        {
            
             if(!nextSquare.isOccupied())
            {
                
                return true;
            }
           
            else if(nextSquare.getPiece().getColor()!=this.getColor())
            {
                //System.out.println("Captured " + nextSquare.getPiece().getType());
                return true;
            }
        }
        //check if castling
        
        else if(currX==0 && currY==4 && nextX==0 && nextY==2)
        {
            
            if(!ChessBoard.board[0][3].isOccupied() && !ChessBoard.board[0][2].isOccupied() && !ChessBoard.board[0][1].isOccupied())
                return true;
        }
        else if(currX==0 && currY==4 && nextX==0 && nextY==6)
        {
            if(!ChessBoard.board[0][5].isOccupied() && !ChessBoard.board[0][6].isOccupied())
                return true;
        }
        else if(currX==7 && currY==4 && nextX==7 && nextY==2)
        {
            if(!ChessBoard.board[7][3].isOccupied() && !ChessBoard.board[7][2].isOccupied() && !ChessBoard.board[7][1].isOccupied())
                return true;
        }
        else if(currX==7 && currY==4 && nextX==7 && nextY==6)
        {
            if(!ChessBoard.board[7][5].isOccupied() && !ChessBoard.board[7][6].isOccupied())
                return true;
        }
        
        
return false;
    }


    
}
