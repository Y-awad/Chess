/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public class Pawn extends Piece{
    private boolean isFirstMove = true;

    public Pawn(PieceColor color) {
        super(color,PieceType.PAWN);
        
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
        if(currX!=1 && currX!=6)
        {
            isFirstMove=false;
        }
      
        int direction;

        if (currentSquare.getPiece().getColor() == PieceColor.WHITE) {
            direction = -1;
        } else {
            direction = 1;
        }
        
        if (nextX == currX + direction && currY==nextY && !nextSquare.isOccupied()) {   
            return true;
        }
        else if (isFirstMove && nextX == currX + 2 * direction && !nextSquare.isOccupied() && !ChessBoard.board[currX + direction][currY].isOccupied()) {
            
            return true;
        } 
        else if(nextX == currX + direction && (nextY == currY + direction || nextY== currY - direction) )
        {
          if(nextSquare.isOccupied()&& nextSquare.getPiece().getColor()!=this.getColor())
          {
            return true; 
          }
        }
        return false;
    }

    
}
