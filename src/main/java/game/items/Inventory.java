package game.items;

import game.exceptions.InventoryFullException;

import java.util.ArrayList;

public class Inventory<T extends Item> {

    private ArrayList<T> items;
    private int capacity, count;

    public Inventory(){

        this.items = new ArrayList<T>();
        this.capacity = 10;
        this.count = 0;

    }

    public void addToInventory(T item) throws InventoryFullException{
        if (this.count == this.capacity){
            throw new InventoryFullException("Inventário Cheio");
        }

        this.items.add(item);
    }

    public boolean RemoveFromInventory(T item){
        return this.items.remove(item);
    }

    public boolean contains(T item){
        return this.items.contains(item);
    }

    public T getIndex(int index){
        return this.items.get(index);
    }

}
