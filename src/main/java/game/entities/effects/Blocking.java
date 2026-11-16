package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Blocking extends Effect {

    public Blocking(int duration){

        super(duration);

        this.name = "Blocking";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        int e_str = e.getStats().getSTR();

        e.setDefense(e.getDefense() + e_str);

        return true;
    }

    @Override
    public boolean apply(Battle b, Entity e) {
        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {
        int e_str = e.getStats().getSTR();

        e.setDefense(e.getDefense() - e_str);
    }
}
