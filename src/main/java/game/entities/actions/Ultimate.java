package game.entities.actions;

public abstract class Ultimate extends Action {

    public final String text;
    public Ultimate(String name, String description, String text) {
        super(name, description);

        this.text = text;
    }

    public void cutscene(){

    }
}
