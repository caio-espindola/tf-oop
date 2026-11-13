package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Smite implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean user_on_back = b.find(user).onBack();
        boolean target_on_back = b.find(target).onBack();

        if (on_same_side || user_on_back){
            return false;
        }

        int user_pie = user.getStats().getPIE();
        int target_pie = target.getStats().getPIE();
        int target_def = target.getDefense();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        if (target_on_back){
            r -= 0.25;
        }

        double atk = user_pie * r;
        double ac = target_def + target_pie;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        target.takeDamage(dmg);

        return true;

    }
    
}
