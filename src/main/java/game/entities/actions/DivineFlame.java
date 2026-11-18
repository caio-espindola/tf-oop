package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Terminated;
import game.exceptions.InvalidActionException;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
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

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException, InvalidActionException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));

        if (! on_same_side){
            throw new InvalidTargetException("Cannot be used on an ally");
        }

        if (target.hasEffect(new Terminated(0))){
            throw new InvalidActionException("Cannot be used twice on the same target");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);

        target.addEffect(new Terminated(3));
        return true;
    }
}
