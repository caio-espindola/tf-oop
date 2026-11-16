package game.entities;

import game.entities.actions.Action;
import game.levels.Battle;

public class PlaceHolder extends Entity{

    public PlaceHolder() {
        super(0, 0, 0, 0);
    }

    public Action SKILLS(int i){
        return null;
    }

    @Override
    public void action(Battle b) {
        return;
    }

}
