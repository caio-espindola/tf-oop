package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Hexed;
import game.exceptions.InvalidActionException;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class Hex extends EffectAction {

    public final int cost = 30;

    public Hex() {
        super("Hex",
                "Cast a curse upon the enemy, permanently reducing their health and mana. Based on INT");
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException, InvalidActionException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against an ally");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        if (target.hasEffect(new Hexed(-1))){
            throw new InvalidActionException("Cannot be used twice on the same target");
        }

        target.addEffect(new Hexed(-1));

        double intelligence_ratio = ((double) user.getStats().getINT()) / (target.getStats().getINT());

        intelligence_ratio += 0.5;

        if (intelligence_ratio < 1){
            intelligence_ratio = 1;
        }

        target.setMaxHP((int) Math.ceil(target.getMaxHP() / intelligence_ratio));
        target.setMaxMana((int) Math.ceil(target.getMaxMana() / intelligence_ratio));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
    
}
