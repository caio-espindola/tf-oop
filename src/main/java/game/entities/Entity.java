package game.entities;

import game.levels.Battle;

public abstract class Entity implements Combatant {

    private String name;
    private int max_hp, current_hp, max_mana, current_mana, defense, level;
    private boolean alive;
    private Stats stats;

    public boolean isAlive(){
        return this.alive;
    }

    public boolean takeDamage(int dmg){

    }

    @Override
    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return max_hp;
    }

    public void setMaxHP(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getCurrentHP() {
        return current_hp;
    }

    public void setCurrentHP(int current_hp) {
        this.current_hp = current_hp;
    }

    public int getMaxMana() {
        return max_mana;
    }

    public void setMaxMana(int max_mana) {
        this.max_mana = max_mana;
    }

    public int getCurrentMana() {
        return current_mana;
    }

    public void setCurrentMana(int current_mana) {
        this.current_mana = current_mana;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public abstract void action(Battle b);
}
