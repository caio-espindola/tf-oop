package game.levels;

import game.entities.Entity;
import game.entities.PlaceHolder;
import game.entities.creatures.Creature;
import game.entities.heroes.Hero;
import game.ui.Arena;

public class Battle {

    private Entity[] heroes, enemies;
    private int turn, count, active_pos;

    public enum Position {
        LEFT_BACK_TOP,
        LEFT_BACK_BOTTOM,
        LEFT_FRONT_TOP,
        LEFT_FRONT_BOTTOM,

        RIGHT_BACK_TOP,
        RIGHT_BACK_MID,
        RIGHT_BACK_BOTTOM,
        RIGHT_FRONT_TOP,
        RIGHT_FRONT_BOTTOM;

        public boolean sameSide(Position p){
            return (this.onLeft() && p.onLeft()) || (this.onRight() && p.onRight());
        }

        public static Position atIndex(int i){
            return values()[i];
        }

        public boolean onLeft(){
            return this.compareTo(RIGHT_BACK_TOP) < 0;
        }

        public boolean onRight(){
            return !this.onLeft();
        }

        public boolean onFront(){
            if (this.onLeft()){

                return (this == LEFT_FRONT_TOP) || (this == LEFT_FRONT_BOTTOM);

            } else {

                return (this == RIGHT_FRONT_TOP) || (this == RIGHT_FRONT_BOTTOM);

            }
        }

        public boolean onBack(){
            return !this.onFront();
        }

        public boolean onTop(){
            return (this == LEFT_BACK_TOP) || (this == LEFT_FRONT_TOP)
                    || (this == RIGHT_BACK_TOP) || (this == RIGHT_FRONT_TOP);
        }

        public boolean onBottom(){
            return (!this.onTop()) && (!this.onMid());
        }

        public boolean onMid(){
            return (this == RIGHT_BACK_MID);
        }

    }

    public Battle(){

        this.heroes = new Entity[4];
        this.enemies = new Entity[5];

        turn = count = active_pos = 0;

    }

    public Entity[] getHeroes(){
        return this.heroes;
    }

    public Entity[] getEnemies(){
        return this.enemies;
    }

    public Entity getFromPosition(Position p){
        if (p.onLeft()){

            return heroes[p.ordinal()];

        } else {

            return enemies[p.ordinal() - 4];

        }
    }

    public Position find(Entity e){

        for (int i = 0; i < 4; i++){
            if (heroes[i].equals(e)){
                return Position.atIndex(i);
            }
        }

        for (int i = 0; i < 5; i++){
            if (enemies[i].equals(e)){
                return Position.atIndex(i + 4);
            }
        }

        return null;

    }

    public boolean kill(Entity e){

        Position p = find(e);

        if (p == null || e instanceof PlaceHolder){
            return false;
        }

        int i = p.ordinal();

        if (i < 4){
            heroes[i] = new PlaceHolder();
        } else {
            enemies[i-4] = new PlaceHolder();
        }

        e.kill();
        e.clearEffects();
        return true;
    }

    public boolean add(Entity e, Position p){

        if (e instanceof Hero){
            return this.add((Hero) e, p);
        } else if (e instanceof Creature){
            return this.add((Creature) e, p);
        } else if (e instanceof PlaceHolder){
            
            if (p.ordinal() < 4){
                this.heroes[p.ordinal()] = e;
                this.count++;
            } else if (p.ordinal() >= 4 && p.ordinal() < 9){
                this.enemies[p.ordinal() - 4] = e;
                this.count++;
            } else {
                return false;
            }

            return true;

        } else {
            return false;
        }

    }

    private boolean add(Hero h, Position p){

        if (p.onRight()){
            System.out.println("ON WRONG SIDE");
            return false;
        }

        if ((! (getFromPosition(p) instanceof PlaceHolder)) && (getFromPosition(p) != null)){
            return false;
        }

        this.heroes[p.ordinal()] = h;
        this.count++;
        return true;

    }

    private boolean add(Creature c, Position p){

        if (p.onLeft()){
            System.out.println("ON WRONG SIDE");
            return false;
        }

        if ((! (getFromPosition(p) instanceof PlaceHolder)) && (getFromPosition(p) != null)){
            return false;
        }

        this.enemies[p.ordinal() - 4] = c;
        this.count++;
        return true;

    }

    public boolean move(Entity e, Position p){

        if (e instanceof Hero){
            return this.move((Hero) e, p);
        } else if (e instanceof Creature){
            return this.move((Creature) e, p);
        } else {
            return false;
        }

    }

    private boolean move(Hero h, Position p){

        if (p.onRight()){
            return false;
        }

        Entity aux = this.getFromPosition(p);
        Position h_pos = this.find(h);

        if (h_pos == null){
            return false;
        }

        if (! (aux instanceof PlaceHolder)){
            heroes[h_pos.ordinal()] = aux;
        } else {
            heroes[h_pos.ordinal()] = new PlaceHolder();
        }

        heroes[p.ordinal()] = h;
        return true;

    }

    private boolean move(Creature c, Position p){

        if (p.onLeft()){
            return false;
        }

        Entity aux = this.getFromPosition(p);
        Position c_pos = this.find(c);

        if (c_pos == null){
            return false;
        }

        if (! (aux instanceof PlaceHolder)){
            enemies[c_pos.ordinal() - 4] = aux;
        } else {
            enemies[c_pos.ordinal() - 4] = new PlaceHolder();
        }

        enemies[p.ordinal() - 4] = c;
        return true;

    }

    public void checkVictory(){

        int victory = 0;

        for (int i = 0; i < 8; i++){
            Entity e = getFromPosition(Position.atIndex(i));
            System.out.println("Entity " + e.getName() + " alive: " + e.isAlive());
        }

        boolean enemy_victory = true;
        for (int i = 0; i < 4; i++){
            if (getFromPosition(Position.atIndex(i)).isAlive()){
                enemy_victory = false;
            }
        }

        boolean hero_victory = true;
        for (int i = 4; i < 8; i++){
            if (getFromPosition(Position.atIndex(i)).isAlive()){
                hero_victory = false;
            }
        }

        if (hero_victory && enemy_victory){
            throw new RuntimeException("Mutual Conditions on checkVictory()");
        }

        victory = hero_victory? 1 :
                    enemy_victory? -1 : 0;

        if (victory == 1){
            end(true);
        } else if (victory == -1){
            end(false);
        }
    }

    private void passTurn(){

        active_pos = 0;

    }

    public void passActive(){

        active_pos++;

        if (active_pos > 8){
            passTurn();
        }

        checkVictory();

        Entity current = getFromPosition(Position.atIndex(active_pos));

        if ((current instanceof PlaceHolder) || !(current.isAlive())){
            passActive();
            return;
        }

        current.passEffects();

        Arena.setActiveEntity(current);

    }

    public void start(){

        for (int i = 0; i < 4; i++){
            if (heroes[i] == null){
                heroes[i] = new PlaceHolder();
                this.add(heroes[i], Position.atIndex(i));
            }
        }

        for (int i = 0; i < 5; i++){
            if (enemies[i] == null){
                enemies[i] = new PlaceHolder();
                this.add(enemies[i], Position.atIndex(i + 4));
            }
        }

        //...

    }

    public void end(boolean victory){

        // calculate xp, gold, etc

        for (Entity e : heroes){
            e.revive();
        }

        // revive cleric in case of mahoraga summon

        Arena.enableEnd();
    }

}
