package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.*;
import game.entities.effects.Necromancer;
import game.levels.Battle;

public class Lich extends Creature {

    protected Stats STARTING_STATS(int l){
        return new Stats(150, 100, 200, 50);
    }

    protected Action SKILLS(int i){
        switch (i){
            case 0:
                return new Attack();
            case 1:
                return new RaiseDead();
            case 2:
                return new StealLife();
            case 3:
                return new ;
            default:
                return null;
        }
    }

    public boolean action(Battle b){

    }

    public Lich(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Null Lich";

        this.addEffect(new Necromancer(-1));

    }

}
