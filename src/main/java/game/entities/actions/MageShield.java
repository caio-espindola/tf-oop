package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Shielded;
import game.exceptions.InvalidActionException;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class MageShield extends EffectAction {

    public final int cost = 15;

    public MageShield() {
        super("Mage Shield",
                "Cast a magical shield around yourself or an ally, granting protection. Based on INT.");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException, InvalidActionException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (! on_same_side){
            throw new InvalidTargetException("Cannot be used on an enemy");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        if (target.hasEffect(new Shielded(-1, 0))){
            throw new InvalidActionException("Cannot be used twice while still active");
        }

        int user_int = user.getStats().getINT();

        target.addEffect(new Shielded(user.getLevel(), user_int));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
}
