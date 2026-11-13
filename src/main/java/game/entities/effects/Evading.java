package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Evading extends Effect {

    public Evading(int duration) {
        super(duration);

        this.name = "Evading";
    }

    @Override
    public boolean apply(Battle b, Entity e) {

    }
}
