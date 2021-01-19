package proj.java;


import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import proj.java.obraz.Frame;


public class Swiat  {
    private int tura = 0;
    private int width, height;
    public List<Organizmy> organisms = new LinkedList<>(); //lista organizmów pozwalająca na przyspieszenie działania programu
    													   //nie musimy przeglądać wszystkich miejsc w tablicy
    private List<Organizmy>toAdd = new LinkedList<>(); //lista organizmow przeznaczonych do dodania
    private List<Organizmy>toRem = new LinkedList<>(); //lista organizmow przeznaczonych do usuniecia
    public JFrame frame;
    public Organizmy[][] board;
    Swiat(int _width, int _height){
        width = _width;
        height = _height;
        board = new Organizmy[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = new Ziemia();
            }
        }
    }

    public void Swiat_rysuj(){

        frame = new JFrame(); //tworzymy frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,1,0,0));
        frame.setSize(600,600); //rozmiar mapy
        Frame fr = new Frame(frame, this,board,height,width);
        fr.setBackground(Color.BLACK); //kolor tła
        frame.add(fr); //dodajemy zmiany 

        frame.setVisible(true); //ustawiamy widoczność na prawdę
    }

    public void Add(Organizmy nowy_org){
        board[nowy_org.pozycja.y][nowy_org.pozycja.x] = nowy_org;
        organisms.add(nowy_org);
    }
    public void toAdd(Organizmy nowy_org){
        board[nowy_org.pozycja.y][nowy_org.pozycja.x] = nowy_org;
        toAdd.add(nowy_org);
    }
    public void NextTurn(Kierunki dir){
    	
    	//główna funcja wykonująca cały procesz dokonywania ruchu
    	
    	tura++; //inkrementacja ilości tur
    	System.out.println(""); //nowa linia
    	System.out.println("Nowa tura! To już " + tura + " tura."); //zaznacz ze dalsze komentarze są z nowej tury
    	   
        for(Organizmy org : organisms){
	        if(org.atak_jez<=0) 
	        	{
	            	org.Akcja(); //odtwarzamy akcję przypisaną do obiektu
	        	}
        }
        for(Organizmy org : toAdd){
            if(!( org instanceof Ziemia)){
                Add(org);
            }
        }
        toAdd.clear();
        for(Organizmy org : organisms){
            if(board[org.pozycja.y][org.pozycja.x] instanceof Ziemia) //sprawdzamy czy organizm nie zniknął podczas tury
            {
                toRem.add(org); //wrzucamy do listy do usunięcia
            }
        }
        for(Organizmy org : toRem){
            organisms.remove(org); //kasujemy organizmy które były przeznaczone do usunięcia
        }
        toRem.clear();

        frame.repaint();

    }
    public int GetHeight(){ return height;}
    
    public int GetWidth(){ return width;}
    
    public void Komentarz_smierci(char ginacy) {
    	//obsługa komentarza śmierci danego osobnika 
    	//switch na podstawie znaku okreslającego zwierzę
        switch (ginacy) {
        case 'j': System.out.println("Właśnie zginęła jeż"); 	break;
        case 'l': System.out.println("Właśnie zginął leniwiec"); 	break;
        case 'o': System.out.println("Właśnie zginęła owca"); 	break;
        case 'w': System.out.println("Właśnie zginął wilk"); 	break;
        case 'z': System.out.println("Właśnie zginął żółw"); 	break;
    }
    }
    }
  



