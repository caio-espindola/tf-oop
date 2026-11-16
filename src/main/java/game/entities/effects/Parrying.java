package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Parrying extends Effect {

    public Parrying(int duration){
        super(duration);

        this.name = "Parrying";
    }

    public boolean apply(Battle b, Entity e){

    }
}
