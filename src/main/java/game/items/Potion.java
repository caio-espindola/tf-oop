package game.items;

import game.entities.Entity;
import game.entities.effects.Effect;
import game.ui.Inventory;

public abstract class Potion extends Item implements Consumable {

    protected Effect effect;

    public Potion(String name, int value, Effect effect){

        super(name, value);

        this.effect = effect;

    }

    public String getType(){
        return "Potion";
    }

    public void consume(Entity e, Inventory inv){

        e.addEffect(this.effect);
        inv.removeFromInventory(this);

    }

}
