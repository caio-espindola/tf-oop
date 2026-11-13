package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Blocking extends Effect {

    public Blocking(int duration){

        super(duration);

        this.name = "Blocking";

    }

    @Override
    public boolean apply(Battle b, Entity e) {

    }
}
