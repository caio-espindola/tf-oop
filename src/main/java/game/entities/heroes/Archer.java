package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.items.Amulet;
import game.items.Armor;
import game.items.Weapon;

public class Archer extends Hero {

    protected int HP_INCREASE(){
        return 80;
    }

    protected int MANA_INCREASE(){
        return 30;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Arrow();
            case 1:
                return new Evade();
            case 2:
                return new Focus();
            case 3:
                return new FukumaMizushi();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
        return new Stats(60, 100, 30, 10);
    }

    protected void INIT_GEAR() {
        Weapon w = new Weapon("Simple Bow",    0, new Stats(10, 20, 0, 0), 0, null);
        Armor  a = new Armor ("Leather Armor", 0, new Stats(10, 20, 0, 0), 0, null);
        Amulet m = new Amulet("Necklace",      0, new Stats(0, 0, 15, 15), 0, null);

        this.equip(w);
        this.equip(a);
        this.equip(m);
    }

    public Archer(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Archer";

    }
}
