package game.entities.creatures;

import game.entities.actions.Attack;
import game.entities.actions.FireBreath;
import game.entities.actions.Wind;
import game.entities.effects.Fearsome;
import game.levels.Battle;

public class Dragon extends Creature {

    public boolean action(Battle b){

    }

    public Dragon(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Data Dragon";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Wind());
        this.setSkill(2, new FireBreath());
        this.setSkill(3, null);

        this.addEffect(new Fearsome(-1));

    }

}
