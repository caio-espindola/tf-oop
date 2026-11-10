package game.items;

import game.entities.heroes.Hero;
import game.exceptions.InventoryFullException;

public abstract class Amulet extends Item implements Equippable {

    public void equip(Hero h, Inventory<Item> inv) throws InventoryFullException {

        Item aux = h.getGear(2);

        if (aux == null){
            h.setGear(2, this);
            return;
        }

        inv.addToInventory(aux);
        h.setGear(2, this);

    }

}
