package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Heal implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (!on_same_side){
            return false;
        }

        int heal_amount = user.getStats().getPIE() / 3;
        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        heal_amount = (int) Math.floor(r * heal_amount);

        target.takeDamage(-1 * heal_amount);

        return true;
    }
    
}
