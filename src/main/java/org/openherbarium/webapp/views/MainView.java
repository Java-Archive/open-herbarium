package org.openherbarium.webapp.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.HashMap;
import java.util.Map;

//@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
//@PWA(name = "OpenHerbarium", shortName = "OpenHerbarium")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView
    extends AppLayout implements BeforeEnterObserver, HasLogger {

  private Tabs                                 tabs                  = new Tabs();
  private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();


  public MainView() {
    addMenuTab("Main", DefaultView.class);
    addMenuTab("Admin", AdminView.class);
    addMenuTab("Dashboard", DashboardView.class);
    tabs.setOrientation(Tabs.Orientation.VERTICAL);
    addToDrawer(tabs);
    addToNavbar(new DrawerToggle());
  }
  private void addMenuTab(String label, Class<? extends Component> target) {
    Tab tab = new Tab(new RouterLink(label, target));
    navigationTargetToTab.put(target, tab);
    tabs.add(tab);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    tabs.setSelectedTab(navigationTargetToTab.get(event.getNavigationTarget()));
  }

}
