package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Attack implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        boolean user_on_front = b.find(user).onFront();
        Battle.Position t_pos = b.find(target);
        boolean target_on_front = t_pos.onFront();
        boolean target_on_top = t_pos.onTop();
        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            return false;
        }

        if (!(user_on_front && target_on_front)){
            return false;
        }

        int user_str = user.getStats().getSTR();
        int target_str = target.getStats().getSTR();
        int target_def = target.getDefense();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_str * r;
        double ac = target_def + target_str;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        target.takeDamage(dmg);

        return true;
    }

}
