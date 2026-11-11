package game.items;

public abstract class Item {

    private String name;
    private int value;

    public Item(String name, int value){

        this.name = name;
        this.value = value;

    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public int getValue(){
        return this.value;
    }

}
