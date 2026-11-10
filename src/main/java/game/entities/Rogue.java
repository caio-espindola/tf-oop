package game.entities;

import game.levels.Battle;
import game.entities.actions.Attack;
import game.entities.actions.Evade;
import game.entities.actions.Feint;
import game.entities.effects.Thief;

public class Rogue extends Creature {
    
    public boolean action(Battle b){

    }

    public Rogue(int max_hp, int max_mana, int defense, int level){
        
        super(max_hp, max_mana, defense, level);

        this.name = "RAM Rogue";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Evade());
        this.setSkill(2, new Feint());
        this.setSkill(3, null);

        this.addEffect(new Thief(-1));
    }

}
