package game.items;

import game.entities.Stats;
import game.entities.effects.Effect;
import game.entities.heroes.Hero;
import game.exceptions.InventoryFullException;

public abstract class Weapon extends Item implements Equippable {

    private Stats bonuses;
    private int rarity;
    private Effect trait;

    public void equip(Hero h, Inventory<Item> inv) throws InventoryFullException {

        Weapon aux = h.getGear(this);

        if (aux == null){
            h.setGear(this);
            return;
        }

        inv.addToInventory(aux.unequip(h));
        h.setGear(this);

        Stats h_stats = h.getStats();

        h_stats.setSTR(h_stats.getSTR() + this.bonuses.getSTR());
        h_stats.setDEX(h_stats.getDEX() + this.bonuses.getDEX());
        h_stats.setINT(h_stats.getINT() + this.bonuses.getINT());
        h_stats.setPIE(h_stats.getPIE() + this.bonuses.getPIE());

        h.addEffect(this.trait);
    }

    public Item unequip(Hero h){

        Stats h_stats = h.getStats();

        h_stats.setSTR(h_stats.getSTR() - this.bonuses.getSTR());
        h_stats.setDEX(h_stats.getDEX() - this.bonuses.getDEX());
        h_stats.setINT(h_stats.getINT() - this.bonuses.getINT());
        h_stats.setPIE(h_stats.getPIE() - this.bonuses.getPIE());

        h.removeEffect(this.trait);

        return h.getGear(this);

    }

}
