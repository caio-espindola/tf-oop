package game.ui;

import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import game.entities.Stats;
import game.entities.heroes.Hero;
import game.exceptions.InventoryFullException;
import game.exceptions.NotEnoughGoldException;
import game.items.Gear;
import game.items.Item;
import game.items.Potion;
import game.items.Weapon;

//List guild items
@Route(value = "inventory", layout = MainLayout.class)
public class Inventory extends VerticalLayout {

    private static Grid<Item> grid;
    private static final ArrayList<Item> itemList = new ArrayList<>();
    private static final int capacity = 10;
    private static int count = 0;


    public Inventory() {
        add(new H2("Inventory"));

        grid = createItemGrid();
        add(grid);

    }

    private static void sellItem(Item i) {

        try {

            Shop.addGold(i.getValue());
            removeFromInventory(i);

        } catch (NotEnoughGoldException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addToInventory(Item item) throws InventoryFullException {
        if (count == capacity){
            throw new InventoryFullException("Inventário Cheio");
        }

        count++;
        itemList.add(item);
    }

    public static void removeFromInventory(Item item){
        itemList.remove(item);
        grid.setItems(itemList);
        count--;
    }

    public static boolean contains(Item item){
        return itemList.contains(item);
    }

    public static Item getIndex(int index){
        return itemList.get(index);
    }

    // GRID

    private static Grid<Item> createItemGrid(){

        Grid<Item> grid = new Grid<>(Item.class, false);

        grid.addColumn(toggleDetails(grid)).setWidth("80px").setFlexGrow(0).setFrozen(true);

        grid.addColumn(Item::getName).setHeader("Name");
        grid.addColumn(Item::getType).setHeader("Type");
        grid.addColumn(Item::getValue).setHeader("Value");

        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(itemDetailsRenderer());
        grid.setHeight("800px");

        grid.setItems(itemList);

        return grid;
    }

    // DETAIL RENDERER

    private static Renderer<Item> toggleDetails(Grid<Item> grid){
        return LitRenderer
                .<Item> of("""
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
                        i -> grid.setDetailsVisible(i,
                                !grid.isDetailsVisible(i)));
    }

    private static ComponentRenderer<ItemDetailLayout, Item> itemDetailsRenderer() {
        return new ComponentRenderer<>(ItemDetailLayout::new, ItemDetailLayout::setItem);
    }

    private static class ItemDetailLayout extends HorizontalLayout {

        private final VerticalLayout leftPanel = new VerticalLayout();
        private final VerticalLayout rightPanel = new VerticalLayout();
        private final VerticalLayout midPanel = new VerticalLayout();

        private final Button sell_button = new Button("Sell");
        private final Button equip_button = new Button("Equip");

        private static Paragraph details = new Paragraph();
        private static ComboBox<Hero> heroes = new ComboBox<>();

        public ItemDetailLayout(){

            leftPanel.add(new H2("Sell Item"));
            midPanel.add(new H2("Item Details"));
            rightPanel.add(new H2("Equip Item"));

            heroes.setItems(Guild.getHeroes());
            heroes.setItemLabelGenerator(Hero::getName);

            leftPanel.add(sell_button);
            midPanel.add(details);
            rightPanel.add(heroes, equip_button);

        }

        public void setItem(Item i){

            add(leftPanel);
            add(midPanel);

            sell_button.addClickListener(clickEvent -> Inventory.sellItem(i));

            if (i instanceof Potion){

                details.setText("Effect: " + i.description);

            } else if (i instanceof Gear){

                equip_button.addClickListener(clickEvent -> {
                    heroes.getValue().equip((Gear) i);
                    grid.setDetailsVisible(i, false);
                    Inventory.removeFromInventory(i);
                });

                add(rightPanel);

                details.setText(((Gear) i).getBonuses().toString());

            }



        }

    }

}
