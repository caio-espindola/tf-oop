package game.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import game.entities.Stats;
import game.entities.heroes.*;
import game.items.Weapon;

//Guild hall: list the party members and stats
@Route(value = "guild", layout = MainLayout.class)
public class Guild extends VerticalLayout {

    private static ArrayList<Hero> heroes = new ArrayList<>();
    private static Grid<Hero> grid;
    private static Dialog level_up_dialog;

    public Guild() {

        add(new H2("Guild Hall"));

        // HERO GRID

        grid = createHeroGrid();
        add(grid);

        // LEVEL UP POPUP

        level_up_dialog = new Dialog("Level Up Hero");
        add(level_up_dialog);
    }

    public static ArrayList<Hero> getHeroes(){
        return heroes;
    }

    private static void initializeHeroes(){

        Hero[] aux = new Hero[4];

        for (Hero h : heroes){
            if (h != null){
                return; // is the constructor called every time the page is accessed?
            }
        }

        heroes.add(new Warrior(150, 20,  50, 1));
        heroes.add(new Archer(120, 45,  50, 1));
        heroes.add(new Mage(60,  100, 50, 1));
        heroes.add(new Cleric(90,  75,  50, 1));
    }

    private static Grid<Hero> createHeroGrid(){

        Grid<Hero> grid = new Grid<>(Hero.class, false);

        grid.addColumn(toggleDetails(grid)).setWidth("80px").setFlexGrow(0).setFrozen(true);

        grid.addColumn(Hero::getName).setHeader("Name");
        grid.addColumn(Hero::getLevel).setHeader("Level").setSortable(true);
        grid.addColumn(Hero::getMaxHP).setHeader("Max HP").setSortable(true);
        grid.addColumn(Hero::getMaxMana).setHeader("Max Mana");
        grid.addColumn(Hero::getDefense).setHeader("Defense");

        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(heroDetailsRenderer());
        grid.setHeight("800px");

        initializeHeroes();
        grid.setItems(heroes);

        return grid;
    }

    // LEVEL UP DIALOG

    private static void invalidAllocationMessage(){

        Notification notif = new Notification();

        Notification.show("Invalid Point Allocation");
        notif.setPosition(Notification.Position.MIDDLE);
    }

    private static IntegerField[] createAllocationFields(){
        IntegerField str = new IntegerField();
        IntegerField dex = new IntegerField();
        IntegerField intl = new IntegerField();
        IntegerField pie = new IntegerField();
        IntegerField[] fields = {str, dex, intl, pie};
        str.setLabel("STR");
        dex.setLabel("DEX");
        intl.setLabel("INT");
        pie.setLabel("PIE");

        return fields;
    }

    private static HorizontalLayout createAllocationButtons(IntegerField[] fields, Hero h){

        HorizontalLayout buttons = new HorizontalLayout();
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        buttons.add(cancel, confirm);

        int max_alloc = h.MAX_ALLOCS_BY_LEVEL[h.getLevel()];

        cancel.addClickListener(clickEvent -> level_up_dialog.close());

        confirm.addClickListener(clickEvent -> {

            int current_alloc = Stream.of(fields).mapToInt(f -> f.getValue()).sum();

            if (current_alloc != max_alloc){

                invalidAllocationMessage();

            } else {

                h.levelUp(new Stats(fields[0].getValue(),
                                    fields[1].getValue(),
                                    fields[2].getValue(),
                                    fields[3].getValue()));

                level_up_dialog.close();
                grid.setDetailsVisible(h, false);
            }
        });

        return buttons;

    }

    private static void levelUpHero(Hero h) {

        level_up_dialog.open();

        int max_alloc = h.MAX_ALLOCS_BY_LEVEL[h.getLevel()];
        IntegerField[] fields = createAllocationFields();
        HorizontalLayout buttons = createAllocationButtons(fields, h);

        VerticalLayout fields_layout = new VerticalLayout();

        for (IntegerField f : fields){
            f.setStepButtonsVisible(true);
            f.setValue(0);
            f.setMin(0);

            fields_layout.add(f);
        }

        level_up_dialog.removeAll();
        level_up_dialog.add(new Paragraph("Points to be Allocated: " + max_alloc), fields_layout, buttons);
    }

