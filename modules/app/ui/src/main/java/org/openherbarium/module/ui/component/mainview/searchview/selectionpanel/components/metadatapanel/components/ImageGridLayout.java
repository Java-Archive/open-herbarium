package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import org.openherbarium.module.backend.metadataservice.api.Metadata;

import java.util.HashMap;
import java.util.Map;

import static org.openherbarium.module.ui.component.mainview.searchview.SearchView.NOT_YET_IMPLEMENTED;


public class ImageGridLayout extends GridLayout {

    public static final String FULLSCREEN_CAPTION = "Vollbild";
    public static final String REMOVE_CAPTION = "Entfernen";

    private static final Map<ButtonTask, VaadinIcons> ICON_BY_BUTTONTASK = new HashMap<>();

    private enum ButtonTask {
        FULLSCREEN,
        REMOVE;
    }

    static {
        ICON_BY_BUTTONTASK.put(ButtonTask.FULLSCREEN, VaadinIcons.EXPAND_FULL);
        ICON_BY_BUTTONTASK.put(ButtonTask.REMOVE, VaadinIcons.CLOSE);
    }

    private Button fullscreenButton = new Button(FULLSCREEN_CAPTION);
    private Button removeButton = new Button(REMOVE_CAPTION);

    public ImageGridLayout(Metadata metadata) {
        super(2, 8);
        addStyleName("outlined");
        final Image image = new Image(NOT_YET_IMPLEMENTED, new ClassResource("test.PNG"));
        image.setSizeFull();
        setSizeFull();
        configureButtons(metadata);
        fullscreenButton.addClickListener(clickEvent -> Notification.show(NOT_YET_IMPLEMENTED));
        addComponent(fullscreenButton, 0, 0);
        addComponent(removeButton, 0, 1);
        final Label label = new Label(image.getCaption());
        label.setSizeFull();
        addComponent(label, 1, 0, 1, 7);
    }

    private void configureButtons(final Metadata metadata) {
        configureButton(fullscreenButton, metadata, ButtonTask.FULLSCREEN);
        configureButton(removeButton, metadata, ButtonTask.REMOVE);
    }

    private void configureButton(final Button button, final Metadata metadata, final ButtonTask task) {
        button.setWidth(80, Unit.PERCENTAGE);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.setIcon(ICON_BY_BUTTONTASK.get(task));
        button.setData(metadata);
    }

    public Button getRemoveButton() {
        return removeButton;
    }
}
