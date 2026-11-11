package game.items;

import game.entities.Entity;

public interface Consumable {

    public void consume(Entity e, Inventory<Item> inv);

}
