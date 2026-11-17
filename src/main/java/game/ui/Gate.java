package game.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

//Gate: Screen after new game. Create player name and guild name
@Route("gate")
public class Gate extends VerticalLayout {
    public Gate() {
        setAlignItems(Alignment.CENTER);

        add(new H2("Portão da Guilda"));

        //MOCK: the values typed here are not saved anywhere yet
        TextField jogador = new TextField("Nome do jogador");
        TextField guilda = new TextField("Nome da guilda");
        add(jogador, guilda);

        add(new Button("Entrar na guilda", e -> {

                if(jogador.isEmpty()||guilda.isEmpty()) {
                Notification.show("Preencha o nome do jogador e o nome da guilda");
                return;
                }

            getUI().ifPresent(ui -> ui.navigate(Guild.class));
        }));
            
            add(new Button("Voltar", e -> getUI().ifPresent(ui -> ui.navigate(MainView.class))));
            
    }
}
