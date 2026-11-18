package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class StealLife extends Action {

    public final int cost = 30;

    public StealLife() {
        super("Steal Life",
                "Steal health from an enemy or ally, dealing damage while healing yourself. Based on INT"
        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        if (user == target){
            throw new InvalidTargetException("Can only be used on another character");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        int user_int = user.getStats().getINT();
        int target_pie = target.getStats().getPIE();
        int target_def = target.getDefense();

        int amount = user_int - target_pie - target_def;

        if (amount < 0){
            amount = 0;
        }

        amount = (int) Math.floor(amount * r);

        if (amount > target.getCurrentHP()){
            amount = target.getCurrentHP();
        }

        user.takeDamage(-1 * amount);
        target.takeDamage(amount); // no mana cost if target killed?

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
}
