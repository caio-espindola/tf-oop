package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class Heal extends Action {

    public final int cost = 15;

    public Heal() {
        super("Heal",
                "Heal the wounds of an ally, or your own. Based on PIE."
        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (!on_same_side){
            throw new InvalidTargetException("Cannot be used on an enemy");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        int heal_amount = user.getStats().getPIE() / 3;
        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        heal_amount = (int) Math.floor(r * heal_amount);

        target.takeDamage(-1 * heal_amount);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
    
}
