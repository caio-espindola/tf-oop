package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.Action;
import game.entities.actions.Attack;
import game.entities.actions.Block;
import game.entities.actions.BoogieWoogie;
import game.entities.actions.Parry;
import game.levels.Battle;

public class Warrior extends Hero {

    protected int HP_INCREASE(){
        return 100;
    }

    protected int MANA_INCREASE(){
        return 10;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Attack();
            case 1:
                return new Block();
            case 2:
                return new Parry();
            case 3:
                return new BoogieWoogie();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
         return new Stats(120, 60, 10, 10);
    }

    public boolean action(Battle b){

    }

    public Warrior(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Warrior";

    }

}
