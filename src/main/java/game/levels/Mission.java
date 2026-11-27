package game.levels;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.entities.creatures.Creature;
import game.entities.creatures.Goblin;
import game.entities.creatures.Skeleton;
import game.items.Item;

import java.util.ArrayList;

public class Mission {

    private ArrayList<Entity> enemies;
    private String name;
    private ArrayList<Item> rewards;

    public Mission(String name, ArrayList<Entity> enemies, ArrayList<Item> rewards){

        this.name = name;
        this.enemies = enemies;
        this.rewards = rewards;

    }

    public static Mission[] getAllMissions() {

        Mission[] m = new Mission[1];

        ArrayList<Entity> ar0 = new ArrayList<>();

        ar0.add(new Goblin(50, 10, 20, 1));
        ar0.add(new Goblin(50, 10, 20, 1));
        ar0.add(new Goblin(50, 10, 20, 1));
        ar0.add(new Skeleton(60, 15, 30, 1));
        ar0.add(new PlaceHolder());

        m[0] = new Mission("m0", ar0, null);

        return m;

    }

    public ArrayList<Entity> getEnemies() {
        return this.enemies;
    }

    public String getName(){
        return this.name;
    }
}
