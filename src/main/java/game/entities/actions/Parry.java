package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Parrying;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class Parry extends EffectAction {

    public final int cost = 10;

    public Parry() {
        super("Parry",
                "Attempt to parry the enemy's next attack, reflecting the damage back at them. Based on STR.");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        if (user != target){
            throw new InvalidTargetException("Can only be used on this character");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        target.addEffect(new Parrying(user.getLevel()));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
        
    }
    
}
