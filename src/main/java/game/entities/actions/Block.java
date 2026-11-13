package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Blocking;
import game.levels.Battle;

public class Block implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        if (user != target){
            return false;
        }

        return user.addEffect(new Blocking(1));

    }

}
