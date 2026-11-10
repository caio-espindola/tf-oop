package game.entities.heroes;

import game.entities.actions.*;
import game.levels.Battle;

public class Cleric extends Hero {

    public boolean action(Battle b){

    }

    public Cleric(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Cleric";

        this.setSkill(0, new Smite());
        this.setSkill(1, new Heal());
        this.setSkill(2, null);
        this.setSkill(3, null);

    }

    public void levelUp(){

        this.setLevel(this.getLevel() + 1);

        if (this.getLevel() == 2){

            this.setSkill(2, new WardingZone());

        } else if (this.getLevel() == 3){

            this.setSkill(3, new FurubeYuraYura());

        }

    }

}
