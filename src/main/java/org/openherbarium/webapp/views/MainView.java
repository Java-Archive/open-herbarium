package org.openherbarium.webapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

//@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "OpenHerbarium", shortName = "OpenHerbarium")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@Route("")
public class MainView
    extends AppLayout {

  public MainView() {
    setPrimarySection(AppLayout.Section.DRAWER);
    Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
    img.setHeight("44px");
    addToNavbar(new DrawerToggle(), img);
    Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
    tabs.setOrientation(Tabs.Orientation.VERTICAL);
    addToDrawer(tabs);
  }
}
