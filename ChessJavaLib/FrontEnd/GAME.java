/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import java.awt.*;
import javax.swing.*;
import ChessCore.*;
import ChessCore.Pieces.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Youssef
 */
public class GAME extends javax.swing.JFrame {
      
    ClassicChessGame ccg=new ClassicChessGame();
    
    JButton[][] square=new JButton[8][8];
    JButton undo=new JButton();
    int k=0;
    ArrayList<Integer> cap=new ArrayList();
    
    Icon w_pawn=new ImageIcon(getClass().getResource("whitepawn_1.png"));
    Icon b_pawn=new ImageIcon(getClass().getResource("blackpawn_1.png"));
    Icon w_king=new ImageIcon(getClass().getResource("whiteking_1.png"));
    Icon b_king=new ImageIcon(getClass().getResource("blackking_1.png"));
    Icon w_queen=new ImageIcon(getClass().getResource("whitequeen_1.png"));
    Icon b_queen=new ImageIcon(getClass().getResource("blackqueen_1.png"));
    Icon w_rook=new ImageIcon(getClass().getResource("whiterook_1.png"));
    Icon b_rook=new ImageIcon(getClass().getResource("blackrook_1.png")); 
    Icon w_bishop=new ImageIcon(getClass().getResource("whitebishop_1.png"));
    Icon b_bishop=new ImageIcon(getClass().getResource("blackbishop_1.png"));
    Icon w_knight=new ImageIcon(getClass().getResource("whitehorse.png"));
    Icon b_knight=new ImageIcon(getClass().getResource("blackhorse.png"));

    /**
     * Creates new form GAME
     */
    public GAME() {
        initComponents();
        Boardinit();
        play();
        undoAction();
    }
    
