package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Terminated;
import game.levels.Battle;

public class DivineFlame extends Ultimate {

    public final int cost = 50;

    public DivineFlame() {
        super("Kill Process",

                """
                Place a death curse upon an enemy, killing them after 3 turns.
                "With no regard for your attributes, as long as you exist within the memory, that existence can be deleted."
                """,

                """
                Terminal:\n
                ...Open!
                """);
    }

    public boolean act(Battle b, Entity user, Entity target) {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (! on_same_side){
            return false;
        }

        if (target.hasEffect(new Terminated(0))){
            return false;
        }

        if (user.getCurrentMana() < this.cost){
            return false;
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);

        target.addEffect(new Terminated(3));
        return true;
    }
}
