package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.Attack;
import game.entities.actions.Block;
import game.entities.effects.Undying;
import game.levels.Battle;

public class Skeleton extends Creature {

    public boolean action(Battle b){

    }

    public Skeleton(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Syntax Skeleton";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Block());
        this.setSkill(2, null);
        this.setSkill(3, null);

        this.addEffect(new Undying(-1));

        Stats aux = this.getStats();

        aux.setSTR(30 * level);
        aux.setDEX(30 * level);
        aux.setINT(10 * level);
        aux.setPIE(0);
    }

}
