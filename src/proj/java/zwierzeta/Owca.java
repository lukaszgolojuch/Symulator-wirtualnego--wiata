package proj.java.zwierzeta;

import proj.java.*;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(){}
    public Owca(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    
    public Owca(Swiat _world, Wspolrzedne _pos){
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
