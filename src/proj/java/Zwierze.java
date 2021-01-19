package proj.java;

import java.awt.*;
import java.util.Random;

public class Zwierze extends Organizmy{
    public Zwierze(){}
    public Zwierze(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    public Zwierze(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
    }
    @Override
    public void Akcja(){
    	this.atak_jez--;
    	Kierunki dir = NewDir();
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
    protected Kierunki NewDir() {
    	Kierunki dir = null;
        Random randGen = new Random();
        int temp;
        //left side
        if (pozycja.x == 0) {
            //top left corner
            if (pozycja.y == 0) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Kierunki.DOL : Kierunki.PRAWO;

            }
            //bottom left corner
            else if (pozycja.y == world.GetHeight() - 1) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Kierunki.GORA : Kierunki.PRAWO;
            }
            //elsewhere on left side
            else {
                temp = randGen.nextInt(3);
                switch (temp) {
                    case 0:
                        dir = Kierunki.GORA;
                        break;
                    case 1:
                        dir = Kierunki.DOL;
                        break;
                    case 2:
                        dir = Kierunki.PRAWO;
                        break;
                }
            }
        }
        //right side
        else if (pozycja.x == world.GetWidth() - 1) {
            //top right
            if (pozycja.y == 0) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Kierunki.DOL : Kierunki.LEWO;
            }
            //bot right
            else if (pozycja.y == world.GetHeight() - 1) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Kierunki.GORA : Kierunki.LEWO;
            }
            //elsewhere right
            else {
                temp = randGen.nextInt(3);
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
                }
            }
        }
        //top side without corners
        else if (pozycja.y == 0) {
            temp = randGen.nextInt(3);
            switch (temp) {
                case 0:
                    dir = Kierunki.PRAWO;
                    break;
                case 1:
                    dir = Kierunki.DOL;
                    break;
                case 2:
                    dir = Kierunki.LEWO;
                    break;
            }
        }
        //bot side without corners
        else if (pozycja.y == world.GetHeight() - 1) {
            temp = randGen.nextInt(3);
            switch (temp) {
                case 0:
                    dir = Kierunki.GORA;
                    break;
                case 1:
                    dir = Kierunki.PRAWO;
                    break;
                case 2:
                    dir = Kierunki.LEWO;
                    break;
            }
        }
        //middle
        else {
            dir=RandDir();
        }
        return dir;
    }
    protected void Ruch(Kierunki dir){
        switch (dir){
            case GORA:
                if(pozycja.y == 0) break;
                world.board[pozycja.y-1][pozycja.x] = world.board[pozycja.y][pozycja.x];
                world.board[pozycja.y][pozycja.x] = new Ziemia();
                pozycja.y--;
                break;
            case DOL:
                if(pozycja.y == world.GetHeight()-1) break;
                world.board[pozycja.y+1][pozycja.x] = world.board[pozycja.y][pozycja.x];
                world.board[pozycja.y][pozycja.x] = new Ziemia();
                pozycja.y++;
                break;
            case LEWO:
                if(pozycja.x == 0) break;
                world.board[pozycja.y][pozycja.x-1] = world.board[pozycja.y][pozycja.x];
                world.board[pozycja.y][pozycja.x] = new Ziemia();
                pozycja.x--;
                break;
            case PRAWO:
                if(pozycja.x == world.GetWidth()-1) break;
                world.board[pozycja.y][pozycja.x+1] = world.board[pozycja.y][pozycja.x];
                world.board[pozycja.y][pozycja.x] = new Ziemia();
                pozycja.x++;
                break;
        }
    }

    @Override
    public char Znak(){
        return 0;
    }
    @Override
    public Color Color(){
       return Color.RED;
    }
    public void Kolizja(Organizmy collider) {
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
            world.Komentarz_smierci(collider.Znak());
            if(collider.Znak() == 'j')
            {
            	this.atak_jez = 2;
            }
            world.board[collider.pozycja.y][collider.pozycja.x]=new Ziemia();
        }
    }
    protected void Rozmnazanie(){
    }

}
