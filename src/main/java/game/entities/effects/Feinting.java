package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Feinting extends Effect {

    public Feinting(int duration){
        super(duration);

        this.name = "Feinting";
    }

    public boolean apply(Battle b, Entity e){

    }
}
