package game.ui;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

//Guild hall: list the party members and stats
@Route(value = "guild", layout = MainLayout.class)
public class Guild extends VerticalLayout {

    //MOCK: temporary type just to fill the grid, replace with the real hero class
    private record HeroMock(String nome, int nivel, int vida, int ataque, int defesa) {}

    //MOCK: fixed heroes, replace with the heroes of the guild
    private static final List<HeroMock> HEROIS = List.of(
        new HeroMock("Aldric",  3, 120, 14, 12),
        new HeroMock("Lyra",  5, 60, 22, 4),
        new HeroMock("Fenn",  2, 80, 16, 6),
        new HeroMock("Mirel",  4, 90, 10, 9));

    public Guild() {
        add(new H2("Salão da Guilda"));

        Grid<HeroMock> grid = new Grid<>();
        grid.addColumn(HeroMock::nome).setHeader("Nome");
        grid.addColumn(HeroMock::nivel).setHeader("Nível").setSortable(true);
        grid.addColumn(HeroMock::vida).setHeader("Vida").setSortable(true);
        grid.addColumn(HeroMock::ataque).setHeader("Ataque");
        grid.addColumn(HeroMock::defesa).setHeader("Defesa");
        grid.setItems(HEROIS);
        add(grid);
    }
}