package game.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

//Recruit new party members
@Route(value = "recruit", layout = MainLayout.class)
public class Recruit extends VerticalLayout {
    public Recruit() {
        add(new H2("Recrutamento de Herói"));

        TextField nome = new TextField("Nome");

        //MOCK: fixed class names, replace with the values of the class enum
        Select<String> classe = new Select<>();
        classe.setLabel("Classe");
        classe.setItems("Guerreiro", "Mago", "Arqueiro", "Clérigo");

        //MOCK: only shows a message, no hero is created or added to the guild
        Button recrutar = new Button("Recrutar",
            e -> Notification.show(nome.getValue() + " (" + classe.getValue() + ") recrutado!"));

        add(nome, classe, recrutar);
    }
}
