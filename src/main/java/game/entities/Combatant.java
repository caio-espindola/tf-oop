package game.entities;

import game.levels.Battle;

public interface Combatant {

    public String getName();

    public int getMaxHP();

    public int getLevel();

    public int getMaxMana();

    public int getDefense();

    public Stats getStats();

    public int getCurrentMana();

    public int getCurrentHP();

    public boolean isAlive();

    public boolean takeDamage(int dmg);

    public void heal(int amount);

    public void action(Battle b);
}
