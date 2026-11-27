package game.entities;

import game.entities.actions.Action;
import game.levels.Battle;

import java.util.ArrayList;

public class PlaceHolder extends Entity{

    public PlaceHolder() {
        super(0, 0, 0, 0);
    }

    public Action SKILLS(int i){
        return null;
    }

    @Override
    public ArrayList<Action> action() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