    // DETAIL RENDERER

    private static Renderer<Hero> toggleDetails(Grid<Hero> grid){
        return LitRenderer
                .<Hero> of("""
                <vaadin-button
                    theme="tertiary icon"
                    aria-label="Toggle details"
                    aria-expanded="${model.detailsOpened ? 'true' : 'false'}"
                    @click="${handleClick}"
                >
                    <vaadin-icon
                    .icon="${model.detailsOpened ? 'vaadin:angle-down' : 'vaadin:angle-right'}"
                    ></vaadin-icon>
                </vaadin-button>
            """)
                .withFunction("handleClick",
                        h -> grid.setDetailsVisible(h,
                                !grid.isDetailsVisible(h)));
    }

    private static ComponentRenderer<HeroDetailLayout, Hero> heroDetailsRenderer() {
        return new ComponentRenderer<>(HeroDetailLayout::new, HeroDetailLayout::setHero);
    }

    private static class HeroDetailLayout extends HorizontalLayout {

        private final VerticalLayout leftPanel = new VerticalLayout();
        private final VerticalLayout rightPanel = new VerticalLayout();
        private final VerticalLayout midPanel = new VerticalLayout();

        private Details s1, s2, s3, s4, w, a, m;
        private Paragraph str, dex, intl, pie, xp;
        private Button level_up;

        private Details[] skills, gear;
        private Paragraph[] stats;

        public HeroDetailLayout(){

            leftPanel.add(new H2("Skills"));
            midPanel.add(new H2("Stats"));
            rightPanel.add(new H2("Gear"));

            s1 = new Details();
            s2 = new Details();
            s3 = new Details();
            s4 = new Details();

            w = new Details();
            a = new Details();
            m = new Details();

            str = new Paragraph();
            dex = new Paragraph();
            intl = new Paragraph();
            pie = new Paragraph();
            xp = new Paragraph();

            level_up = new Button("Level Up");
            level_up.setEnabled(false);

            skills = new Details[]{s1, s2, s3, s4};
            gear = new Details[]{w, a, m};
            stats = new Paragraph[]{str, dex, intl, pie, xp};

            Stream.of(skills).forEach(s -> leftPanel.add(s));
            Stream.of(gear).forEach(s -> rightPanel.add(s));
            Stream.of(stats).forEach(s -> midPanel.add(s));

            midPanel.add(level_up);

            add(leftPanel);
            add(midPanel);
            add(rightPanel);

        }

        public void setHero(Hero h){

            for (int i = 0; i < 4; i++){

                String skill_name = (h.getAllSkills()[i] == null) ? "Locked" : h.getAllSkills()[i].name;
                String skill_desc = (h.getAllSkills()[i] == null) ? "This Skill is still locked" : h.getAllSkills()[i].description;

                skills[i].setSummaryText(skill_name);
                skills[i].add(new Paragraph(skill_desc));
            }

            for (int i = 0; i < 3; i++){

                String gear_name = (h.getAllGear()[i] == null) ? "Empty" : h.getAllGear()[i].getName();
                String gear_bonuses = (h.getAllGear()[i] == null) ? "" : h.getAllGear()[i].getBonuses().toString();

                gear[i].setSummaryText(gear_name);
                gear[i].add(new Paragraph(gear_bonuses));
            }

            for (int i = 0; i < 4; i++){

                String stat_name = new String[]{"STR", "DEX", "INT", "PIE"}[i];
                int stat_val = h.getStats().toArray()[i];

                stats[i].setText(stat_name + ": " + stat_val);

            }

            int xp_val = h.getXP();
            stats[4].setText("XP: " + xp_val + " / " + 100*h.getLevel()); // TODO: fix this bullshit

            level_up.setEnabled(h.canLevelUp());
            level_up.addClickListener(clickEvent -> Guild.levelUpHero(h));

        }

    }
}