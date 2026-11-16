package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Hexed;
import game.levels.Battle;

public class Hex extends EffectAction {

    public final int cost = 30;
    public static String name = "Hex";

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        if (target.hasEffect(new Hexed(-1))){
            return false;
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
