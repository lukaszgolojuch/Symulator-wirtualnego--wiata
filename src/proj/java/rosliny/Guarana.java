	package proj.java.rosliny;

import proj.java.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Guarana extends Trawa {
    public Guarana(){}
    public Guarana(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    public Guarana(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
        sila = 0;
        inicjatywa = 0;
    }
    @Override
    public char Znak(){
        return 'g';
    }

    @Override
    public Color Color() {
        return Color.red;
    }
    @Override
    protected void Rozmnazanie(List<Kierunki>chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case GORA: world.toAdd(new Guarana(world, new Wspolrzedne(pozycja.x,pozycja.y-1))); 	break;
                case DOL: world.toAdd(new Guarana(world, new Wspolrzedne(pozycja.x,pozycja.y+1)));  break;
                case PRAWO: world.toAdd(new Guarana(world, new Wspolrzedne(pozycja.x+1,pozycja.y)));  break;
                case LEWO: world.toAdd(new Guarana(world, new Wspolrzedne(pozycja.x-1,pozycja.y)));  break;
            }
        }
    }
    @Override
    public void Kolizja(Organizmy collider){
        collider.sila+=3; //osobnik który ją zje otrzymuje +3 do siły
        super.Kolizja(collider);
    }
}
