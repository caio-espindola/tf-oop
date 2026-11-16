package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Cowardly extends Effect {

    public Cowardly(int duration){

        super(duration);

        this.name = "Cowardly";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        return true;
    }

    public boolean apply(Battle b, Entity e){
        double hp_percent = (double) e.getCurrentHP() / e.getMaxHP();

        boolean escape = Math.random() > hp_percent;

        if (escape){
            return b.kill(e);
        }

        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {

    }

}
