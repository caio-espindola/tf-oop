package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class Focus extends DirectAction {

    public final int cost = 10;

    public Focus() {
        super("Focus",
                "Fire an arrow with great accuracy, ignoring armor and avoidance. Based on DEX.");
    }


    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against allies");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        int user_dex = user.getStats().getDEX();
        int target_dex = target.getStats().getDEX();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_dex * r;
        double ac = target_dex;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        if (checkParry(target)){

            return false;

        }

        target.takeDamage(dmg);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
    
}
