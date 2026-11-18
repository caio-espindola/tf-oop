package game.ui;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//Item shop: list items for sale and buy them with guild gold
@Route(value = "shop", layout = MainLayout.class)
public class Shop extends VerticalLayout {

    //MOCK: temporary type just to fill the grid, replace with the real item classes
    private record ItemMock(String nome, String tipo, String efeito, int preco) {}

    //MOCK: fixed stock, replace with the items available in the shop
    private static final List<ItemMock> ESTOQUE = List.of(
        new ItemMock("Espada Longa", "Arma", "+9 ataque", 90),
        new ItemMock("Arco Élfico", "Arma", "+8 ataque", 80),
        new ItemMock("Armadura de Placas", "Armadura", "+10 defesa", 120),
        new ItemMock("Amuleto da Sorte", "Amuleto", "+3 todos os atributos", 150),
        new ItemMock("Poção de Vida", "Poção", "+30 vida", 15),
        new ItemMock("Poção de Mana", "Poção", "+20 mana", 15));

    public Shop() {
        add(new H2("Loja de Itens"));

        //MOCK: fixed gold, the real value comes from the guild
        add(new Paragraph("Ouro da guilda: 240"));

        Grid<ItemMock> grid = new Grid<>();
        grid.addColumn(ItemMock::nome).setHeader("Item");
        grid.addColumn(ItemMock::tipo).setHeader("Tipo");
        grid.addColumn(ItemMock::efeito).setHeader("Efeito");
        grid.addColumn(ItemMock::preco).setHeader("Preço (ouro)").setSortable(true);
        //MOCK: buying only shows a message, it does not spend gold or add to the inventory yet
        grid.addComponentColumn(item -> new Button("Comprar",
            e -> Notification.show(item.nome() + " comprado por " + item.preco() + " de ouro")));
        grid.setItems(ESTOQUE);

        add(grid);
    }
}
