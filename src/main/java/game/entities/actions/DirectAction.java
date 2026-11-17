package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Evading;
import game.entities.effects.Parrying;

public abstract class DirectAction extends Action {

    public DirectAction(String name, String description) {
        super(name, description);
    }

    public static boolean checkEvasion(Entity e){

        if (! (e.hasEffect(new Evading(0)))){
            return false;
        }

        return Math.random() < 0.5;
    }

    public static boolean checkParry(Entity e){

        if (! (e.hasEffect(new Parrying(0)))){
            return false;
        }

        return Math.random() < 0.5;
    }

}
