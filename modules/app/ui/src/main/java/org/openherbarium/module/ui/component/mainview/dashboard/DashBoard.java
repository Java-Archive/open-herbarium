package org.openherbarium.module.ui.component.mainview.dashboard;

import javax.annotation.PostConstruct;

import org.openherbarium.module.api.HasLogger;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;

/**
 *
 */
public class DashBoard extends Composite implements HasLogger {

  @PostConstruct
  private void postConstruct() {
    setCompositionRoot(new Label("Hello Open Herbarium"));
  }


}
