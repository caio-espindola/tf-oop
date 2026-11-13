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

    public Stats getBonuses(){
        return this.bonuses;
    }

    public Effect getTrait(){
        return this.trait;
    }

}
