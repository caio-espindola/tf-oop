package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Shielded extends Effect {

    int pwr;

    public Shielded(int duration, int pwr){
        super(duration);

        this.pwr = pwr;
        this.name = "Shielded";
    }

    @Override
    public boolean set(Battle b, Entity e) {
        e.setDefense(e.getDefense() + this.pwr);
        return true;
    }

    public boolean apply(Battle b, Entity e){
        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {
        e.setDefense(e.getDefense() - this.pwr);
    }
}
