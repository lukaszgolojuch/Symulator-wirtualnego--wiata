package proj.java;
import java.awt.*;
import java.util.Random;

public abstract class Organizmy {
    public int sila;
    public int inicjatywa;
	public int atak_jez = 0;
    public Swiat world;
    public Wspolrzedne pozycja;
    public Organizmy(){}
    public Organizmy(Swiat _world,Wspolrzedne _coords){
        world = _world;
        pozycja = _coords;
    }
    public Organizmy(Swiat _world, Wspolrzedne _pozycja, int _inicjatywa, int _sila){
        world = _world;
        pozycja = _pozycja;
        sila = _sila;
        inicjatywa = _inicjatywa;
    }
    void Akcja(){}
    public void Kolizja(Organizmy collider){
        //world.Log(this.GetSign() + " collides with " + collider.GetSign());
        if(collider.sila > this.sila){
            System.out.println(collider.Znak() + " zabił " + this.Znak());
            world.board[pozycja.y][pozycja.x] = new Ziemia();
        }
        else {
            System.out.println(this.Znak() + " zabił " + collider.Znak());

        }
    }
    abstract public Color Color();
    abstract public char Znak();
    protected Kierunki RandDir(){
    	Kierunki dir = null;
        Random randGen = new Random();
        int temp = randGen.nextInt(4);
        switch(temp){
            case 0:
                dir = Kierunki.GORA;
                break;
            case 1:
                dir = Kierunki.DOL;
                break;
            case 2:
                dir = Kierunki.LEWO;
                break;
            case 3:
                dir = Kierunki.PRAWO;
                break;
        }
        return dir;
    }
    protected Organizmy Next(Kierunki dir, int distance){
        switch (dir) {
            case GORA:
                return world.board[pozycja.y - distance][pozycja.x];
            case DOL:
                return world.board[pozycja.y + distance][pozycja.x];
            case PRAWO:
                return world.board[pozycja.y][pozycja.x + distance];
            case LEWO:
                return world.board[pozycja.y][pozycja.x - distance];

        }
        return this;//never will be return, just for disabling errors from intelliJ
    }
}
