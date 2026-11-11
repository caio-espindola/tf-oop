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

    protected Action UNLOCKABLE(int i){
        if (i == 0){
            return new Parry();
        } else {
            return new BoogieWoogie();
        }
    }

    public boolean action(Battle b){

    }

    public Warrior(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Warrior";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Block());
        this.setSkill(2, null);
        this.setSkill(3, null);

        Stats aux = this.getStats();

        aux.setSTR(120);
        aux.setDEX(60);
        aux.setINT(10);
        aux.setPIE(10);

    }

}
