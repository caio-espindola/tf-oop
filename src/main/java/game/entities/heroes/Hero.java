package game.entities.heroes;

import game.entities.Entity;
import game.exceptions.InventoryFullException;
import game.items.Equippable;
import game.items.Inventory;
import game.items.Item;
import game.levels.Battle;

public abstract class Hero extends Entity {

    public Inventory<Item> inventory;
    private int xp;

    public Hero(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.alive = true;

    }

    public int getXP() {
        return xp;
    }

    public boolean addXP(){

    }

    private void levelUp(){

    }

    public String getJob(){
        return "Hero";
    }

    public boolean equip(Equippable e){

    }

    public boolean addToInventory(Item i) throws InventoryFullException {

    }

    public Item removeFromInventory(Item i){

    }

    public abstract void action(Battle b);

}
