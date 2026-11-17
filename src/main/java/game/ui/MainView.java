package game.ui;

//import javax.swing.GroupLayout.Alignment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



// Main menu
@Route("") //keep it blank for the default "localhost" be the menu
public class MainView extends VerticalLayout {
    public MainView() {
        setAlignItems(Alignment.CENTER);

        //add(new H1("Cronicas de Javária"));
        H1 titulo = new H1("Cronicas de Javária");
        titulo.getStyle().set("margin-bottom", "3rem");
        add(titulo);
        add(new Button("New game ", e -> getUI().ifPresent(ui -> ui.navigate(Gate.class))));
        add(new Button("Load game ", e -> add("Jogo Carregado")));
        add(new Button("Options ", e -> add("Opções")));
    }
}
