package game.items;

import game.entities.Stats;
import game.entities.effects.Effect;
import game.entities.heroes.Hero;
import game.exceptions.InventoryFullException;

public abstract class Amulet extends Gear {

    public Amulet(String name, int value, Stats bonuses, int rarity, Effect trait){

        super(name, value, bonuses, rarity, trait);

    }

}
