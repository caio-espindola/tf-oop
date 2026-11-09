package game.entities;

import game.items.Inventory;
import game.items.Item;

public abstract class Hero extends Entity {

    public Inventory<Item> inventory;
    private int xp;

    public int getXP() {
        return xp;
    }

    public boolean addXP(){

    }

    //getClass() check previous solution in gravity.

}
