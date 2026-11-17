package game.entities.actions;

import game.entities.Entity;
import game.entities.heroes.Mahoraga;
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

    public boolean act(Battle b, Entity user, Entity target){

        if (user != target){
            return false;
        }

        Mahoraga big_raga = new Mahoraga(100, 100, 0, 1);
        Battle.Position user_pos = b.find(user);

        b.kill(user);
        b.add(big_raga, user_pos);

    }

}
