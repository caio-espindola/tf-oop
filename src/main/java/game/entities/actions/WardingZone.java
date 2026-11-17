package game.entities.actions;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.entities.effects.Shielded;
import game.levels.Battle;

public class WardingZone extends EffectAction {

    public final int cost = 50;

    public WardingZone() {
        super("Warding Zone",
                "Cast a protective ward upon all your allies. Based on PIE");
    }

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean on_right_side = b.find(user).onRight();

        if (! on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        Entity[] ally_list = (on_right_side) ? b.getEnemies() : b.getHeroes();

        int user_pie = user.getStats().getPIE();

        for (Entity e : ally_list){
            e.addEffect(new Shielded(user.getLevel(), user_pie));
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
    
}
