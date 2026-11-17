package game.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//Most combat summary, show how much gold and items were rewarded
@Route(value = "summary", layout = MainLayout.class)
public class Summary extends VerticalLayout {
    public Summary() {
        add(new H2("Resumo da Batalha"));

        //MOCK: fixed result and rewards, replace with the record of the battle
        add(new Paragraph("Resultado: Vitória"));
        add(new Paragraph("Turnos: 2"));
        add(new Paragraph("Recompensas: 50 de experiência e 30 de ouro"));

        add(new Button("Voltar ao salão", e -> getUI().ifPresent(ui -> ui.navigate(Guild.class))));
    }
}
