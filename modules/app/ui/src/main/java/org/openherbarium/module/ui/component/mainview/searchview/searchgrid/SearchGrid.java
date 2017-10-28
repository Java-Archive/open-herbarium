package org.openherbarium.module.ui.component.mainview.searchview.searchgrid;

import static org.openherbarium.module.backend.metadataservice.api.Metadata.DATE;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.DETERMINER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.RECORDER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.TAXON_NAME;
import static org.openherbarium.module.ui.component.mainview.searchview.SearchView.MAX_SELECTED_METADATA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.Scan;
import org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist.SelectionListSubscriber;
import org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist.VaadinSelectionListSubject;
import org.openherbarium.module.ui.component.mainview.searchview.searchgrid.dataprovider.MetadataDataProvider;
import org.openherbarium.module.ui.component.mainview.searchview.searchgrid.filter.FilterableColumn;
import org.openherbarium.module.ui.component.mainview.searchview.searchgrid.filter.TimeSpanFilter;
import com.vaadin.data.ValueProvider;
import com.vaadin.shared.ui.grid.GridStaticCellType;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.TextRenderer;
import com.vaadin.ui.themes.ValoTheme;

public class SearchGrid extends Grid<Metadata>
    implements VaadinSelectionListSubject<Metadata>, SelectionListSubscriber, HasLogger {

  private static final String SCANS = "Scans"; //TODO use property Service -> i18n
  private static final String SELECTED = "Auswahl"; //TODO use property Service -> i18n

  @Inject
  private MetadataService metadataService;

  private static final String MESSAGE_MAX_2_ENTITIES_SELECTABLE =
      "Maximal erlaubte Anzahl an Datens\u00E4tzen ausgew\u00E4hlt"; //TODO use property Service -> i18n

  private MetadataDataProvider dataProvider = null;
  private final Set<FilterableColumn> columnDecorators = new HashSet<>();
  private List<Metadata> selectedMetadatas = new ArrayList<>();

  private List<SelectionListSubscriber> subscribers = new ArrayList<>();

  private void buildAndAddColumns() {
    addAllColumns();
    addFilterRow();
  }

  private void addAllColumns() {
    FilterableColumn columnDecorator = new FilterableColumn(
        addColumn(Metadata::getTaxonName).setCaption(TAXON_NAME).setId(TAXON_NAME), true);
    columnDecorators.add(columnDecorator);
    columnDecorator =
        new FilterableColumn(addColumn(Metadata::getDate).setCaption(DATE).setId(DATE), true);
    columnDecorators.add(columnDecorator);
    columnDecorator =
        new FilterableColumn(addColumn(metadata -> metadata.getRecorder().getFirstName() + " "
            + metadata.getRecorder().getLastName()).setCaption(RECORDER).setId(RECORDER), true);
    columnDecorators.add(columnDecorator);
    columnDecorator = new FilterableColumn(
        addColumn(metadata -> metadata.getDeterminer().getFirstName() + " "
            + metadata.getDeterminer().getLastName()).setCaption(DETERMINER).setId(DETERMINER),
        true);
    columnDecorators.add(columnDecorator);
    columnDecorator = new FilterableColumn(addColumn(Metadata::getScans).setCaption(SCANS)
        .setId(SCANS).setSortable(false).setExpandRatio(6).setRenderer(scans -> {
          final StringBuilder sb = new StringBuilder();
          if (scans != null) {
            final List<Scan> scanList = new ArrayList<>(scans);
            Collections.sort(scanList);
            for (final Scan scan : scanList) {
              sb.append(scan.getName()).append(", ");
            }
            return sb.substring(0, sb.length() - 2);
          }
          return "";
        }, new TextRenderer("")), false);
    columnDecorators.add(columnDecorator);
    columnDecorator = new FilterableColumn(addComponentColumn(metadata -> {
      final CheckBox checkBox = new CheckBox();
      checkBox.setData(metadata);
      checkBox.addValueChangeListener(event -> {
        if (!event.isUserOriginated()) {
          return;
        }
        final Boolean selected = event.getValue();
        final Metadata metadataFromCheckbox = (Metadata) checkBox.getData();
        boolean selectionChanged = false;
        if (selected) {
          if (selectedMetadatas.size() < MAX_SELECTED_METADATA) {
            selectedMetadatas.add(metadataFromCheckbox);
            selectionChanged = true;
          } else {
            Notification.show(MESSAGE_MAX_2_ENTITIES_SELECTABLE);
            checkBox.setValue(false);
          }
        } else {
          if (selectedMetadatas.contains(metadataFromCheckbox)) {
            selectedMetadatas.remove(metadataFromCheckbox);
            selectionChanged = true;
          }
        }
        if (selectionChanged) {
          notifySubscribersAboutUpdatedList(new ArrayList<Metadata>(selectedMetadatas));
        }
      });
      return checkBox;
    }).setSortable(false).setCaption("").setId(SELECTED), false);
    columnDecorators.add(columnDecorator);
  }

  private void addFilterRow() {
    final HeaderRow filterRow = appendHeaderRow();
    filterRow.getCell(TAXON_NAME).setComponent(createColumnFilterField(TAXON_NAME));
    filterRow.getCell(DATE).setComponent(createTimeSpanFilterField());
    filterRow.getCell(RECORDER).setComponent(createColumnFilterField(RECORDER));
    filterRow.getCell(DETERMINER).setComponent(createColumnFilterField(DETERMINER));
  }

  private Component createTimeSpanFilterField() {
    final TimeSpanFilter timeSpanFilter = new TimeSpanFilter();
    timeSpanFilter.addListener((Listener) event -> rebuildAndExecuteFilters());
    return timeSpanFilter;
  }

  private TextField createColumnFilterField(final String placeholder) {
    final TextField filterField = new TextField();
    filterField.setWidth(100, Unit.PERCENTAGE);
    filterField.addStyleName(ValoTheme.TEXTFIELD_TINY);
    filterField.setPlaceholder(placeholder);
    filterField.addValueChangeListener(event -> rebuildAndExecuteFilters());
    return filterField;
  }

  private void rebuildAndExecuteFilters() {
    dataProvider.clearFilters();
    final HeaderRow filterRow = getHeaderRow(getHeaderRowCount() - 1);
    for (final FilterableColumn filterableColumn : columnDecorators) {
      final Column<?, ?> column = filterableColumn.getColumn();
      final HeaderCell headerCell = filterRow.getCell(column);
      if (headerCell.getCellType() != GridStaticCellType.WIDGET) {
        continue;
      }
      final Component filterComponent = headerCell.getComponent();
      if (columnDecorators.contains(filterableColumn) && filterableColumn.isFilterable()
          && filterComponent != null) {
        if (filterComponent instanceof TextField) {
          handleTextFieldFilter(column, (TextField) filterComponent);
        } else if (filterComponent instanceof TimeSpanFilter) {
          handleTimeSpanFilter((TimeSpanFilter) filterComponent);
        }
      }
    }
    dataProvider.refreshAll();
    notifySubscribersAboutUpdatedList(new ArrayList<>());
  }

  private void handleTimeSpanFilter(final TimeSpanFilter timeSpanFilter) {
    dataProvider.setFromFilter(timeSpanFilter.getFrom());
    dataProvider.setToFilter(timeSpanFilter.getTo());
  }

  private void handleTextFieldFilter(final Column<?, ?> column, final TextField textField) {
    final String textFieldValue = textField.getValue();
    if (textFieldValue != null && !textFieldValue.isEmpty()) {
      switch (column.getId()) {
        case Metadata.DETERMINER:
          dataProvider.setDeterminerFilter(textFieldValue);
          break;
        case Metadata.RECORDER:
          dataProvider.setRecorderFilter(textFieldValue);
          break;
        case Metadata.TAXON_NAME:
          dataProvider.setTaxonFilter(textFieldValue);
          break;
        default:
          logger().error("Filter by coloum {} is not supported!", column.getId());
      }
    }
  }

  @PostConstruct
  public void postConstruct() {
    dataProvider = new MetadataDataProvider(metadataService);
    setDataProvider(dataProvider);
    setSelectionMode(SelectionMode.NONE);
    buildAndAddColumns();
    setSizeFull();
  }

  @Override
  public void notifySubscribersAboutUpdatedList(final List<Metadata> list) {
    for (final SelectionListSubscriber subscriber : subscribers) {
      subscriber.currentSelectionListIs(list);
    }
  }

  @Override
  public void addSubscriber(final SelectionListSubscriber subscriber) {
    subscribers.add(subscriber);
  }

  @Override
  public void currentSelectionListIs(final List<Metadata> list) {
    final List<Metadata> oldList = new ArrayList<>(selectedMetadatas);
    selectedMetadatas = new ArrayList<>(list);
    for (final Metadata oldSelectedMetadata : oldList) {
      if (!selectedMetadatas.contains(oldSelectedMetadata)) {
        final ValueProvider<Metadata, ?> valueProvider = getColumn(SELECTED).getValueProvider();
        final CheckBox checkbox = (CheckBox) valueProvider.apply(oldSelectedMetadata);
        undoSelectionAndReloadGridRow(oldSelectedMetadata, checkbox);
      }
    }
  }

  private void undoSelectionAndReloadGridRow(Metadata oldSelectedMetadata,
      CheckBox selectionBoxFromUnselectedMetadataItem) {
    selectionBoxFromUnselectedMetadataItem.setValue(false);
    dataProvider.refreshItem(oldSelectedMetadata);
  }
}
