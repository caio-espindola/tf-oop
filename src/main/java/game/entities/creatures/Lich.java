package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.Attack;
import game.entities.actions.FurubeYuraYura;
import game.entities.actions.RaiseDead;
import game.entities.actions.StealLife;
import game.entities.effects.Necromancer;
import game.levels.Battle;

public class Lich extends Creature {

    public boolean action(Battle b){

    }

    public Lich(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Null Lich";

        this.setSkill(0, new Attack());
        this.setSkill(1, new RaiseDead());
        this.setSkill(2, new StealLife());
        this.setSkill(3, new );

        this.addEffect(new Necromancer(-1));

        Stats aux = this.getStats();

        aux.setSTR(150);
        aux.setDEX(100);
        aux.setINT(200);
        aux.setPIE(50);

    }

}
