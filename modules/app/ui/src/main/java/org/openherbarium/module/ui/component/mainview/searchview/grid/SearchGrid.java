package org.openherbarium.module.ui.component.mainview.searchview.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.grid.GridStaticCellType;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.ui.component.mainview.searchview.grid.filter.FilterableColumn;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SearchGrid extends Grid<Metadata> {

    private static final String TAXON_NAME = "Taxon";
    private static final String RECORDER = "Finder";
    private static final String DETERMINER = "Bestimmer";
    private static final String DATE = "Datum";
    private static final String SELECTED = "Auswahl";

    private static final String MESSAGE_MAX_2_ENTITIES_SELECTABLE = "Maximal 2 Datens\u00E4tze ausw\u00E4hlbar";

    private final ListDataProvider<Metadata> dataProvider;
    private final Set<FilterableColumn> columnDecorators = new HashSet<>();
    private final Set<Metadata> selectedMetadatas = new HashSet<>();

    public SearchGrid(final String caption, final Collection<Metadata> items) {
        setCaption(caption);
        dataProvider = DataProvider.ofCollection(items);
        setDataProvider(dataProvider);
        setSelectionMode(SelectionMode.NONE);
        buildAndAddColumns();
        setSizeFull();
    }

    private void buildAndAddColumns() {
        addAllColumns();
        addFilterRow();
    }

    private void addAllColumns() {
        FilterableColumn column = new FilterableColumn(addColumn(Metadata::getTaxonName).setCaption(TAXON_NAME).setId(TAXON_NAME), true);
        columnDecorators.add(column);
        column = new FilterableColumn(addColumn(Metadata::getDate).setCaption(DATE).setId(DATE), false);
        columnDecorators.add(column);
        column = new FilterableColumn(addColumn(metadata -> metadata.getRecorder().getFirstName() + " " + metadata.getRecorder().getLastName()).setCaption(RECORDER).setId(RECORDER), true);
        columnDecorators.add(column);
        column = new FilterableColumn(addColumn(metadata -> metadata.getDeterminer().getFirstName() + " " + metadata.getDeterminer().getLastName()).setCaption(DETERMINER).setId(DETERMINER), true);
        columnDecorators.add(column);
        column = new FilterableColumn(addComponentColumn(metadata -> {
            final CheckBox checkBox = new CheckBox();
            checkBox.setData(metadata);
            checkBox.addValueChangeListener(event -> {
                final Boolean selected = event.getValue();
                final Metadata metadataFromCheckbox = (Metadata) checkBox.getData();
                if (selected) {
                    if (selectedMetadatas.size() < 2) {
                        selectedMetadatas.add(metadataFromCheckbox);
                    } else {
                        Notification.show(MESSAGE_MAX_2_ENTITIES_SELECTABLE);
                        checkBox.setValue(false);
                    }
                } else {
                    selectedMetadatas.remove(metadataFromCheckbox);
                }
                System.out.println(selectedMetadatas);
            });
            return checkBox;
        }).setCaption(SELECTED).setId(SELECTED), false);
        columnDecorators.add(column);
    }

    private void addFilterRow() {
        final HeaderRow filterRow = appendHeaderRow();
        filterRow.getCell(TAXON_NAME).setComponent(buildColumnFilterField(TAXON_NAME));
        filterRow.getCell(RECORDER).setComponent(buildColumnFilterField(RECORDER));
        filterRow.getCell(DETERMINER).setComponent(buildColumnFilterField(DETERMINER));
    }

    private TextField buildColumnFilterField(final String placeholder) {
        final TextField filterField = new TextField();
        filterField.setWidth("100%");
        filterField.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filterField.setPlaceholder(placeholder);
        filterField.addValueChangeListener(event -> rebuildAndExecuteFilters());
        return filterField;
    }

    private void rebuildAndExecuteFilters() {
        dataProvider.clearFilters();
        final HeaderRow filterRow = getHeaderRow(getHeaderRowCount() - 1);
        for (final FilterableColumn filterableColumn : columnDecorators) {
            final Column column = filterableColumn.getColumn();
            final ValueProvider<Metadata, String> valueProvider = column.getValueProvider();
            final HeaderCell headerCell = filterRow.getCell(column);
            if (headerCell.getCellType() != GridStaticCellType.WIDGET) {
                continue;
            }
            final Component filterComponent = headerCell.getComponent();
            if (columnDecorators.contains(filterableColumn) && filterableColumn.isFilterable() && filterComponent != null) {
                final TextField textField = (TextField) filterComponent;
                final String textFieldValue = textField.getValue();
                if (textFieldValue != null && !textFieldValue.isEmpty()) {
                    dataProvider.addFilter(valueProvider, value -> value.toLowerCase().contains(textFieldValue.toLowerCase()));
                }
            }
        }
        dataProvider.refreshAll();
    }

}
