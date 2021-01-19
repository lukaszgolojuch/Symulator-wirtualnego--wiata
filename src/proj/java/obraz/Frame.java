package proj.java.obraz;

import proj.java.Organizmy;
import proj.java.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Frame extends JPanel {
    private Organizmy[][] board;
    private int height, width; //rozmiar tablicy
    private Swiat swt; //swiat
    public Frame(JFrame _frame, Swiat wrld,Organizmy[][] _board,int _height, int _width ){
       board=_board; //plansza
       height=_height; //wys planszy	
       width=_width; //szer planszy
       swt = wrld;

       setFocusable(true);
       setFocusTraversalKeysEnabled(true);
       przycisk();       

    }

    public void paint(Graphics g){
        super.paint(g);

        for(int i = 1; i <= height; i++){
            for(int j = 1; j <= width; j++){
            	//rysujemy plansze na ekreanie
            	//rozmiar pola 10x10
            	//przerwa miedzy polami 5
                g.setColor(board[i-1][j-1].Color());
                g.fillRect(130+j*15, 130+i * 15, 10,10);
            }
        }

    }
    
    private void przycisk() {
    	//Przycisk kolejny ruch
        JButton nastepny_ruch = new JButton("Kolejna tura");

        nastepny_ruch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                swt.NextTurn(null); //uruchom kolejny ruch - wykonaj ruch
                
            }
        });
        add(nastepny_ruch);
        nastepny_ruch.setVisible(true);
    }
    
 

}
