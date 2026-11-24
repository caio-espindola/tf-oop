package game.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import game.entities.Stats;
import game.entities.heroes.*;

//Guild hall: list the party members and stats
@Route(value = "guild", layout = MainLayout.class)
public class Guild extends VerticalLayout {

    private static List<Hero> heroes = new ArrayList<>();

    private void initializeHeroes(){

        Hero[] aux = new Hero[4];

        for (Hero h : heroes){
            if (h != null){
                return; // is the constructor called every time the page is accessed?
            }
        }

        aux[0] = new Warrior(150, 20,  50, 1);
        aux[1] = new Archer(120, 45,  50, 1);
        aux[2] = new Mage(60,  100, 50, 1);
        aux[3] = new Cleric(90,  75,  50, 1);

        heroes = List.of(aux);
    }

    public Guild() {

        this.initializeHeroes();

        add(new H2("Guild Hall"));

        Grid<Hero> grid = new Grid<>(Hero.class, false);

        grid.addColumn(toggleDetails(grid)).setWidth("80px").setFlexGrow(0).setFrozen(true);

        grid.addColumn(Hero::getName).setHeader("Name");
        grid.addColumn(Hero::getLevel).setHeader("Level").setSortable(true);
        grid.addColumn(Hero::getMaxHP).setHeader("Max HP").setSortable(true);
        grid.addColumn(Hero::getMaxMana).setHeader("Max Mana");
        grid.addColumn(Hero::getDefense).setHeader("Defense");


        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(heroDetailsRenderer());

        grid.setItems(heroes);

        add(grid);
    }

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

        private Details s1, s2, s3, s4, w, a, m;

        private Details[] skills;
        private Details[] gear;

        public HeroDetailLayout(){

            leftPanel.add(new H2("Skills"));
            rightPanel.add(new H2("Gear"));

            s1 = new Details();
            s2 = new Details();
            s3 = new Details();
            s4 = new Details();

            w = new Details();
            a = new Details();
            m = new Details();

            skills = new Details[]{s1, s2, s3, s4};
            gear = new Details[]{w, a, m};

            Stream.of(skills).forEach(s -> leftPanel.add(s));
            Stream.of(gear).forEach(s -> rightPanel.add(s));

            add(leftPanel);
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

        }

    }
}