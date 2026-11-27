package game.ui;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import game.entities.Entity;
import game.entities.actions.Action;
import game.entities.creatures.Creature;
import game.entities.creatures.CreatureAI;
import game.entities.heroes.Hero;
import game.exceptions.GameException;
import game.exceptions.InvalidActionException;
import game.exceptions.NotEnoughManaException;
import game.levels.Battle;
import game.levels.Mission;

import java.util.ArrayList;

//Battle arena
@Route(value = "arena", layout = MainLayout.class)
public class Arena extends VerticalLayout {

    private static Select<Mission> mission_select = new Select<>();
    private static MultiSelectListBox<String> battle_log = new MultiSelectListBox<>();
    private static Button end_battle_button = new Button();
    private static HorizontalLayout battle_field;
    private static Button confirm_mission_button = new Button();
    private static Paragraph active_entity_text = new Paragraph();
    private static HorizontalLayout battle_interface = new HorizontalLayout();
    private static Select<Action> action_selector = new Select<>();
    private static Select<Entity> target_selector = new Select<>();
    private static Button action_button = new Button("Confirm Action");

    public static Avatar[] entity_avatars = new Avatar[9];
    public static ArrayList<Entity> entities;
    private static Mission current_mission;
    private static Battle battle;
    private static Entity active_entity;

    public Arena() {
        add(new H2("Arena"));

        mission_select.setLabel("Select Mission:");
        mission_select.setItems(Mission.getAllMissions());
        mission_select.setItemLabelGenerator(i -> i.getName());
        mission_select.addValueChangeListener(vcl -> {
            current_mission = mission_select.getValue();
            confirm_mission_button.setEnabled(true);
        });

        confirm_mission_button.setText("Confirm Mission");
        confirm_mission_button.setEnabled(false);
        confirm_mission_button.addClickListener(clickEvent -> {
            setupBattle(current_mission);
            startBattle();
        });

        battle_log.setAriaLabel("Battle Log");
        battle_log.add("Battle Begins");

        end_battle_button.setText("End Battle");
        end_battle_button.setEnabled(false);
        end_battle_button.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(Summary.class)));

        for (int i = 0; i < 9; i++){
            entity_avatars[i] = new Avatar();
        }

        battle_field = createBattleField();
        battle_interface = createBattleInterface();

        add(mission_select, confirm_mission_button);
    }

    private HorizontalLayout createBattleInterface() {

        HorizontalLayout bi = new HorizontalLayout();

        action_selector.addValueChangeListener(vcl -> {
            action_button.setEnabled(! target_selector.isEmpty());
        });

        target_selector.addValueChangeListener(vcl -> {
            action_button.setEnabled(! action_selector.isEmpty());
        });

        action_button.setEnabled(false);
        action_button.addClickListener(clickEvent -> {
            action(action_selector.getValue(), target_selector.getValue());
        });

        bi.add(active_entity_text, action_selector, target_selector, action_button);

        return bi;

    }

    public boolean action(Action action, Entity target) {

        boolean result = false;

        try {

            result = action.act(battle, active_entity, target);
            String success = result ? "successful." : "unsuccessful.";

            log(active_entity.getName() + " used " + action.name + " on " + target.getName() + " and was " + success);

            battle.passActive();

        } catch (GameException e){

            Notification notif = Notification.show(e.getMessage());
            notif.setPosition(Notification.Position.MIDDLE);

        } catch (Exception e){

            Notification notif = Notification.show("Unknown Error: " + e.getMessage());
            notif.setPosition(Notification.Position.MIDDLE);

        }

        return result;
    }

    public static void enableEnd() {
        end_battle_button.setEnabled(true);
    }

    private void startBattle() {

        removeAll();
        add(new H2("Battle"));

        add(battle_field,
            battle_interface,
            end_battle_button,
            battle_log);

        battle = new Battle();

        for (int i = 0; i < 9; i++){
            boolean success = battle.add(entities.get(i), Battle.Position.atIndex(i));
            System.out.println("Add " + entities.get(i) + " success: " + success);
        }

        battle.start();

        battle.passActive();

    }

    public static void setActiveEntity(Entity e){
        active_entity = e;

        active_entity_text.setText("Active Entity: " + e.getName());

        for (int i = 0; i < 9; i++){

            if (entities.get(i).equals(e)){
                entity_avatars[i].setColorIndex(2);
            } else {
                entity_avatars[i].setColorIndex(0);
            }
        }

        if (e instanceof Creature){
            CreatureAI.act(e, battle);
        } else {

            action_selector.setItems(e.action());

        }


    }

    private static void setupBattle(Mission currentMission) {

        ArrayList<Hero> heroes = Guild.getHeroes();
        ArrayList<Entity> enemies = currentMission.getEnemies();

        ArrayList<Entity> aux = new ArrayList<>(heroes);
        aux.addAll(enemies);

        for (int i = 0; i < 9; i++){
            entity_avatars[i].setName(aux.get(i).getName() + "lv. " + aux.get(i).getLevel());
        }

        entities = aux;

        target_selector.setItems(entities);
    }

    private static HorizontalLayout createBattleField() {

        HorizontalLayout bf = new HorizontalLayout();

        HorizontalLayout left = new HorizontalLayout();
        HorizontalLayout right = new HorizontalLayout();

        VerticalLayout left_back = new VerticalLayout();
        VerticalLayout left_front = new VerticalLayout();
        VerticalLayout right_back = new VerticalLayout();
        VerticalLayout right_front = new VerticalLayout();

        left_back.add(entity_avatars[0], entity_avatars[1]);
        left_front.add(entity_avatars[2], entity_avatars[3]);

        right_back.add(entity_avatars[4], entity_avatars[5], entity_avatars[6]);
        right_front.add(entity_avatars[7], entity_avatars[8]);

        left.add(left_back, left_front);
        right.add(right_front, right_back);

        bf.add(left, right);

        return bf;
    }

    public static boolean setAvatarAtPos(Entity e, Battle.Position p){

        if (p.onLeft() && e instanceof Creature){
            return false;
        } else if (p.onRight() && e instanceof Hero){
            return false;
        }

        int index = p.ordinal();

        entity_avatars[index].setName(e.getName() + "lvl. " + e.getLevel());

        return true;

    }

    public static void log(String msg){

    }
}
