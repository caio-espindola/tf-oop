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

    // action(): pass functional interface as parameter? or individual methods attack(), spell(), move() etc?
    // specification says to pass instance of Battle, but it seems weird.

    public void action(Battle b);
}
