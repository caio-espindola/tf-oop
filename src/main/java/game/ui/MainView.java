package game.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


// Main menu
@Route("") //keep it blank for the default "localhost" be the menu
public class MainView extends VerticalLayout {
    public MainView() {
        add(new Button("New game ", e -> add("Novo jogo!")));
        add(new Button("Load game ", e -> add("Jogo Carregado")));
        add(new Button("Options ", e -> add("Opções")));
    }
}
