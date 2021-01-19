package proj.java;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
public class Trawa extends Organizmy {
    public Trawa(){}
    public Trawa(Swiat _world, Wspolrzedne _pozycja, int _sila, int _inicjatywa){
        super(_world,_pozycja,_sila,_inicjatywa);
    }
    public Trawa(Swiat _world, Wspolrzedne _pos){
        super(_world, _pos);
        sila = 0;
        inicjatywa = 0;
    }
    @Override
    public char Znak(){
        return 't';
    }

    @Override
    public Color Color() {
        return new Color(0,100,0);
    }
    @Override
    public void Akcja(){
        Random gen = new Random();
        int temp = gen.nextInt(10);
        if(temp==0){
            List<Kierunki> chances = new LinkedList<>();
            if (pozycja.x == 0) {
                if (pozycja.y == 0) {
                    if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                    if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
                }
                else if (pozycja.y == world.GetHeight() - 1) {
                    if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                    if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
                }
                else {
                    if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                    if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                    if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
                }
            }
            else if (pozycja.x == world.GetWidth() - 1) {
                if (pozycja.y == 0) {
                    if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                    if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                }
                else if (pozycja.y == world.GetHeight() - 1) {
                    if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                    if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                }
                else {
                    if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                    if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                    if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                }
            }
            else if (pozycja.y == 0) {
                if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
            }
            else if (pozycja.y == world.GetHeight() - 1) {
                if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
            }
            else {
                if (world.board[pozycja.y-1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.GORA);
                if (world.board[pozycja.y+1][pozycja.x] instanceof Ziemia) chances.add(Kierunki.DOL);
                if (world.board[pozycja.y][pozycja.x-1] instanceof Ziemia) chances.add(Kierunki.LEWO);
                if (world.board[pozycja.y][pozycja.x+1] instanceof Ziemia) chances.add(Kierunki.PRAWO);
            }
            Rozmnazanie(chances);
        }
    }
    protected void Rozmnazanie(List<Kierunki> chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case GORA:
                    world.toAdd(new Trawa(world, new Wspolrzedne(pozycja.x, pozycja.y - 1)));
                    break;
                case DOL:
                    world.toAdd(new Trawa(world, new Wspolrzedne(pozycja.x, pozycja.y + 1)));
                    break;
                case PRAWO:
                    world.toAdd(new Trawa(world, new Wspolrzedne(pozycja.x + 1, pozycja.y)));
                    break;
                case LEWO:
                    world.toAdd(new Trawa(world, new Wspolrzedne(pozycja.x - 1, pozycja.y)));
                    break;
            }
        }
    }
    @Override
    public void Kolizja(Organizmy collider){
        //world.Log(collider.GetSign() + " eats .");
        world.board[pozycja.y][pozycja.x] = new Ziemia();
    }
}
