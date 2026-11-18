package game.entities.actions;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class FireBreath extends Action {

    public final int cost = 45;

    public FireBreath() {
        super("Fire Breath",
                "Breath fire unto your enemies, hitting them all at once. Based on INT and STR"
        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean on_right_side = b.find(user).onRight();

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used on allies");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        int foe_count = 0;
        Entity[] foe_list = (on_right_side) ? b.getHeroes() : b.getEnemies();

        for (Entity e : foe_list){
            if (e != null && !(e instanceof PlaceHolder)){
                foe_count++;
            }
        }

        int user_int = user.getStats().getINT();
        int user_str = user.getStats().getSTR();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[
        int atk = (int) Math.floor(user_int + user_str * r / foe_count);


        for (Entity e : foe_list){

            int ac = e.getDefense() + e.getStats().getSTR();
            int dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));
            e.takeDamage(dmg);

        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
}
