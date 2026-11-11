package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.Attack;
import game.entities.actions.Evade;
import game.entities.effects.Cowardly;
import game.levels.Battle;

public class Goblin extends Creature {

    public boolean action(Battle b){

    }

    public Goblin(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Glitch Goblin";

        this.setSkill(0, new Attack());
        this.setSkill(1, new Evade());
        this.setSkill(2, null);
        this.setSkill(3, null);

        this.addEffect(new Cowardly(-1));

        Stats aux = this.getStats();

        aux.setSTR(20 * level);
        aux.setDEX(30 * level);
        aux.setINT(10 * level);
        aux.setPIE(0);

    }

}
