package game.entities.heroes;

import game.entities.actions.Heal;
import game.entities.actions.Smite;
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

}
