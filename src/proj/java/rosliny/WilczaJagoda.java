package proj.java.rosliny;

import proj.java.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class WilczaJagoda extends Trawa {
    public WilczaJagoda(){}
    public WilczaJagoda(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    public WilczaJagoda(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
        sila = 0;
        inicjatywa = 0;
    }
    @Override
    public char Znak(){
        return 'i';
    }

    @Override
    public Color Color() {
        return Color.blue;
    }
    @Override
    protected void Rozmnazanie(List<Kierunki> chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case GORA: world.toAdd(new WilczaJagoda(world, new Wspolrzedne(pozycja.x,pozycja.y-1))); 	break;
                case DOL: world.toAdd(new WilczaJagoda(world, new Wspolrzedne(pozycja.x,pozycja.y+1)));  break;
                case PRAWO: world.toAdd(new WilczaJagoda(world, new Wspolrzedne(pozycja.x+1,pozycja.y)));  break;
                case LEWO: world.toAdd(new WilczaJagoda(world, new Wspolrzedne(pozycja.x-1,pozycja.y)));  break;
            }
        }
    }
    @Override
    public void Kolizja(Organizmy collider){
        world.board[pozycja.y][pozycja.x] = new Ziemia();
        world.board[collider.pozycja.y][collider.pozycja.x] = new Ziemia();
    }
}
