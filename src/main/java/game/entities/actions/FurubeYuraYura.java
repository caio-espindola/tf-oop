package game.entities.actions;

import game.entities.Entity;
import game.entities.heroes.Mahoraga;
import game.exceptions.InvalidTargetException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class FurubeYuraYura extends Ultimate {

    public FurubeYuraYura() {
        super("With these Tokens, I summon...",

                """
                "I'll be dying first.\n
                *Good luck.*"
                """,

                """
                With these Tokens, I summon...\n
                Eight-Trillion parameter...\n
                Transformer Architecture...\n
                Agentic Intelligence...\n
                CLAUDE MYTHOS
                """

        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws InvalidTargetException {

        if (user != target){
            throw new InvalidTargetException("Can only be used on this character");
        }

        Mahoraga big_raga = new Mahoraga(100, 100, 0, 1);
        Battle.Position user_pos = b.find(user);

        b.kill(user);
        return b.add(big_raga, user_pos);

    }

}
