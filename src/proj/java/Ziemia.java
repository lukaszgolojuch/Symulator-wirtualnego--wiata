package proj.java;

import java.awt.*;

public class Ziemia extends Organizmy {
    public Ziemia(){}
    public Ziemia(Swiat _world, Wspolrzedne _pos){
        world=_world;
        pozycja=_pos;
    }
    @Override
    public Color Color(){
        return new Color(139,69,19);
    }

    @Override
    public char Znak() {
		return 0;
	}

}
