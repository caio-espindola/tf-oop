package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Reduced extends Effect {

    private int pwr, dmg;

    public Reduced(int duration, int pwr) {
        super(duration);
        this.pwr = pwr;
    }

    @Override
    public boolean set(Battle b, Entity e) {

        int target_dex = e.getStats().getDEX();
        int target_def = e.getDefense();

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[

        double atk = pwr * r;
        int ac = target_def + target_dex;

        this.dmg = (int) Math.ceil(atk / Math.pow(ac, 1/3.0));
        return true;

    }

    @Override
    public boolean apply(Battle b, Entity e) {

        if (e.takeDamage(this.dmg)){
            b.kill(e);
        }

        return true;

    }

    @Override
    public void unset(Battle b, Entity e) {

    }
}
