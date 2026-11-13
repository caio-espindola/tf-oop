package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.*;
import game.entities.effects.Fearsome;
import game.levels.Battle;

public class Dragon extends Creature {

    protected Stats STARTING_STATS(int l){
        return new Stats(50*l, 20*l, 30*l, 10*l);
    }

    protected Action SKILLS(int i){
        switch (i){
            case 0:
                return new Attack();
            case 1:
                return new Wind();
            case 2:
                return new FireBreath();
            default:
                return null;
        }
    }

    public boolean action(Battle b){

    }

    public Dragon(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Data Dragon";

        this.addEffect(new Fearsome(-1));

    }

}
