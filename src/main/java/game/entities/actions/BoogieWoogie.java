package game.entities.actions;

import game.entities.Entity;
import game.entities.effects.Parrying;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class BoogieWoogie extends Ultimate {

    public final int cost = 30;

    public BoogieWoogie() {
        super("Try-Catch",

                """
                Apply your Parry on all your allies at once, lasting 3 turns.\n
                "Tell me: What kind of data is your type?"
                """,

                """
                The toll of the Gion Shoja bells echoes the impermanence of all things...\n
                The color of the Sala flowers reveals the truth: that the prosperous must decline.\n
                ...HOWEVER!\n
                We are the Exception!
                """);
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean on_left = b.find(user).onLeft();

        if (! on_same_side){
            throw new InvalidTargetException("Can only be used on allies");
        }

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        Entity[] ally_list = (on_left) ? b.getHeroes() : b.getEnemies();

        for (Entity e : ally_list){
            e.addEffect(new Parrying(3));
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }

}
