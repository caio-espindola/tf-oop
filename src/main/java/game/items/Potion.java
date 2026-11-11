package game.items;

import game.entities.Entity;
import game.entities.effects.Effect;

public abstract class Potion extends Item implements Consumable {

    protected Effect effect;

    public Potion(String name, int value, Effect effect){

        super(name, value);

        this.effect = effect;

    }

    public void consume(Entity e, Inventory<Item> inv){

        e.addEffect(this.effect);
        inv.RemoveFromInventory(this);

    }

}
