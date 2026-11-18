package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.OutOfRangeException;
import game.levels.Battle;

public class Attack extends DirectAction {


    public Attack() {
        super("Attack",
                "Attack an enemy with your weapon. Based on STR. Can only be used from the front. Can only reach enemies in the front.");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, OutOfRangeException {

        boolean user_on_front = b.find(user).onFront();
        Battle.Position t_pos = b.find(target);
        boolean target_on_front = t_pos.onFront();
        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against an ally");
        }

        if (! user_on_front){
            throw new OutOfRangeException("Can only be used from the front");
        }

        if (! target_on_front){
            throw new OutOfRangeException("Can only reach enemies in the front");
        }

        int user_str = user.getStats().getSTR();
        int target_str = target.getStats().getSTR();
        int target_def = target.getDefense();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_str * r;
        double ac = target_def + target_str;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        if (checkEvasion(target)){

            return false;

        } else if (checkParry(target)){

            double str_ratio = Math.min(target_str / user_str, 1);

            dmg = (int) Math.floor(dmg * (str_ratio));
            user.takeDamage(dmg);
            return false;

        }

        target.takeDamage(dmg);

        return true;
    }

}
