package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.items.Amulet;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;

public class Cleric extends Hero {

    protected int HP_INCREASE(){
        return 60;
    }

    protected int MANA_INCREASE(){
        return 50;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Smite();
            case 1:
                return new Heal();
            case 2:
                return new WardingZone();
            case 3:
                return new FurubeYuraYura();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
        return new Stats(40, 40, 20, 100);
    }

    protected void INIT_GEAR() {
        Weapon w = new Weapon("Mace",     0, new Stats(20, 0,  0,  10), 0, null);
        Armor  a = new Armor ("Robes",    0, new Stats(0,  10, 10, 10), 0, null);
        Amulet m = new Amulet("Necklace", 0, new Stats(0,  0,  15, 15), 0, null);

        this.equip(w, null);
        this.equip(a, null);
        this.equip(m, null);
    }

    public Cleric(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Cleric";

    }
}