    private void Boardinit()
    {
        for(int i=0 ;i<8;i++) 
        {
            for(int j=0;j<8;j++)
            {                
                square[i][j]=new JButton();
                square[i][j].setBounds(j*62,7*59-i*59,64,64);
                square[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
            }
        }
        setboardcolor();
        setboardpieces();
        undo.setBounds(0, 8*59, 512, 100);
        undo.setBackground(Color.black);
        undo.setText("UNDO");
        undo.setFont(new Font("arial",Font.BOLD,20));
        undo.setForeground(Color.WHITE);
        
    }
    private void setboardcolor()
    {
        for(int i=0 ;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if((i%2==1 && j%2==1) || (i%2==0 && j%2==0))
                {square[i][j].setBackground(Color.getHSBColor(18, (float)0.15,(float) 0.8));}
                else
                {square[i][j].setBackground(Color.getHSBColor(275,(float) 0.14,(float)0.47));}
            }
        }
    }
    private void setboardpieces()
    {
        for(int i=0 ;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(i==6)
                {
                    square[i][j].setIcon(b_pawn);
                }
                else if(i==1)
                {
                    square[i][j].setIcon(w_pawn);
                }
                else if(i==7 && (j==0 || j==7))
                {
                    square[i][j].setIcon(b_rook);
                }
                else if(i==0 && (j==0 || j==7))
                {
                    square[i][j].setIcon(w_rook);
                }
                else if(i==7 && (j==1 || j==6))
                {
                    square[i][j].setIcon(b_knight);
                }
                else if(i==0 && (j==1 || j==6))
                {
                    square[i][j].setIcon(w_knight);
                }
                else if(i==7 && (j==2 || j==5))
                {
                    square[i][j].setIcon(b_bishop);
                }
                else if(i==0 && (j==2 || j==5))
                {
                    square[i][j].setIcon(w_bishop);
                }
                else if(i==7 && j==3)
                {
                    square[i][j].setIcon(b_queen);
                }
                else if(i==0 && j==3)
                {
                    square[i][j].setIcon(w_queen);
                }
                else if(i==7 && j==4)
                {
                    square[i][j].setIcon(b_king);
                }
                else if(i==0 && j==4)
                {
                    square[i][j].setIcon(w_king);
                }
            }
        }
    }
    private void play()
    {
        for(int i=0 ;i<8;i++) 
        {
            for(int j=0;j<8;j++)
            {                
                BoardFile file = null;
                BoardRank rank = null;
                       switch(j)
                {
                    case 0 -> file=BoardFile.A;
                    case 1 -> file=BoardFile.B;
                    case 2 -> file=BoardFile.C;
                    case 3 -> file=BoardFile.D;
                    case 4 -> file=BoardFile.E;
                    case 5 -> file=BoardFile.F;
                    case 6 -> file=BoardFile.G;
                    case 7 -> file=BoardFile.H;
                }
                switch(i)
                {
                    case 0 -> rank=BoardRank.FIRST;
                    case 1 -> rank=BoardRank.SECOND;
                    case 2 -> rank=BoardRank.THIRD;
                    case 3 -> rank=BoardRank.FORTH;
                    case 4 -> rank=BoardRank.FIFTH;
                    case 5 -> rank=BoardRank.SIXTH;
                    case 6 -> rank=BoardRank.SEVENTH;
                    case 7 -> rank=BoardRank.EIGHTH;
                }
                Square s=new Square(file,rank);
                        
                
                
                square[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        setboardcolor();checkgamestatus();
                        java.util.List<Square> L= ccg.getAllValidMovesFromSquare(s);
                        
                        for(Square x: L)
                        {
                            int jj=x.getFile().getValue();
                            int ii=x.getRank().getValue();
                            square[ii][jj].setBackground(Color.green);
                            if(square[ii][jj].getBackground()==Color.green)
                            {
                                square[ii][jj].addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    move(s,x,square[ii][jj]);
                                    
                                    
                                }

                            });
                            }
                            
                        }
                    }
                });
                
            }
        }
    }
    private void move(Square s1,Square s2,JButton b2)
    {
        
        if( ccg.hasPieceIn(s1) && b2.getBackground()==Color.green)
        {k++;
            Move m=new Move(s1,s2);
            ccg.SaveMoves(m);
            if(ccg.hasPieceIn(s2))
            {
                ccg.SavecapturedPieces(s2);
                cap.add(k);
            }
            
            ccg.makeMove(m);
            flipboard();
            

           
       
        int x1=s1.getRank().getValue();
        int x2=s2.getRank().getValue();
        int y1=s1.getFile().getValue();
        int y2=s2.getFile().getValue();
        if(x1==0 && y1==4 && x2==0 && y2==6)
        {
            square[x2][y2].setIcon(square[x1][y1].getIcon());
            square[x2][y2-1].setIcon(square[x2][y2+1].getIcon());
            square[x2][y2+1].setIcon(null);
            square[x1][y1].setIcon(null);
        }
        else if(x1==0 && y1==4 && x2==0 && y2==2)
        {
            square[x2][y2].setIcon(square[x1][y1].getIcon());
            square[x2][y2+1].setIcon(square[x2][y2-2].getIcon());
            square[x2][y2-2].setIcon(null);
            square[x1][y1].setIcon(null);
        }
        else if(x1==7 && y1==4 && x2==7 && y2==6)
        {
            square[x2][y2].setIcon(square[x1][y1].getIcon());
            square[x2][y2-1].setIcon(square[x2][y2+1].getIcon());
            square[x2][y2+1].setIcon(null);
            square[x1][y1].setIcon(null);
        }
        else if(x1==7 && y1==4 && x2==7 && y2==2)
        {
            square[x2][y2].setIcon(square[x1][y1].getIcon());
            square[x2][y2+1].setIcon(square[x2][y2-2].getIcon());
            square[x2][y2-2].setIcon(null);
            square[x1][y1].setIcon(null);
        }
        else
        {
            square[x2][y2].setIcon(square[x1][y1].getIcon());
            square[x1][y1].setIcon(null);
        }
        }
    }
       
    private void checkgamestatus()
    {
         if(ccg.getGameStatus()==GameStatus.WHITE_UNDER_CHECK)
        {
            for(int i=0; i<8;i++)
                for(int j=0;j<8;j++)
                {
                    if (square[i][j].getIcon()==w_king)
                    {
                        square[i][j].setBackground(Color.red);
                    }
                        
                }
        }
         else if(ccg.getGameStatus()==GameStatus.BLACK_UNDER_CHECK)
        {
            for(int i=0; i<8;i++)
                for(int j=0;j<8;j++)
                {
                    if (square[i][j].getIcon()==b_king)
                    {
                        square[i][j].setBackground(Color.red);
                    }
                        
                }
        }
         if(ccg.getGameStatus()==GameStatus.BLACK_WON || ccg.getGameStatus()==GameStatus.WHITE_WON || ccg.getGameStatus()==GameStatus.INSUFFICIENT_MATERIAL || ccg.getGameStatus()==GameStatus.STALEMATE )
         {
             JOptionPane.showMessageDialog(null,ccg.getGameStatus());
         }
    }
    private void flipboard()
    {
        if(ccg.getWhoseTurn()==Player.WHITE)
        {
            for(int i=0 ;i<8;i++) 
        {
            for(int j=0;j<8;j++)
            {                
                
                square[i][j].setBounds(j*62,7*59-i*59,64,64);
                
            }
        }
        }
        else
        {
            for(int i=0 ;i<8;i++) 
        {
            for(int j=0;j<8;j++)
            {                
                
                square[i][j].setBounds(j*62,i*59,64,64);
                
            }
        }
        }
    }
    
    private void undoAction()
    {
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ccg.getSavedMoves().isEmpty())
                {
                    
                    
                    Piece p=null;
                    if(!cap.isEmpty())
                {
                    if(k==cap.get(cap.size()-1) && !ccg.getcapturedPieces().isEmpty())
                {
                     p=ccg.getLastcapturedPiece();
                     ccg.ReturncapturedPiece();
                cap.remove(cap.size()-1);
                }
                }
                    k--;
                
                Move last=ccg.getLAstMove();
                Square s1=last.getToSquare();
                Square s2=last.getFromSquare();
                int x1=s1.getRank().getValue();
                int x2=s2.getRank().getValue();
                int y1=s1.getFile().getValue();
                int y2=s2.getFile().getValue();
                
                Move move=new Move(s1,s2);
                ccg.ReverseMove(move,p);
                
                
                
                if(p!=null)
                {
                if(p.getOwner()==Player.BLACK)
                {
                    if( p instanceof Pawn)
                {
                    
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(b_pawn);
                }
                    if( p instanceof Rook)
                {
                   square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(b_rook);
                }
                    if( p instanceof Knight)
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(b_knight);
                }
                    if( p instanceof Bishop)
                {
                   square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(b_bishop);
                }
                    if( p instanceof Queen)
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(b_queen);
                }
                }
                
                else if(p.getOwner()==Player.WHITE)
                {
                    if( p instanceof Pawn)
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(w_pawn);
                }
                    if( p instanceof Rook)
                {
                   square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(w_rook);
                }
                    if( p instanceof Knight)
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(w_knight);
                }
                    if( p instanceof Bishop)
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(w_bishop);
                }
                    if( p instanceof Queen)
                {
                   square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(w_queen);
                }
                }
                }
                else
                {
                    square[x2][y2].setIcon(square[x1][y1].getIcon());
                square[x1][y1].setIcon(null);
                }
                ccg.undoLastMove();
                
                flipboard();
                }
            }
        });
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GAME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GAME g=new GAME();
                
                
                g.setSize(512,612);
                g.setVisible(true);
                g.add(g.undo);

               for(int i=0;i<8;i++)
                   for(int j=0;j<8;j++)
                   {
                        g.add(g.square[i][j]);
                   }
               
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
