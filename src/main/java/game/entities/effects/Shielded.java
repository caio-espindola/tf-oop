package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Shielded extends Effect {

    int pwr;

    public Shielded(int duration, int pwr){
        super(duration);

        this.pwr = pwr;
        this.name = "Shielded";
    }

    public boolean apply(Battle b, Entity e){

    }
}
