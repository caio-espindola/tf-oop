package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Evading;
import game.entities.effects.Feinting;
import game.levels.Battle;

public class Feint implements Action {

    public static String name = "Feint";

    public boolean act(Battle b, Entity user, Entity target) {

        if (user != target){
            return false;
        }

        return user.addEffect(new Feinting(2));

    }
}
