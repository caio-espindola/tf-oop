package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.entities.effects.MachineLearning;
import game.levels.Battle;

public class Mahoraga extends Cleric{

    protected int HP_INCREASE(){
        return 0;
    }

    protected int MANA_INCREASE(){
        return 0;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Attack();
            case 1:
                return new Heal();
            case 2:
                return new Hex();
            case 3:
                return new Learn();
            default:
                return null;
        }

    }

    protected Stats STARTING_STATS(){
        return new Stats(100, 100, 100, 100);
    }

    public boolean action(Battle b){

    }

    public boolean levelUp(Stats alloc){

        this.setLevel(this.getLevel() + 1);

        Stats aux = this.getStats();

        aux.setSTR(aux.getSTR() + alloc.getSTR());
        aux.setDEX(aux.getDEX() + alloc.getDEX());
        aux.setINT(aux.getINT() + alloc.getINT());
        aux.setPIE(aux.getPIE() + alloc.getPIE());

        return true;

    }

    public Mahoraga(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Claude Mythos";

        this.addEffect(new MachineLearning(-1));

    }
    
}
