package proj.java;
import proj.java.zwierzeta.*;
import proj.java.rosliny.*;

import java.util.Random;


public class Main {
	
	static int Rozmiar = 20; //rozmiar okienka


    public static void main(String[] args) {

     Swiat mapka = new Swiat(Rozmiar,Rozmiar);
     Generowanie_organizmow(mapka);

     mapka.Swiat_rysuj();

    }
    

    private static void Generowanie_organizmow(Swiat mapka) {
        Random gen = new Random();
        int ilosc_organizmow = gen.nextInt(Rozmiar*Rozmiar);//maksymalna ilość osobników
        										   //jest ilością pól na planszy
        for(int i = 0; i < ilosc_organizmow; i++) {
        	//dla każdego orgaznizmu generujemy wsp. x y jego położenia
            int x = gen.nextInt(Rozmiar); 
            int y = gen.nextInt(Rozmiar);

                int org = gen.nextInt(9); //losujemy jaki organizm dodamy
                switch(org){
                    case 0: mapka.Add(new Jez(mapka, new Wspolrzedne(x,y))); break;
                    case 1: mapka.Add(new Leniwiec(mapka, new Wspolrzedne(x,y)));break;
                    case 2: mapka.Add(new Trawa(mapka, new Wspolrzedne(x,y)));break;
                    case 3: mapka.Add(new Guarana(mapka, new Wspolrzedne(x,y)));break;
                    case 4: mapka.Add(new Owca(mapka, new Wspolrzedne(x,y)));break;
                    case 5: mapka.Add(new Zolw(mapka, new Wspolrzedne(x,y)));break;
                    case 6: mapka.Add(new Wilk(mapka, new Wspolrzedne(x,y)));break;
                    case 7: mapka.Add(new WilczaJagoda(mapka, new Wspolrzedne(x,y)));break;
                }
            
        }
    }



}
