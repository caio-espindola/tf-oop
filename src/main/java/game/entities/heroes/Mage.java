package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.levels.Battle;

public class Mage extends Hero {

    protected int HP_INCREASE(){
        return 40;
    }

    protected int MANA_INCREASE(){
        return 70;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new MagicMissile();
            case 1:
                return new MageShield();
            case 2:
                return new Hex();
            case 3:
                return new Murasaki();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
        return new Stats(20, 50, 120 ,10);
    }

    public boolean action(Battle b){

    }

    public Mage(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Mage";

    }
}
