package game.items;

public abstract class Item {

    public String description;
    private String name;
    private int value;

    public Item(String name, int value){

        this.name = name;
        this.value = value;

    }

    public abstract String getType();

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
