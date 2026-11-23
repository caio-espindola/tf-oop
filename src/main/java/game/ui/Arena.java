package game.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

//Battle arena
@Route(value = "arena", layout = MainLayout.class)
public class Arena extends VerticalLayout {
    public Arena() {
        add(new H2("Arena de Batalha"));



        //MOCK: fixed mission names, replace with the real missions
        Select<String> missao = new Select<>();
        missao.setLabel("Missão");
        missao.setItems("Vila dos Goblins", "Cripta Fora de Escopo", "Covil do Dragão", "Masmorras do Heap");

        add(missao);

        //MOCK: fixed log lines, the real log comes from the battle turn by turn
        add(new Paragraph("Turno 1: Aldric ataca Goblin e causa 9 de dano."));
        add(new Paragraph("Turno 1: Goblin ataca Aldric e causa 2 de dano."));
        add(new Paragraph("Turno 2: Lyra usa Bola de Fogo em Goblin e causa 22 de dano."));

        add(new Button("Encerrar batalha", e -> getUI().ifPresent(ui -> ui.navigate(Summary.class))));
    }
}
