package game.items;

import game.entities.Stats;
import game.entities.effects.Effect;
import game.entities.heroes.Hero;

public abstract class Gear extends Item implements Equippable{
    
    protected Stats bonuses;
    protected int rarity;
    protected Effect trait;

    public Gear(String name, int value, Stats bonuses, int rarity, Effect trait){

        super(name, value);

        this.bonuses = bonuses;
        this.rarity = rarity;
        this.trait = trait;

    }

    public void equip(Hero h, Inventory<Item> inv){

        Gear aux = h.getGear(this);

        if (aux == null){
            h.setGear(this);
        } else {
            inv.addToInventory(aux.unequip(h));
            h.setGear(this);
        }

        Stats h_stats = h.getStats();

        h_stats.setSTR(h_stats.getSTR() + this.bonuses.getSTR());
        h_stats.setDEX(h_stats.getDEX() + this.bonuses.getDEX());
        h_stats.setINT(h_stats.getINT() + this.bonuses.getINT());
        h_stats.setPIE(h_stats.getPIE() + this.bonuses.getPIE());

        h.addEffect(this.trait);
        
    }

    public Gear unequip(Hero h){

        Stats h_stats = h.getStats();

        h_stats.setSTR(h_stats.getSTR() - this.bonuses.getSTR());
        h_stats.setDEX(h_stats.getDEX() - this.bonuses.getDEX());
        h_stats.setINT(h_stats.getINT() - this.bonuses.getINT());
        h_stats.setPIE(h_stats.getPIE() - this.bonuses.getPIE());

        h.removeEffect(this.trait);

        return h.getGear(this);

    }
}
