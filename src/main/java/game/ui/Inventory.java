package game.ui;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//List guild items
@Route(value = "inventory", layout = MainLayout.class)
public class Inventory extends VerticalLayout {

    //MOCK: temporary type just to fill the grid, replace with the real item classes
    private record ItemMock(String nome, String tipo, String efeito, int valor) {}

    //MOCK: fixed items, replace with the inventory of the heroes and the shop
    private static final List<ItemMock> ITENS = List.of(
        new ItemMock("Espada Curta", "Arma", "+5 ataque", 40),
        new ItemMock("Cajado de Carvalho", "Arma", "+7 ataque", 60),
        new ItemMock("Cota de Malha", "Armadura", "+6 defesa", 55),
        new ItemMock("Poção de Vida", "Poção", "+30 vida", 15),
        new ItemMock("Poção de Mana", "Poção", "+20 mana", 15));

    public Inventory() {
        add(new H2("Inventário / Arsenal"));

        Grid<ItemMock> grid = new Grid<>();
        grid.addColumn(ItemMock::nome).setHeader("Item");
        grid.addColumn(ItemMock::tipo).setHeader("Tipo");
        grid.addColumn(ItemMock::efeito).setHeader("Efeito");
        grid.addColumn(ItemMock::valor).setHeader("Valor (ouro)");
        grid.setItems(ITENS);

        add(grid);
    }
}
