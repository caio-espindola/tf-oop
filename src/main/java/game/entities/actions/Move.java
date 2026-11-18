package game.entities.actions;

import game.entities.Entity;
import game.exceptions.InvalidTargetException;
import game.levels.Battle;

public class Move extends Action {


    public Move() {
        super("Move",
                "Move to a different position. Some attacks can only be used when next to the enemy, or only against nearby enemies."
        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException {

        Battle.Position t_pos = b.find(target);

        if (t_pos == null){
            throw new InvalidTargetException("Target not found");
        }

        return b.move(user, b.find(target));

    }
}
