package game.entities.effects;

import game.entities.Entity;
import game.entities.Stats;
import game.entities.heroes.Mahoraga;
import game.levels.Battle;

public class MachineLearning extends Effect {

    public MachineLearning(int duration){
        super(duration);

        this.name = "Machine Learning";
    }

    private int prev_hp;

    @Override
    public boolean set(Battle b, Entity e) {
        return true;
    }

    @Override
    public boolean apply(Battle b, Entity e) {
        int dhp = prev_hp - e.getCurrentHP();
        int dhp_f = dhp / 4;
        Stats alloc = new Stats(dhp_f, dhp_f, dhp_f, dhp_f);

        if (dhp < 0){
            ((Mahoraga) e).levelUp(alloc);
        }

        this.prev_hp = e.getCurrentHP();
        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {

    }
}
