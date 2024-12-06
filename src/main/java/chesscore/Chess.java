/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Chess {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        
        Chessgame chessGame = new Chessgame(chessBoard);
        ArrayList<String> moves = readMovesFromFile("ChessGame.txt ");

        {

        }
        for (String move : moves) {
            String[] moveParts = move.split(",");
            String currentSquareNotation = " ";
            String nextSquareNotation = " ";
            char Prom = ' ';
            if (moveParts.length == 3) {
                currentSquareNotation = moveParts[0].trim();
                nextSquareNotation = moveParts[1].trim();
                String Promotion = moveParts[2].trim();
                Prom = Promotion.charAt(0);
            } else if (moveParts.length == 2) {
                currentSquareNotation = moveParts[0].trim();
                nextSquareNotation = moveParts[1].trim();
            }

            Square currentSquare = convertToIndices(currentSquareNotation);

            Square nextSquare = convertToIndices(nextSquareNotation);

            // Execute the move
            if (moveParts.length == 2) {
                if (chessGame.isblackcheckmated() || chessGame.iswhitecheckmated()) {
                    System.out.println("Game Already ended");

                } else if (chessGame.isValidMove(currentSquare, nextSquare)) {

                    if (chessGame.movecasteling(currentSquare, nextSquare)) {

                        System.out.println("Castle");
                    } else {
                        if (nextSquare.isOccupied()) {
                            System.out.println("Captured " + nextSquare.getPiece().getType());
                        }
                        chessGame.Move(currentSquare, nextSquare);

                         if(chessGame.isEnpassant(currentSquare, nextSquare)){
                        
                            System.out.println();
                        System.out.println("Captured Pawn");
                        chessGame.Move(currentSquare, nextSquare);
                         }

                    }

                    if (chessGame.iswhitecheckmated()) {
                        System.out.println("Black Won");
                    } else if (chessGame.isblackcheckmated()) {
                        System.out.println("White Won");
                    } else if (chessGame.isDrawInsuffMat()) {
                        System.out.println("Insufficient Material");
                    } else if (chessGame.isStalemate()) {
                        System.out.println("Stalemate");
                    } else if (chessGame.iswhiteincheck()) {
                        System.out.println("White in check");
                    } else if (chessGame.isblackincheck()) {
                        System.out.println("Black in check");
                    }
                } else {
                    System.out.println("Invalid move");
                }
            } 
            else if (moveParts.length == 3) {
                if (chessGame.isValidMove(currentSquare, nextSquare)) {
                    if (nextSquare.isOccupied()) {
                            System.out.println("Captured " + nextSquare.getPiece().getType());
                        }
                    System.out.println("Promotion");
                    switch (Prom) {
                        case 'R':
                            Rook R = new Rook(currentSquare.getPiece().getColor());
                            currentSquare.setPiece(R);
                            break;
                        case 'Q':
                            Queen Q = new Queen(currentSquare.getPiece().getColor());
                            currentSquare.setPiece(Q);
                            break;
                        case 'K':
                            Knight K = new Knight(currentSquare.getPiece().getColor());
                            currentSquare.setPiece(K);
                            break;
                        case 'B':
                            Bishop B = new Bishop(currentSquare.getPiece().getColor());
                            currentSquare.setPiece(B);
                            break;

                        default:
                            System.out.println("Promotion");

                    }
                    chessGame.Move(currentSquare, nextSquare);
                }

            }

        }
    }

    private static ArrayList<String> readMovesFromFile(String filename) {
        ArrayList<String> moves = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                moves.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moves;
    }

    public static Square convertToIndices(String move) {
        if (move.length() != 2 && move.length() != 3) {
            throw new IllegalArgumentException("Invalid syntax:");
        }

        char file = move.charAt(0);
        int rank = Character.getNumericValue(move.charAt(1));

        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
            throw new IllegalArgumentException("Invalid file or rank");
        }

        int row = 8 - rank;
        int col = file - 'a';
        return ChessBoard.board[row][col];
    }

   
   
}
