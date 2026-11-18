package game.entities.actions;

import game.entities.Entity;
import game.entities.Stats;
import game.entities.heroes.Archer;
import game.entities.heroes.Cleric;
import game.entities.heroes.Mage;
import game.entities.heroes.Warrior;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;

public class Learn extends Action {

    public final int cost = 50;

    public Learn() {
        super("Learn",
                "Learn from others, adding their stats to your own. When used on an enemy, increases your health instead."
        );
    }

    public boolean act(Battle b, Entity user, Entity target) throws NotEnoughManaException {

        if (user.getCurrentMana() < this.cost){
            throw new NotEnoughManaException();
        }

        Stats s = user.getStats();

        if (target instanceof Warrior){
            s.setSTR(s.getSTR() + 100);
        } else if (target instanceof Archer){
            s.setDEX(s.getDEX() + 100);
        } else if (target instanceof Mage){
            s.setINT(s.getINT() + 100);
        } else if (target instanceof Cleric){
            s.setPIE(s.getPIE() + 100);
        } else {
            user.setMaxHP(user.getMaxHP() + 100);
        }

        user.setCurrentMana(user.getCurrentMana() - this.cost);
        return true;
    }
}
