package game.ui;

import java.util.List;

import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
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

    private static List<Hero> heroes;

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

        Grid<Hero> grid = new Grid<>(Hero.class);

        grid.addColumn(Hero::getName).setHeader("Name");
        grid.addColumn(Hero::getLevel).setHeader("Level").setSortable(true);
        grid.addColumn(Hero::getMaxHP).setHeader("Max HP").setSortable(true);
        grid.addColumn(Hero::getMaxMana).setHeader("Max Mana");
        grid.addColumn(Hero::getDefense).setHeader("Defense");

        grid.setItems(heroes);
        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(heroDetailsRenderer());

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
        return new ComponentRenderer<>(() -> new HeroDetailLayout(), HeroDetailLayout::setHero);
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

        }

        public void setHero(Hero h){

            for (int i = 0; i < 4; i++){
                skills[i].setSummaryText(h.getAllSkills()[i].name);
                skills[i].setContent(new Paragraph(h.getAllSkills()[i].description));
            }

            for (int i = 0; i < 3; i++){
                gear[i].setSummaryText(h.getAllGear()[i].getName());
                Stats aux = h.getAllGear()[i].getBonuses();
                gear[i].setContent(new Paragraph(aux.toString()));
            }

        }

    }
}