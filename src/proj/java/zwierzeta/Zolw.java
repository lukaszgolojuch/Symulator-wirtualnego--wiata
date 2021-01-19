package proj.java.zwierzeta;

import proj.java.*;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(){}
    public Zolw(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    public Zolw(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
        sila = 4;
        inicjatywa = 4;
    }
    @Override
    public char Znak(){
        return 'o';
    }

    @Override
    public Color Color() {
        return Color.WHITE;
    }
    
    public void Akcja(){
    	this.atak_jez--;

    	Kierunki dir = NewDir();
    	
        Random gen = new Random();
        int move_chance = gen.nextInt(100);

        if(move_chance < 25)
        {
        	if(dir == Kierunki.GORA && pozycja.y >0 ||
                    dir == Kierunki.DOL && pozycja.y < world.GetHeight()-1 ||
                    dir == Kierunki.LEWO && pozycja.x > 0 ||
                    dir == Kierunki.PRAWO && pozycja.x < world.GetWidth()-1){
                    if(Next(dir,1) instanceof Ziemia){
                    	Ruch(dir);
                    }
                    else{
                        Next(dir,1).Kolizja(this);
                        if(Next(dir,1) instanceof Ziemia) Ruch(dir);
                    }
                }
        }
        
    }
    
    public void Kolizja(Organizmy collider) {
    	if(collider.sila > 5)
    	{
            if(collider.Znak() == this.Znak()){
                System.out.println("WOW! Doszło do rozmnożenia!!!");
            	Rozmnazanie();
            }
            else if (collider.sila > this.sila){
                world.Komentarz_smierci(this.Znak());
                if(this.Znak() == 'j')
                {
                	collider.atak_jez = 2;
                }
                world.board[pozycja.y][pozycja.x]=new Ziemia();
            }
            else{
            	if(collider.Znak() == 'j')
                {
                	this.atak_jez = 2;
                }
                world.Komentarz_smierci(this.Znak());
                world.board[collider.pozycja.y][collider.pozycja.x]=new Ziemia();
            }
    	}
    }
    
    protected void Rozmnazanie(){
    	Kierunki dir = RandDir();

        if (dir == Kierunki.GORA && pozycja.y > 0 ||
                dir == Kierunki.DOL && pozycja.y < (world.GetHeight() - 1) ||
                dir == Kierunki.PRAWO && pozycja.x < (world.GetWidth() - 1) ||
                dir == Kierunki.LEWO && pozycja.x > 0) {
            if (Next(dir, 1) instanceof Ziemia) {
                switch (dir) {
                    case GORA:
                        world.toAdd(new Owca(world, new Wspolrzedne(pozycja.x,pozycja.y-1)));
                        break;
                    case DOL:
                        world.toAdd(new Owca(world, new Wspolrzedne(pozycja.x,pozycja.y+1)));
                        break;
                    case PRAWO:
                        world.toAdd(new Owca(world, new Wspolrzedne(pozycja.x+1,pozycja.y)));
                        break;
                    case LEWO:
                        world.toAdd(new Owca(world, new Wspolrzedne(pozycja.x-1,pozycja.y)));
                        break;
                }
            }
        }
    }

}
