package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class MagicMissile extends DirectAction {

    public final int cost = 10;

    public MagicMissile() {
        super("Magic Missile",
                "Launch a magical strike against an enemy. Can hit targets from afar. based on INT");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against an ally");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        int user_int = user.getStats().getINT();
        int target_int = target.getStats().getINT();
        int target_def = target.getDefense();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_int * r;
        double ac = target_def + target_int;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        if (checkEvasion(target)){

            return false;

        } else if (checkParry(target)){

            return false;

        }

        target.takeDamage(dmg);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
}
