package game.entities.actions;

import game.levels.Battle;

@FunctionalInterface
public abstract interface Action {
    public abstract boolean act(Battle b);
}
