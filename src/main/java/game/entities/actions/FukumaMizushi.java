package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Reduced;
import game.levels.Battle;

public class FukumaMizushi extends Ultimate {

    public final int cost = 75;

    public FukumaMizushi() {
        super("Parallel Reduce",

                """
                Fire a hail of arrows upon all enemies at once, lasting for 3 turns.\n
                "A truly divine technique."
                """,

                """
                Let me show you what REAL Programming looks like...\n
                Vector Operation:\n
                PARALLEL REDUCE
                """);
    }

    public boolean act(Battle b, Entity user, Entity target){

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean on_left = b.find(user).onLeft();

        if (on_same_side){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        Entity[] foe_list = on_left ? b.getEnemies() : b.getHeroes();

        for (Entity e : foe_list){
            e.addEffect(new Reduced(3, user.getStats().getDEX()));
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }

}
