package game.entities.effects;

import game.entities.Entity;
import game.entities.creatures.Skeleton;
import game.levels.Battle;

public class Necromancer extends Effect {

    public Necromancer(int duration){

        super(duration);

        this.name = "Necromancer";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        e.addEffect(new Evading(-1));
        return true;
    }

    public boolean apply(Battle b, Entity e){

        boolean has_skeletons = false;
        Battle.Position p = b.find(e);
        Entity[] ally_list = p.onRight() ? b.getEnemies() : b.getHeroes();

        for (Entity a : ally_list){
            has_skeletons = has_skeletons || a instanceof Skeleton;
        }

        if (has_skeletons){
            if (e.hasEffect(new Evading(0))){
                return true;
            } else {
                return this.set(b,e);
            }
        } else {
            if (e.hasEffect(new Evading(0))){
                this.unset(b, e);
                return true;
            } else {
                return true;
            }
        }
    }

    @Override
    public void unset(Battle b, Entity e) {
        e.removeEffect(new Evading(0));
    }

}
