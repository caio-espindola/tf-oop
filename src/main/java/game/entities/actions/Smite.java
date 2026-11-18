package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.exceptions.OutOfRangeException;
import game.levels.Battle;

public class Smite extends DirectAction {

    public final int cost = 10;

    public Smite() {
        super("Smite",
                "Attack the enemy with a surge of holy light. Deals less damage against faraway enemies. Based on PIE.");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException, OutOfRangeException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean user_on_back = b.find(user).onBack();
        boolean target_on_back = b.find(target).onBack();

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against an ally");
        }

        if (user_on_back){
            throw new OutOfRangeException("Can only be used from the front");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
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

        if (checkEvasion(target)){

            return false;

        }

        target.takeDamage(dmg);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
    
}
