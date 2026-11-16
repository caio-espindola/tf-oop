package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Shielded;
import game.levels.Battle;

public class MageShield implements Action {

    public final int cost = 15;
    public static String name = "Mage Shield";

    public boolean act(Battle b, Entity user, Entity target) {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (! on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        if (target.hasEffect(new Shielded(-1, 0))){
            return false;
        }

        int user_int = user.getStats().getINT();

        target.addEffect(new Shielded(user.getLevel(), user_int));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
}
