package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Move implements Action {

    public boolean act(Battle b, Entity user, Entity target){

        Battle.Position t_pos = b.find(target);
        Battle.Position u_pos = b.find(user);

        if (t_pos == null){
            return false;
        }

        if (!(target.isAlive()) && t_pos.onBack() && u_pos.onFront()){
            return false;
        }



        b.move(user, b.find(target));

    }
}
