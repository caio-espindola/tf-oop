package game.entities.creatures;

import game.entities.Stats;
import game.entities.actions.Action;
import game.entities.actions.Attack;
import game.entities.actions.Evade;
import game.entities.effects.Cowardly;
import game.levels.Battle;

public class Goblin extends Creature {

    protected Stats STARTING_STATS(int l){
        return new Stats(20*l, 30*l, 10*l, 0);
    }

    protected Action SKILLS(int i){
        switch (i){
            case 0:
                return new Attack();
            case 1:
                return new Evade();
            default:
                return null;
        }
    }

    public boolean action(Battle b){

    }

    public Goblin(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Glitch Goblin";

        this.addEffect(new Cowardly(-1));

    }

}
