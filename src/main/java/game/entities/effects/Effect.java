package game.entities.effects;

public abstract class Effect implements Applicable{

    private int duration;
    protected String name;

    public Effect(int duration){
        this.duration = duration;
        this.name = "Effect";
    }

    public boolean passDuration(){

        if (this.duration == 1){
            this.duration--;
            return true;
        }

        this.duration--;
        return false;

    }

    public int getDuration(){
        return this.duration;
    }

    public String getName(){
        return this.name;
    }

    public void setDuration(int dur){
        this.duration = dur;
    }

    public boolean equals(Effect e){
        return (this.name.equals(e.getName()));
    }
    
}
