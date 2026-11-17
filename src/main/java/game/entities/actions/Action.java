package game.entities.actions;

import game.levels.Battle;
import game.entities.Entity;

public abstract class Action {

    public final String name, description;

    public Action(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract boolean act(Battle b, Entity user, Entity target);

}
