/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public class Rook extends Piece{

    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);

    }


    @Override
    public boolean isValidMove(Square current, Square next) {
        int currX = current.getRow();
        int currY = current.getColumn();
        int nextX = next.getRow();
        int nextY = next.getColumn();
         int direction;
        if (currX < nextX || currY < nextY) {
            direction = 1;
        } else {
            direction = -1;
        }

        if (nextX < 0 || nextX >= 8 || nextY < 0 || nextY >= 8) {
            return false;
        }

        if (currX == nextX) {
            for (int i = currY + direction; i >= 0 && i < 8 && i != nextY; i += direction) {
                
            if (ChessBoard.board[currX][i].isOccupied()) {
                return false; 
            }
            
        }
            if(next.isOccupied()&& next.getPiece().getColor()!=this.getColor())
          {
            return true;
          }
        if(!next.isOccupied())
            return true;
        }
       
        if (currY == nextY) {
            for (int i = currX + direction; i >= 0 && i < 8 && i != nextX; i += direction) {
                               
                
            if (ChessBoard.board[i][currY].isOccupied()) {
                return false; 
            }
        }
        
        if(next.isOccupied()&& next.getPiece().getColor()!=this.getColor())
          {
            return true;
          }
        if(!next.isOccupied())
            return true;
        }
        
        return false;
    }


    
    
}
