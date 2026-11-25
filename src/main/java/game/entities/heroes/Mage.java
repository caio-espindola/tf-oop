package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.items.Amulet;
import game.items.Armor;
import game.items.Weapon;

public class Mage extends Hero {

    protected int HP_INCREASE(){
        return 40;
    }

    protected int MANA_INCREASE(){
        return 70;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new MagicMissile();
            case 1:
                return new MageShield();
            case 2:
                return new Hex();
            case 3:
                return new Murasaki();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
        return new Stats(20, 50, 120 ,10);
    }

    protected void INIT_GEAR() {
        Weapon w = new Weapon("Staff",    0, new Stats(0, 0, 30, 0), 0,   null);
        Armor a  = new Armor ("Robes",    0, new Stats(0, 10, 10, 10), 0, null);
        Amulet m = new Amulet("Necklace", 0, new Stats(0, 0, 15, 15), 0,  null);

        this.equip(w);
        this.equip(a);
        this.equip(m);
    }

    public Mage(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Mage";

    }
}
