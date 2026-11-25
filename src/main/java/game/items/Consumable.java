package game.items;

import game.entities.Entity;
import game.ui.Inventory;

public interface Consumable {

    public void consume(Entity e, Inventory inv);

}
