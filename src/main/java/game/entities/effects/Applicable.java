package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

@FunctionalInterface
public abstract interface Applicable {
    
    public abstract boolean apply(Battle b, Entity e);

}
