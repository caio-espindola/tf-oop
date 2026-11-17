package game.entities.actions;

import game.entities.Entity;
import game.levels.Battle;

public class Murasaki extends Ultimate {

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

    public boolean act(Battle b, Entity user, Entity target){

    }

}
