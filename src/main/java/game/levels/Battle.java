package game.levels;

import game.entities.Entity;
import game.entities.creatures.Creature;
import game.entities.heroes.Hero;

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

        this.heroes = new Hero[4];
        this.enemies = new Creature[5];

        turn = count = active_pos = 0;

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

    public boolean add(Entity e, Position p){

        if (e instanceof Hero){
            return this.add(e, p);
        } else if (e instanceof Creature){
            return this.add(e, p);
        } else { // add case for placeholder entity
            return false;
        }

    }

    private boolean add(Hero h, Position p){

        if (p.onRight()){
            return false;
        }

        if (getFromPosition(p) != null){
            return false;
        }

        this.heroes[p.ordinal()] = h;
        this.count++;
        return true;

    }

    private boolean add(Creature c, Position p){

        if (p.onLeft()){
            return false;
        }

        if (getFromPosition(p) != null){
            return false;
        }

        this.enemies[p.ordinal() - 4] = c;
        this.count++;
        return true;

    }

    public boolean move(Entity e, Position p){

        if (e instanceof Hero){
            return this.move(e, p);
        } else if (e instanceof Creature){
            return this.move(e, p);
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

        if (aux != null){
            heroes[h_pos.ordinal()] = aux;
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

        if (aux != null){
            enemies[c_pos.ordinal() - 4] = aux;
        }

        enemies[p.ordinal() - 4] = c;
        return true;

    }

    public void checkVictory(){

        int victory = 0;

        for (int i = 0; !getFromPosition(Position.atIndex(i)).isAlive(); i++){
            if (i >= 3){
                victory = -1;
                break;
            }
        }

        for (int i = 4; !getFromPosition(Position.atIndex(i)).isAlive(); i++){
            if (i >= 8){
                victory = 1;
                break;
            }
        }

        if (victory == 1){
            end(true);
        } else if (victory == -1){
            end(false);
        }
    }

    private void passTurn(){

        active_pos = 0;

    }

    private void passActive(){

        active_pos++;

        if (active_pos > 8){
            passTurn();
        }

        Entity current = getFromPosition(Position.atIndex(active_pos));

        if ((current == null) || !(current.isAlive())){
            passActive();
        }

        checkVictory();

        current.action(this);

    }

    public void start(){

    }

    public void end(boolean victory){

    }

}
