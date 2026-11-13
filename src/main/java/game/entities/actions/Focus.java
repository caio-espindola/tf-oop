package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Focus implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            return false;
        }

        int user_dex = user.getStats().getDEX();
        int target_dex = target.getStats().getDEX();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_dex * r;
        double ac = target_dex;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        target.takeDamage(dmg);

        return true;
    }
    
}
