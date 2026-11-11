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

    protected Action UNLOCKABLE(int i){
        if (i == 0){
            return new Hex();
        } else {
            return new Murasaki();
        }
    }

    public boolean action(Battle b){

    }

    public Mage(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Mage";

        this.setSkill(0, new MagicMissile());
        this.setSkill(1, new MageShield());
        this.setSkill(2, null);
        this.setSkill(3, null);

        Stats aux = this.getStats();

        aux.setSTR(20);
        aux.setDEX(50);
        aux.setINT(120);
        aux.setPIE(10);

    }
}
