package game.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

//Shared layout: top bar + side menu used by every screen after the gate
public class MainLayout extends AppLayout {
    public MainLayout() {
        H3 titulo = new H3("Cronicas de Javária");
        titulo.getStyle().set("margin", "0");
        addToNavbar(new DrawerToggle(), titulo);

        //each item points to the class of a screen, the path comes from its @Route
        SideNav menu = new SideNav();
        menu.addItem(new SideNavItem("Guild Hall", Guild.class));
        menu.addItem(new SideNavItem("Inventory", Inventory.class));
        menu.addItem(new SideNavItem("Shop", Shop.class));
        menu.addItem(new SideNavItem("Arena", Arena.class));
        menu.addItem(new SideNavItem("Chronicles", Chronicles.class));
        menu.addItem(new SideNavItem("Exit to Menu", MainView.class));
        addToDrawer(menu);
    }
}
