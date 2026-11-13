package game.entities.heroes;

import game.entities.Entity;
import game.entities.Stats;
import game.entities.actions.Action;
import game.exceptions.InventoryFullException;
import game.items.*;
import game.levels.Battle;

public abstract class Hero extends Entity {

    private Gear weapon, armor, amulet;
    private int xp;
    protected int level_up_available;

    private final int MAX_LEVEL = 3;
    protected abstract int HP_INCREASE();
    protected abstract int MANA_INCREASE();
    protected abstract Stats STARTING_STATS();

    public Hero(int max_hp, int max_mana, int defense, int level){

        super(max_hp, max_mana, defense, level);

        this.setSkill(0, SKILLS(0));
        this.setSkill(1, SKILLS(1));
        this.setSkill(2, null);
        this.setSkill(3, null);

        this.setStats(STARTING_STATS());

        this.alive = true;

    }

    public int getXP() {
        return xp;
    }

    public boolean canLevelUp(){
        return this.level_up_available > 0;
    }

    public boolean addXP(int amount){

        this.xp += amount;

        if ((this.xp >= 100 * this.getLevel()) && (this.getLevel() < MAX_LEVEL)){
            this.xp -= 100 * this.getLevel();
            this.level_up_available++;
            return this.addXP(0);
        }

        return false;

    }

    protected boolean levelUp(Stats alloc){

        if (this.level_up_available < 1){
            return false;
        }

        this.setLevel(this.getLevel() + 1);

        if (this.getLevel() == 2){

            this.setSkill(2, SKILLS(2));

        } else if (this.getLevel() == 3){

            this.setSkill(3, SKILLS(3));

        }

        Stats aux = this.getStats();

        aux.setSTR(aux.getSTR() + alloc.getSTR());
        aux.setDEX(aux.getDEX() + alloc.getDEX());
        aux.setINT(aux.getINT() + alloc.getINT());
        aux.setPIE(aux.getPIE() + alloc.getPIE());

        this.setMaxHP(this.getMaxHP() + HP_INCREASE());
        this.setMaxMana(this.getMaxMana() + MANA_INCREASE());

        return true;

    }

    public void equip(Gear e, Inventory<Item> inv) throws InventoryFullException {

        Gear aux = this.getGear(e);

        if (aux == null){
            this.setGear(e);
        } else {
            inv.addToInventory(this.unequip(aux));
            this.setGear(e);
        }

        Stats h_stats = this.getStats();

        h_stats.setSTR(h_stats.getSTR() + e.getBonuses().getSTR());
        h_stats.setDEX(h_stats.getDEX() + e.getBonuses().getDEX());
        h_stats.setINT(h_stats.getINT() + e.getBonuses().getINT());
        h_stats.setPIE(h_stats.getPIE() + e.getBonuses().getPIE());

        this.addEffect(e.getTrait());

    }

    private Gear unequip(Gear g){

        Stats h_stats = this.getStats();

        h_stats.setSTR(h_stats.getSTR() - g.getBonuses().getSTR());
        h_stats.setDEX(h_stats.getDEX() - g.getBonuses().getDEX());
        h_stats.setINT(h_stats.getINT() - g.getBonuses().getINT());
        h_stats.setPIE(h_stats.getPIE() - g.getBonuses().getPIE());

        this.removeEffect(g.getTrait());

        return this.getGear(g);
    }

    public abstract void action(Battle b);

    public Gear getGear(Gear g){

        if (g instanceof Weapon){

            return this.weapon;

        } else if (g instanceof Armor){

            return this.armor;

        } else if (g instanceof Amulet){

            return this.amulet;

        } else {
            return null;
        }

    }

    public void setGear(Gear g){

        if (g instanceof Weapon){

            this.weapon = g;

        } else if (g instanceof Armor){

            this.armor = g;

        } else if (g instanceof Amulet){

            this.amulet = g;

        }
    }
}
