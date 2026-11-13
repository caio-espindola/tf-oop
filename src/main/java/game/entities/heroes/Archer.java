package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.levels.Battle;

public class Archer extends Hero {

    protected int HP_INCREASE(){
        return 80;
    }

    protected int MANA_INCREASE(){
        return 30;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Arrow();
            case 1:
                return new Evade();
            case 2:
                return new Focus();
            case 3:
                return new FukumaMizushi();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
        return new Stats(60, 100, 30, 10);
    }

    public boolean action(Battle b){

    }

    public Archer(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Archer";

    }
}
