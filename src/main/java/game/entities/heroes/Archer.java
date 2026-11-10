package game.entities.heroes;

import game.entities.actions.*;
import game.levels.Battle;

public class Archer extends Hero {

    public boolean action(Battle b){

    }

    public Archer(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Archer";

        this.setSkill(0, new Arrow());
        this.setSkill(1, new Evade());
        this.setSkill(2, null);
        this.setSkill(3, null);

    }

    public void levelUp(){

        this.setLevel(this.getLevel() + 1);

        if (this.getLevel() == 2){

            this.setSkill(2, new Focus());

        } else if (this.getLevel() == 3){

            this.setSkill(3, new FukumaMizushi());

        }

    }

}
