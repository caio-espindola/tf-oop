package game.entities.creatures;

import game.entities.Entity;
import game.entities.Stats;
import game.entities.actions.Action;
import game.levels.Battle;

public abstract class Creature extends Entity {

    protected abstract Stats STARTING_STATS(int l);
    protected abstract Action SKILLS(int i);

    public abstract void action(Battle b);

    public Creature(int max_hp, int max_mana, int defense, int level){
        
        super(max_hp, max_mana, defense, level);

        for (int i = 0; i < 4; i++){
            this.setSkill(i, SKILLS(i));
        }

        this.setStats(STARTING_STATS(level));

        this.alive = true;
        
    }

}
