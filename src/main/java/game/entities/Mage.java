package game.entities;

import game.entities.actions.MageShield;
import game.entities.actions.MagicMissile;
import game.levels.Battle;

public class Mage extends Hero {

    public boolean action(Battle b){

    }

    public Mage(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Mage";

        this.setSkill(0, new MagicMissile());
        this.setSkill(1, new MageShield());
        this.setSkill(2, null);
        this.setSkill(3, null);

    }

}
