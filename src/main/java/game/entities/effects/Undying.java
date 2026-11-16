package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Undying extends Effect {

    public Undying(int duration){

        super(duration);

        this.name = "Undying";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        return true;
    }

    public boolean apply(Battle b, Entity e){
        double hp_percent = (double) e.getCurrentHP() / e.getMaxHP();

        if (hp_percent < 0.2){
            e.setCurrentHP(e.getMaxHP());
            return e.removeEffect(this);
        }

        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {

    }

}
