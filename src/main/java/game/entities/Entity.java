package game.entities;

import java.util.ArrayList;

import game.items.Equippable;
import game.levels.Battle;
import game.entities.actions.Action;
import game.entities.effects.Effect;

public abstract class Entity implements Combatant {

    protected String name;
    private int max_hp, current_hp, max_mana, current_mana, defense, level;
    protected boolean alive;
    private Stats stats;
    private Action[] skills;
    private ArrayList<Effect> effects;

    public Entity(int max_hp, int max_mana, int defense, int level){

        this.setMaxHP(max_hp);
        this.setCurrentHP(max_hp);
        this.setMaxMana(max_mana);
        this.setCurrentMana(max_mana);
        this.setDefense(defense);
        this.setLevel(level);

        this.skills = new Action[4];
        this.effects = new ArrayList<Effect>();
    }

    public boolean isAlive(){
        return this.alive;
    }

    public boolean addEffect(Effect e){

        if (e == null){
            return true;
        }

        boolean substitution = false;

        for (Effect elem : this.effects){
            if (elem.equals(e)){
                effects.set(effects.indexOf(elem), e);
                substitution = true;
            }
        }

        if (!substitution){
            this.effects.add(e);
        }

        return substitution;

    }

    public boolean removeEffect(Effect e){
        if (e == null){
            return true;
        }

        return effects.remove(e);
    }

    public boolean takeDamage(int dmg){

        if (current_hp - dmg <= 0){
            current_hp = 0;
            this.alive = false;
            return true;
        }

        if (current_hp - dmg >= max_hp){
            current_hp = max_hp;
            return false;
        }

        current_hp -= dmg;
        return false;

    }

    public void heal(int amount){

        this.takeDamage(amount * -1);

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

    public Action getSkill(int index){
        return skills[index];
    }

    public Action[] getAllSkills(){
        return this.skills;
    }

    public void setSkill(int index, Action skill){
        this.skills[index] = skill;
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
