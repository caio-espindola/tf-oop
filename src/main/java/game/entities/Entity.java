package game.entities;

import java.util.ArrayList;

import game.entities.actions.Move;
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

    /**
     * Returns the skills that this type can have
     * @param i the index of the desired skill
     */
    protected abstract Action SKILLS(int i);

    public Entity(int max_hp, int max_mana, int defense, int level){

        this.setMaxHP(max_hp);
        this.setCurrentHP(max_hp);
        this.setMaxMana(max_mana);
        this.setCurrentMana(max_mana);
        this.setDefense(defense);
        this.setLevel(level);

        this.skills = new Action[4];
        this.effects = new ArrayList<Effect>();
        this.stats = new Stats();
    }

    /**
     * Kills this entity
     */
    public void kill(){
        this.alive = false;
    }

    /**
     * Revives this entity, reseting effects
     */
    public void revive(){
        this.alive = true;
        this.clearEffects();
        this.setCurrentHP(max_hp);
        this.setCurrentMana(max_mana);
    }

    /**
     *
     * @return true if this entity is alive
     */
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * Decreases the duration of all effects by one, and removes them if duration becomes zero.
     * Call this on all entities for each activation in Battle
     */
    public void passEffects(){

        for (Effect e : this.effects){
            if (e.passDuration()){
                this.removeEffect(e);
            }
        }
    }

    /**
     * Removes all active effects on this entity
     */
    public void clearEffects(){
        for (Effect e : this.effects){
            this.removeEffect(e);
        }
    }

    /**
     * Adds an effect to this entity. If the same effect was already present, it is replaced with the new duration.
     * @param e An Effect
     * @return true if an effect was replaced or if e was null
     */
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

        e.set(null, this);
        return substitution;

    }

    /**
     * Looks for an active effect of the same type as the parameter and removes it.
     * @param e A sample effect (this instance is not used)
     * @return true if the removal was successful, false if there was no such effect
     */
    public boolean removeEffect(Effect e){
        if (e == null){
            return true;
        }

        e.unset(null, this);
        return effects.remove(e);
    }

    /**
     * Checks if this entity has an effect of the same type as the parameter
     */
    public boolean hasEffect(Effect e){
        for (Effect elem : this.effects){
            if (elem.equals(e)){
                return true;
            }
        }

        return false;
    }

    /**
     * Deals damage to this entity. if dmg is negative, heals instead;
     * @param dmg the amount of damage to be dealt
     * @return true if the damage results in death
     */
    public boolean takeDamage(int dmg){

        if (current_hp - dmg <= 0){
            current_hp = 0;
            this.kill();
            return true;
        }

        if (current_hp - dmg >= max_hp){
            current_hp = max_hp;
            return false;
        }

        current_hp -= dmg;
        return false;

    }

    /**
     * Heals this entity by the given amount
     * @param amount the health to be regained
     */
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
        if (max_hp <= 0){
            max_hp = 1;
        }

        this.max_hp = max_hp;

        if (current_hp > max_hp){
            current_hp = max_hp;
        }
    }

    public int getCurrentHP() {
        return current_hp;
    }

    public void setCurrentHP(int current_hp) {
        if (current_hp <= 0){
            current_hp = 1;
        }

        this.current_hp = current_hp;

        if (current_hp > max_hp){
            current_hp = max_hp;
        }
    }

    public Action getSkill(int index){
        return skills[index];
    }

    /**
     *
     * @return The list with all the skills this entity has
     */
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

        if (max_mana < 0){
            max_mana = 0;
        }

        this.max_mana = max_mana;

        if (current_mana > max_mana){
            current_mana = max_mana;
        }
    }

    public int getCurrentMana() {
        return current_mana;
    }

    public void setCurrentMana(int current_mana) {

        if (current_mana < 0){
            current_mana = 0;
        }

        this.current_mana = current_mana;

        if (current_mana > max_mana){
            current_mana = max_mana;
        }
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

    public void setStats(Stats s){
        this.stats = s;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Returns all possible actions for this entity
     * @return an ArrayList of Action
     */
    public ArrayList<Action> action(){

        ArrayList<Action> aux = new ArrayList<>();
        aux.add(new Move());

        for (Action a : this.getAllSkills()){
            aux.add(a);
        }

        return aux;
    }
}
