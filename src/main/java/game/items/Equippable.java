package game.items;

import game.entities.Stats;
import game.entities.heroes.Hero;

public interface Equippable {

    public void equip(Hero h, Inventory<Item> inv);

    public Item unequip(Hero h);
}
