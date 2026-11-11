package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.levels.Battle;

public class Cleric extends Hero {

    protected int HP_INCREASE(){
        return 60;
    }

    protected int MANA_INCREASE(){
        return 50;
    }

    protected Action UNLOCKABLE(int i){
        if (i == 0){
            return new WardingZone();
        } else {
            return new FurubeYuraYura();
        }
    }

    public boolean action(Battle b){

    }

    public Cleric(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Cleric";

        this.setSkill(0, new Smite());
        this.setSkill(1, new Heal());
        this.setSkill(2, null);
        this.setSkill(3, null);

        Stats aux = this.getStats();

        aux.setSTR(40);
        aux.setDEX(40);
        aux.setINT(20);
        aux.setPIE(100);

    }
}
