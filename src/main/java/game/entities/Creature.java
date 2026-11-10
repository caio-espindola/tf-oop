package game.entities;

import game.levels.Battle;
import game.entities.actions.Action;

public abstract class Creature extends Entity {

    public abstract void action(Battle b);

    public Creature(int max_hp, int max_mana, int defense, int level){
        
        super(max_hp, max_mana, defense, level);

        this.alive = true;
        
    }

}
