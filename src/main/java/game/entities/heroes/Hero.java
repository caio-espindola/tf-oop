package game.entities.heroes;

import game.entities.Entity;
import game.exceptions.InventoryFullException;
import game.items.*;
import game.levels.Battle;

public abstract class Hero extends Entity {

    private Weapon weapon;
    private Armor armor;
    private Amulet amulet;
    private int xp;

    public Hero(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.alive = true;

    }

    public int getXP() {
        return xp;
    }

    public boolean addXP(int amount){

        this.xp += amount;

        if (this.xp >= 100 && this.getLevel() < 3){
            this.xp -= 100;
            this.levelUp();
            return true;
        }

        return false;

    }

    protected abstract void levelUp();

    public void equip(Equippable e, Inventory<Item> inv) throws InventoryFullException {

        e.equip(this, inv);

    }

    public abstract void action(Battle b);

    public Weapon getGear(Weapon e){
        return this.weapon;
    }

    public Armor getGear(Armor e){
        return this.armor;
    }

    public Amulet getGear(Amulet e){
        return this.amulet;
    }

    public void setGear(Weapon e){
        this.weapon = e;
    }

    public void setGear(Armor e){
        this.armor = e;
    }

    public void setGear(Amulet e){
        this.amulet = e;
    }
}
