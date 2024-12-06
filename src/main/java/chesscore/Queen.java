/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public class Queen extends Piece {

public Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(Square current, Square next) {
        int currX = current.getRow();
        int currY = current.getColumn();
        int nextX = next.getRow();
        int nextY = next.getColumn();
        
        int directionX;
        if (currX < nextX) {
            directionX = 1;
        } else if (currX > nextX) {
            directionX = -1;
        } else {
            directionX = 0;
        }

        int directionY;
        if (currY < nextY) {
            directionY = 1;
        } else if (currY > nextY) {
            directionY = -1;
        } else {
            directionY = 0;
        }

       
        if (nextX < 0 || nextX >= 8 || nextY < 0 || nextY >= 8) {
            return false;
        }

        
        if (currX == nextX || currY == nextY || Math.abs(currX - nextX) == Math.abs(currY - nextY)) {
        int x = currX + directionX;
        int y = currY + directionY; 
        
        while (x != nextX || y != nextY) {
            
            if (ChessBoard.board[x][y].isOccupied()) {
                
                return false; 
            }
            x += directionX;
            y += directionY;
        }
       if(!next.isOccupied())
            {
                return true;
            }
           
            else if(next.getPiece().getColor()!=this.getColor())
            {
                return true;
            }
        }
      
        return false;
    }

    
    
}
