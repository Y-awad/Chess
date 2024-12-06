/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author Youssef
 */
public class ChessBoard {
   public static Square[][] board;

    public ChessBoard() {
        
        board = new Square[8][8];
        initializeBoard();
    }
    
    public Square getSquare(int x, int y) {
        return board[x][y];
    }
    
    private void initializeBoard() {
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Square(row, col,null);
            }
        }

        // PAWNS
        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(new Pawn(PieceColor.BLACK));
            board[6][col].setPiece(new Pawn(PieceColor.WHITE));
        }
        //ROOKS
             board[0][0].setPiece(new Rook(PieceColor.BLACK));
             board[0][7].setPiece(new Rook(PieceColor.BLACK));
             board[7][0].setPiece(new Rook(PieceColor.WHITE));
             board[7][7].setPiece(new Rook(PieceColor.WHITE));
        

        // KNIGHTS
        board[0][1].setPiece(new Knight(PieceColor.BLACK));
        board[0][6].setPiece(new Knight(PieceColor.BLACK));
        board[7][1].setPiece(new Knight(PieceColor.WHITE));
        board[7][6].setPiece(new Knight(PieceColor.WHITE));

        // BISHOPS
        board[0][2].setPiece(new Bishop(PieceColor.BLACK));
        board[0][5].setPiece(new Bishop(PieceColor.BLACK));
        board[7][2].setPiece(new Bishop(PieceColor.WHITE));
        board[7][5].setPiece(new Bishop(PieceColor.WHITE));

        // QUEENS
        board[0][3].setPiece(new Queen(PieceColor.BLACK));
        board[7][3].setPiece(new Queen(PieceColor.WHITE));

        // KINGS
        board[0][4].setPiece(new King(PieceColor.BLACK));
        board[7][4].setPiece(new King(PieceColor.WHITE));
    }

    
}
