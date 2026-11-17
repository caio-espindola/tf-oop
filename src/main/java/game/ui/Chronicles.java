package game.ui;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//Guild chronicles: stats and battle history
@Route(value = "chronicles", layout = MainLayout.class)
public class Chronicles extends VerticalLayout {

    //MOCK: temporary type just to fill the grid, replace with the real battle record
    private record BattleMock(String data, String herois, String missao, String resultado, int turnos) {}

    //MOCK: fixed history, replace with the battle history of the guild
    private static final List<BattleMock> HISTORICO = List.of(
        new BattleMock("10/11/2026", "Aldric, Lyra", "Vila dos Goblins", "Vitória", 4),
        new BattleMock("12/11/2026", "Fenn", "Cripta Fora de Escopo", "Derrota", 6),
        new BattleMock("14/11/2026", "Aldric, Mirel", "Vila dos Goblins", "Fuga", 3));

    public Chronicles() {
        add(new H2("Crônicas da Guilda"));

        //MOCK: fixed numbers, the real ones are calculated from the heroes and the history
        add(new Paragraph("Nível médio dos heróis: 3,5"));
        add(new Paragraph("Total de ouro da guilda: 240"));
        add(new Paragraph("Taxa de vitórias: 33%"));

        Grid<BattleMock> grid = new Grid<>();
        grid.addColumn(BattleMock::data).setHeader("Data");
        grid.addColumn(BattleMock::herois).setHeader("Heróis");
        grid.addColumn(BattleMock::missao).setHeader("Missão");
        grid.addColumn(BattleMock::resultado).setHeader("Resultado");
        grid.addColumn(BattleMock::turnos).setHeader("Turnos");
        grid.setItems(HISTORICO);

        add(grid);
    }
}
