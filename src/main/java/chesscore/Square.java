/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public class Square {

        public Piece piece;
        public int row;
        public int column;

    
    public Square(int row, int column,Piece piece) {
        this.row = row;
        this.column = column;
        this.piece = piece;
    }
        
        public boolean isOccupied() {
            return piece != null;
        }
    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    }


