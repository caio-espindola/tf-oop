package game.entities.actions;

import game.levels.Battle;
import game.entities.Entity;

@FunctionalInterface
public abstract interface Action {

    public abstract boolean act(Battle b, Entity user, Entity target);

}
