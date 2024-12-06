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
public class Bishop extends Piece{



    public Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
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
        
         if(x==y)
        {
            
            if(currX+x==nextX && currY+y==nextY)
            {
                
                for(int i=1;i<x;i++)
                {
                    if(ChessBoard.board[currX+i][currY+i].isOccupied())
                        return false;
                }
            }
            
            else if(currX+x==nextX && currY-y==nextY)
            {
                for(int i=1;i<x;i++)
                {
                    if(ChessBoard.board[currX+i][currY-i].isOccupied())
                        return false;
                }
            }
            
            else if(currX-x==nextX && currY+y==nextY)
            {
                for(int i=1;i<x;i++)
                {
                    if(ChessBoard.board[currX-i][currY+i].isOccupied())
                        return false;
                }
            }
            
            else if(currX-x==nextX && currY-y==nextY)
            {
                for(int i=1;i<x;i++)
                {
                    if(ChessBoard.board[currX-i][currY-i].isOccupied())
                        return false;
                }
            }
            
            if(!nextSquare.isOccupied())
            {
                return true;
            }
           
            else if(nextSquare.getPiece().getColor()!=this.getColor())
            {
                return true;
            }
                
        }
        return false;
        
    }



    }
    
    

