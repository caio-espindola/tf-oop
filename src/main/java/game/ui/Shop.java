package game.ui;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import game.exceptions.NotEnoughGoldException;

//Item shop: list items for sale and buy them with guild gold
@Route(value = "shop", layout = MainLayout.class)
public class Shop extends VerticalLayout {

    private static int gold = 0;

    public Shop(){

    }

    public static int getGold(){
        return gold;
    }

    public static void addGold(int g) throws NotEnoughGoldException {

        if (gold + g < 0){
            throw new NotEnoughGoldException("Insufficient gold");
        }

        gold += g;
    }

    public static void removeGold(int g) throws NotEnoughGoldException {
        addGold(-1 * g);
    }
}
