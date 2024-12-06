/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public abstract class Piece {
    private PieceColor color;
    private PieceType type;



    public Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor() {
        return this.color;
    }


    public PieceType getType() {
        return this.type;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public void setType(PieceType type) {
        this.type = type;
    }
    
    public abstract boolean isValidMove(Square currentsquare,Square nextSquare);

    
}
