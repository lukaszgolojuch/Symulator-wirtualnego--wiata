package proj.java.zwierzeta;

import proj.java.*;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk() {}
    public Wilk(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    
    public Wilk(Swiat _world, Wspolrzedne _pos){
       super(_world, _pos);
       sila = 9;
       inicjatywa = 5;
    }
    @Override
    public char Znak(){
        return 'w';
    }

    @Override
    public Color Color() {
        return Color.GRAY;
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
                        world.toAdd(new Wilk(world, new Wspolrzedne(pozycja.x,pozycja.y-1)));
                        break;
                    case DOL:
                        world.toAdd(new Wilk(world, new Wspolrzedne(pozycja.x,pozycja.y+1)));
                        break;
                    case PRAWO:
                        world.toAdd(new Wilk(world, new Wspolrzedne(pozycja.x+1,pozycja.y)));
                        break;
                    case LEWO:
                        world.toAdd(new Wilk(world, new Wspolrzedne(pozycja.x-1,pozycja.y)));
                        break;
                }
            }
        }
    }
}
