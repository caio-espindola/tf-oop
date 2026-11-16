package game.entities.effects;

import game.entities.Entity;
import game.levels.Battle;

public class Fearsome extends Effect {

    public Fearsome(int duration){

        super(duration);

        this.name = "Fearsome";

    }

    @Override
    public boolean set(Battle b, Entity e) {
        Battle.Position p = b.find(e);
        Entity[] foe_list = p.onRight() ? b.getHeroes() : b.getEnemies();

        for (Entity f : foe_list){
            f.setDefense(f.getDefense() - 10);
        }

        return true;
    }

    public boolean apply(Battle b, Entity e){
        return true;
    }

    @Override
    public void unset(Battle b, Entity e) {

        Battle.Position p = b.find(e);
        Entity[] foe_list = p.onRight() ? b.getHeroes() : b.getEnemies();

        for (Entity f : foe_list){ // heroes that die before a dragon is defeated get a permanent defense debuff. thats crazy bro
            f.setDefense(f.getDefense() + 10); // and big raga can get a permanent defense buff in the same way
        }

    }

}
