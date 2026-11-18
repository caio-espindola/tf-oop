package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Evading;
import game.exceptions.InvalidTargetException;
import game.levels.Battle;

public class Evade extends EffectAction {


    public Evade() {
        super("Evade",
                "Increase your avoidance for the next turn, giving you a 50% chance to ignore physical attacks");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException {

        if (user != target){
            throw new InvalidTargetException("Can only be used on this character");
        }

        return user.addEffect(new Evading(1));
    }
}
