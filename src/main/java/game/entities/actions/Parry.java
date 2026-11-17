package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Parrying;
import game.levels.Battle;

public class Parry extends EffectAction {

    public final int cost = 10;

    public Parry() {
        super("Parry",
                "Attempt to parry the enemy's next attack, reflecting the damage back at them. Based on STR.");
    }

    public boolean act(Battle b, Entity user, Entity target){

        if (user != target){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        target.addEffect(new Parrying(user.getLevel()));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
        
    }
    
}
