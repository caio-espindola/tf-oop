package game.entities;

public class Stats {
    
    private int str, dex, intl, pie;

    public enum stats {
        STR,
        DEX,
        INT,
        PIE
    }

    public Stats(){
        this.str = this.dex = this.intl = this.pie = 0;
    }

    public Stats(int str, int dex, int intl, int pie){
        this.str = str;
        this.dex = dex;
        this.intl = intl;
        this.pie = pie;
    }

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
