package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Focus implements Action {

    public final int cost = 10;
    public static String name = "Focus";

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        int user_dex = user.getStats().getDEX();
        int target_dex = target.getStats().getDEX();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = user_dex * r;
        double ac = target_dex;

        int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));

        target.takeDamage(dmg);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
    
}
