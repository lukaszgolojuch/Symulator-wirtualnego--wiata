package proj.java.zwierzeta;

import proj.java.*;

import java.awt.*;
import java.util.Random;

public class Leniwiec extends Zwierze {
	public boolean czy_ruch;
    public Leniwiec(){}
   /* public Leniwiec(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    } */
   public Leniwiec(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
        sila = 2;
        inicjatywa = 1;
        czy_ruch = true;
    }
    @Override
    public char Znak(){
        return 'l';
    }

    @Override
    public Color Color() {
        return Color.YELLOW;
        
    }
    @Override
    protected void Rozmnazanie(){
    	Kierunki dir = RandDir();

        if (dir == Kierunki.GORA && pozycja.y > 0 ||
                dir == Kierunki.DOL && pozycja.y < (world.GetHeight() - 1) ||
                dir == Kierunki.PRAWO && pozycja.x < (world.GetWidth() - 1) ||
                dir == Kierunki.LEWO && pozycja.x > 0) {
            if (Next(dir, 1) instanceof Ziemia) {
                switch (dir) {
                    case GORA:
                        world.toAdd(new Leniwiec(world, new Wspolrzedne(pozycja.x,pozycja.y-1)));
                        break;
                    case DOL:
                        world.toAdd(new Leniwiec(world, new Wspolrzedne(pozycja.x,pozycja.y+1)));
                        break;
                    case PRAWO:
                        world.toAdd(new Leniwiec(world, new Wspolrzedne(pozycja.x+1,pozycja.y)));
                        break;
                    case LEWO:
                        world.toAdd(new Leniwiec(world, new Wspolrzedne(pozycja.x-1,pozycja.y)));
                        break;
                }
            }
        }
    }
    @Override
    public void Akcja(){
    	Kierunki dir= NewDir();
    	if(czy_ruch)
    	{
    		czy_ruch = false;
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
    	else
    	{
    		czy_ruch = true;
    	}
    }
    @Override
    protected Kierunki NewDir(){
        Random gen = new Random();
        int temp;
        Kierunki dir = null;
        //left side
        if (pozycja.x == 0 || pozycja.x == 1) {
            //top left corner
            if (pozycja.y == 0) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Kierunki.DOL : Kierunki.PRAWO;
            }
            // bottom left corner
            else if (pozycja.y == world.GetHeight() - 1) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Kierunki.GORA : Kierunki.PRAWO;
            }
            //elsewhere on left side
            else {
                temp = gen.nextInt(3);
                switch (temp) {
                    case 0: dir = Kierunki.GORA; break;
                    case 1: dir = Kierunki.DOL; break;
                    case 2: dir = Kierunki.PRAWO; break;
                }
            }
        }
        //right side
        else if (pozycja.x == world.GetWidth() - 1 || pozycja.x == world.GetWidth()-2) {
            //top right
            if (pozycja.y == 0) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Kierunki.DOL : Kierunki.LEWO;
            }
            //bot right
            else if (pozycja.y == world.GetHeight() - 1) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Kierunki.GORA : Kierunki.LEWO;
            }
            //elsewhere right
            else
            {
                temp = gen.nextInt(3);
                switch (temp) {
                    case 0: dir = Kierunki.GORA; break;
                    case 1: dir = Kierunki.DOL; break;
                    case 2: dir = Kierunki.LEWO; break;
                }
            }
        }
        //top side without corners
        else if (pozycja.y == 0 || pozycja.y==1) {
            temp = gen.nextInt(3);
            switch (temp) {
                case 0: dir = Kierunki.PRAWO; break;
                case 1: dir = Kierunki.DOL; break;
                case 2: dir = Kierunki.LEWO; break;
            }
        }
        //bot side without corners
        else if (pozycja.y == world.GetHeight() - 1 || pozycja.y == world.GetHeight()-2) {
            temp = gen.nextInt(2);
            switch (temp) {
                case 0: dir = Kierunki.GORA; break;
                case 1: dir = Kierunki.PRAWO; break;
                case 2: dir = Kierunki.LEWO; break;
            }
        }
        //middle
        else {
            temp = gen.nextInt(4);
            switch (temp) {
                case 0:
                    dir = Kierunki.GORA;
                    break;
                case 1:
                    dir = Kierunki.DOL;
                    break;
                case 2:
                    dir = Kierunki.LEWO;
                    break;
                case 4:
                    dir = Kierunki.PRAWO;
                    break;
            }
        }
        return dir;
    }
    @Override
    public void Kolizja(Organizmy collider){
        Random gen = new Random();
        int temp = gen.nextInt(2);
      //  world.Log(this.GetSign() + " collides with " + collider.GetSign());
        if(collider instanceof Leniwiec){
        	Rozmnazanie();
        }
        else if (temp == 0){
            if(pozycja.x > 0 && Next(Kierunki.LEWO,1) instanceof Ziemia) Ruch(Kierunki.LEWO);
            else if (pozycja.x < world.GetWidth()-1 && Next(Kierunki.PRAWO, 1) instanceof Ziemia) Ruch(Kierunki.PRAWO);
            else if (pozycja.y > 0 && Next(Kierunki.GORA,1) instanceof Ziemia) Ruch(Kierunki.GORA);
            else if (pozycja.y < world.GetHeight() -1 && Next(Kierunki.DOL,1) instanceof Ziemia) Ruch(Kierunki.DOL);
        }
        else if(collider.sila > this.sila){
            if(this.Znak() == 'j')
            {
            	collider.atak_jez = 2;
            }
            world.board[pozycja.y][pozycja.x] = new Ziemia();
        }
        else 
        {
            world.Komentarz_smierci(collider.Znak());
            if(collider.Znak() == 'j')
            {
            	this.atak_jez = 2;
            }
            world.board[collider.pozycja.y][collider.pozycja.x] = new Ziemia();
        }
    }
}
