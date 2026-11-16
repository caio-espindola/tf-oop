package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Thief extends Effect {

    public Thief(int duration){

        super(duration);

        this.name = "Thief";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        return true;
    }

    public boolean apply(Battle b, Entity e){
        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {

    }

}
