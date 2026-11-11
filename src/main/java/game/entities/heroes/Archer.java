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

    protected Action UNLOCKABLE(int i){
        if (i == 0){
            return new Focus();
        } else {
            return new FukumaMizushi();
        }
    }

    public boolean action(Battle b){

    }

    public Archer(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Archer";

        this.setSkill(0, new Arrow());
        this.setSkill(1, new Evade());
        this.setSkill(2, null);
        this.setSkill(3, null);

        Stats aux = this.getStats();

        aux.setSTR(60);
        aux.setDEX(100);
        aux.setINT(30);
        aux.setPIE(10);

    }
}
