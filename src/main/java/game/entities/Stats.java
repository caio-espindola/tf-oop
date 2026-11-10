package game.entities;

public class Stats {
    
    private int str, dex, intl, pie;

    public int getSTR(){
        return this.str;
    }

    public int getDEX(){
        return this.dex;
    }

    public int getINT(){
        return this.intl;
    }

    public int getPIE(){
        return this.pie;
    }

    public void setSTR(int strength){
        this.str = strength;
    }

    public void setDEX(int dexterity){
        this.dex = dexterity;
    }

    public void setINT(int intelligence){
        this.intl = intelligence;
    }

    public void setPIE(int piety){
        this.pie = piety;
    }

}
