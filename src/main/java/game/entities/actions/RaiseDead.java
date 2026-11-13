package game.entities.actions;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.entities.creatures.Skeleton;
import game.levels.Battle;

public class RaiseDead implements Action {

    public boolean act(Battle b, Entity user, Entity target) {

        if (!(target instanceof PlaceHolder)){
            return false;
        }

        double r = 1 + ((Math.random())-0.5)/2; // generates a random double in [0.75 , 1.25[
        int user_int = user.getStats().getINT();
        int user_pie = user.getStats().getPIE();

        r -= 0.25;

        int hp = (int) Math.floor(user_int-user_pie * r / 2);

        Battle.Position p = b.find(target);
        Skeleton s = new Skeleton(hp, 0, 0, 2);

        return b.add(s, p);

    }
}
