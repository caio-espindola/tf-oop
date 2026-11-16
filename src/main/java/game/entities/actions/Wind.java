package game.entities.actions;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.levels.Battle;

public class Wind implements Action {

    public final int cost = 25;
    public static String name = "Wind";

    public boolean act(Battle b, Entity user, Entity target) {

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean on_right_side = b.find(user).onRight();

        if (on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        int foe_count = 0;
        Entity[] foe_list = (on_right_side) ? b.getHeroes() : b.getEnemies();

        for (Entity e : foe_list){
            if (e != null && !(e instanceof PlaceHolder)){
                foe_count++;
            }
        }

        int user_dex = user.getStats().getDEX();

        for (Entity e : foe_list){
            int ac = e.getDefense();

            e.setCurrentMana(e.getCurrentMana() - (user_dex - ac));
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
}
