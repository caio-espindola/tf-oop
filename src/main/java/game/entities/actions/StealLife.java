package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class StealLife implements Action {

    public final int cost = 30;
    public static String name = "Steal Life";

    public boolean act(Battle b, Entity user, Entity target) {

        if (user == target){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
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
        target.takeDamage(amount);

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }
}
