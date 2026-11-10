package game.entities;

import game.entities.actions.Action;
import game.entities.actions.Attack;
import game.entities.actions.Evade;
import game.levels.Battle;

public class Goblin extends Creature {

    public boolean action(Battle b){

    }

    public Goblin(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Glitch Goblin";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Evade());
        this.setSkill(2, null);
        this.setSkill(3, null);

        this.addEffect(new Cowardly(-1));

    }

}
