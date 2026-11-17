package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Blocking;
import game.levels.Battle;

public class Block extends EffectAction {

    public Block() {
        super("Block",
                "Block the enemy's next attack, increasing your defense. Based on STR.");
    }

    public boolean act(Battle b, Entity user, Entity target){

        if (user != target){
            return false;
        }

        return user.addEffect(new Blocking(1));

    }

}
