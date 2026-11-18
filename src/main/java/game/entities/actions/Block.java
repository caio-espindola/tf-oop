package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Blocking;
import game.exceptions.InvalidTargetException;
import game.levels.Battle;

public class Block extends EffectAction {

    public Block() {
        super("Block",
                "Block the enemy's next attack, increasing your defense. Based on STR.");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException {

        if (user != target){
            throw new InvalidTargetException("Can only be used on this character");
        }

        return user.addEffect(new Blocking(1));

    }

}
