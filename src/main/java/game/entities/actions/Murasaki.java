package game.entities.actions;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

import java.util.stream.Stream;

public class Murasaki extends Ultimate {

    public final int cost = 70;

    public Murasaki() {
        super("Null Pointer",

                """
                Fire an unblockable attack with tremendous power that can pierce enemies.\n
                "Throughout the Kernel and the OS, I alone am the admin".
                """,

                """
                Take the dynamic method and the unverified object and combine these concepts into an undefined reference...\n
                Imaginary Technique:\n
                NULL POINTER EXCEPTION
                """

        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException, NotEnoughManaException {

        boolean on_same_side = b.find(user).sameSide(b.find(target));
        boolean hero_user = b.find(user).onLeft();
        Battle.Position pos = b.find(target);
        Entity[] target_list;

        if (on_same_side){
            throw new InvalidTargetException("Cannot be used against allies");
        }

        if (user.getCurrentMana() > this.cost){
            throw new NotEnoughManaException();
        }

        if (hero_user){

            if (pos == Battle.Position.RIGHT_FRONT_TOP || pos == Battle.Position.RIGHT_BACK_TOP){

                target_list = new Entity[]{
                        b.getFromPosition(Battle.Position.RIGHT_FRONT_TOP),
                        b.getFromPosition(Battle.Position.RIGHT_BACK_TOP),
                        b.getFromPosition(Battle.Position.RIGHT_BACK_MID)
                };

            } else if (pos == Battle.Position.RIGHT_BACK_MID){

                target_list = new Entity[]{
                        b.getFromPosition(Battle.Position.RIGHT_FRONT_TOP),
                        b.getFromPosition(Battle.Position.RIGHT_BACK_MID),
                        b.getFromPosition(Battle.Position.RIGHT_FRONT_BOTTOM)
                };

            } else if (pos == Battle.Position.RIGHT_FRONT_BOTTOM || pos == Battle.Position.RIGHT_BACK_BOTTOM){

                target_list = new Entity[]{
                        b.getFromPosition(Battle.Position.RIGHT_FRONT_BOTTOM),
                        b.getFromPosition(Battle.Position.RIGHT_BACK_BOTTOM),
                        b.getFromPosition(Battle.Position.RIGHT_BACK_MID)
                };

            } else {
                return false;
            }

        } else {

            if (pos == Battle.Position.LEFT_BACK_TOP || pos == Battle.Position.LEFT_FRONT_TOP){

                target_list = new Entity[]{
                        b.getFromPosition(Battle.Position.LEFT_BACK_TOP),
                        b.getFromPosition(Battle.Position.LEFT_FRONT_TOP)
                };

            } else if (pos == Battle.Position.LEFT_BACK_BOTTOM || pos == Battle.Position.LEFT_FRONT_BOTTOM){

                target_list = new Entity[]{
                        b.getFromPosition(Battle.Position.LEFT_BACK_BOTTOM),
                        b.getFromPosition(Battle.Position.LEFT_FRONT_BOTTOM)
                };

            } else {
                return false;
            }

        }

        int user_int = user.getStats().getINT();

        double r = 2 + ((Math.random())-0.5)/2; // generates a random double in [1.75 , 2.25[
        int targets = Stream.of(target_list).filter(t -> !(t instanceof PlaceHolder) && (t != null)).toList().size();

        final double atk = user_int * r / targets;
        
        Stream.of(target_list).forEach(e -> e.takeDamage((int) Math.ceil(atk / Math.pow(e.getStats().getINT(), 1/3.0))));

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;

    }

}
