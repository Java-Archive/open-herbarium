package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Composite;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import static org.openherbarium.module.ui.component.mainview.searchview.SearchView.NOT_YET_IMPLEMENTED;

public class TaskPanel extends Composite {

  private static final String TASKS = "Aufgaben";
  private static final String RUN = "Los!";

  private enum Task {
    COMPARE("Auswahl vergleichen");

    private final String readableName;

    Task(String readableName) {
      this.readableName = readableName;
    }

    public String getReadableName() {
      return readableName;
    }
  }

  private HorizontalLayout contentLayout = new HorizontalLayout();

  private FormLayout tasksBoxLayout = new FormLayout();
  private Button goButton = new Button();

  public TaskPanel() {
    final ComboBox<Task> tasksComboBox = new ComboBox<>();
    tasksBoxLayout.addComponents(tasksComboBox);
    configureTaskComboBox(tasksComboBox);
    configureGoButton();
    buildContentLayout();
    setCompositionRoot(contentLayout);
    tasksBoxLayout.setWidth(100, Unit.PERCENTAGE);
    setWidth(100, Unit.PERCENTAGE);
  }

  private void configureTaskComboBox(ComboBox<Task> tasksComboBox) {
    tasksComboBox.setCaption(TASKS);
    tasksComboBox.setItems(Task.values());
    tasksComboBox.setWidth(100, Unit.PERCENTAGE);
    tasksComboBox.setTextInputAllowed(false);
    tasksComboBox.setEmptySelectionAllowed(false);
    tasksComboBox.setValue(Task.COMPARE);
    tasksComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Task>) Task::getReadableName);
    tasksComboBox.addStyleName(ValoTheme.COMBOBOX_SMALL);
  }

  private void configureGoButton() {
    goButton.setIcon(VaadinIcons.CHEVRON_CIRCLE_RIGHT, RUN);
    goButton.setSizeUndefined();
    goButton.addStyleName(ValoTheme.BUTTON_SMALL);
    goButton.addClickListener(event -> Notification.show(NOT_YET_IMPLEMENTED));
  }

  private void buildContentLayout() {
    contentLayout.setWidth(100, Unit.PERCENTAGE);
    contentLayout.setMargin(false);
    contentLayout.setSpacing(false);
    contentLayout.addComponents(tasksBoxLayout, goButton);
    contentLayout.setComponentAlignment(tasksBoxLayout, Alignment.MIDDLE_LEFT);
    contentLayout.setComponentAlignment(goButton, Alignment.MIDDLE_CENTER);
    contentLayout.setExpandRatio(tasksBoxLayout, 0.8f);
    contentLayout.setExpandRatio(goButton, 0.2f);
  }
}
