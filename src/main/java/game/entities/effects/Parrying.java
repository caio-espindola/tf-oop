package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Parrying extends Effect {

    public Parrying(int duration){
        super(duration);

        this.name = "Parrying";
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
