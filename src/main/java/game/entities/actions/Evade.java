package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Evade implements Action{

    public boolean act(Battle b, Entity user, Entity target) {
        return false;
    }
}
