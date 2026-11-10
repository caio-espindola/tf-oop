package game.items;

import game.entities.Entity;

public abstract class Potion extends Item implements Consumable {

    public abstract void consume(Entity e);

}
