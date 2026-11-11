package game.items;

import game.entities.Stats;
import game.entities.effects.Effect;
import game.entities.heroes.Hero;
import game.exceptions.InventoryFullException;

public abstract class Weapon extends Gear {

    public Weapon(String name, int value, Stats bonuses, int rarity, Effect trait){

        super(name, value, bonuses, rarity, trait);

    }

}
