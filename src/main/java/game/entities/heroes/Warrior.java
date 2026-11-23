package game.entities.heroes;

import game.entities.Stats;
import game.entities.actions.*;
import game.items.Amulet;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;

public class Warrior extends Hero {

    protected int HP_INCREASE(){
        return 100;
    }

    protected int MANA_INCREASE(){
        return 10;
    }

    protected Action SKILLS(int i){

        switch (i) {
            case 0:
                return new Attack();
            case 1:
                return new Block();
            case 2:
                return new Parry();
            case 3:
                return new BoogieWoogie();
            default:
                return null;
        }
    }

    protected Stats STARTING_STATS(){
         return new Stats(120, 60, 10, 10);
    }

    protected void INIT_GEAR() {
        Weapon w = new Weapon("Iron Sword", 0, new Stats(20, 10, 0, 0), 0, null);
        Armor  a = new Armor ("Iron Armor", 0, new Stats(20, 10, 0, 0), 0, null);
        Amulet m = new Amulet("Necklace",   0, new Stats(0, 0, 15, 15), 0, null);

        this.equip(w, null);
        this.equip(a, null);
        this.equip(m, null);
    }

    public Warrior(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.name = "Warrior";

    }



}
