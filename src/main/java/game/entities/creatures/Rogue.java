package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.Action;
import game.levels.Battle;
import game.entities.actions.Attack;
import game.entities.actions.Evade;
import game.entities.actions.Feint;
import game.entities.effects.Thief;

public class Rogue extends Creature {

    protected Stats STARTING_STATS(int l){
        return new Stats(20*l, 50*l, 10*l, 10*l);
    }

    protected Action SKILLS(int i){
        switch (i){
            case 0:
                return new Attack();
            case 1:
                return new Evade();
            case 2:
                return new Feint();
            default:
                return null;
        }
    }
    
    public boolean action(Battle b){

    }

    public Rogue(int max_hp, int max_mana, int defense, int level) {

        super(max_hp, max_mana, defense, level);

        this.name = "RAM Rogue";

        this.addEffect(new Thief(-1));

    }
}
