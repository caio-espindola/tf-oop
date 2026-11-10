package game.entities.heroes;

import game.entities.actions.Attack;
import game.entities.actions.Block;
import game.levels.Battle;

public class Warrior extends Hero {

    public boolean action(Battle b){

    }

    public Warrior(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Warrior";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Block());
        this.setSkill(2, null);
        this.setSkill(3, null);

    }

}
