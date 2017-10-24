package org.openherbarium.module.ui.component.mainview;

import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.property.PropertyService;
import org.openherbarium.module.ui.component.mainview.dashboard.DashBoard;
import org.openherbarium.module.ui.component.menu.MenuComponent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import static com.vaadin.ui.themes.ValoTheme.MENU_ROOT;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.cssLayoutID;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.horizontalLayoutID;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.verticalLayoutID;
import static org.rapidpm.ddi.DI.activateDI;

/**
 *
 */
public class MainView extends Composite implements HasLogger {

  public static final String CONTENT_LAYOUT = "mainview.contentlayout";
  public static final String MENU_LAYOUT = "mainview.menulayout";
  public static final String MAIN_LAYOUT = "mainview.mainlayout";

  @Inject
  private PropertyService propertyService;

  private String property(String key) {
    return propertyService.resolve(key);
  }


  final CssLayout contentLayout = new CssLayout();
  final VerticalLayout menuLayout = new VerticalLayout();
  final HorizontalLayout mainLayout = new HorizontalLayout(menuLayout, contentLayout);

  @PostConstruct
  public void postConstruct() {
    contentLayout.setId(cssLayoutID().apply(MainView.class, CONTENT_LAYOUT));
    contentLayout.addComponent(activateDI(DashBoard.class));
    contentLayout.setSizeFull();


    menuLayout.setId(verticalLayoutID().apply(MainView.class, MENU_LAYOUT));
    menuLayout.setStyleName(MENU_ROOT);
    // menuLayout.setWidth(100 , Sizeable.Unit.PERCENTAGE);
    // menuLayout.setHeight(100 , Sizeable.Unit.PERCENTAGE);
    menuLayout.setSizeUndefined();
    menuLayout.addComponent(activateDI(new MenuComponent(contentLayout)));


    mainLayout.setId(horizontalLayoutID().apply(MainView.class, MAIN_LAYOUT));
    mainLayout.setSizeFull();
    // mainLayout.setExpandRatio(menuLayout , 0.20f);
    mainLayout.setExpandRatio(contentLayout, 1.0f);

    setCompositionRoot(mainLayout);
  }

}
