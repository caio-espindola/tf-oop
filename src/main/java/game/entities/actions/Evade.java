package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Evading;
import game.levels.Battle;

public class Evade implements Action{

    public boolean act(Battle b, Entity user, Entity target) {

        if (user != target){
            return false;
        }

        return user.addEffect(new Evading(1));
    }
}
