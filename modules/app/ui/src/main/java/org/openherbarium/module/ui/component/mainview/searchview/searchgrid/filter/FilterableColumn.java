package org.openherbarium.module.ui.component.mainview.searchview.searchgrid.filter;

import com.vaadin.ui.Grid;

public class FilterableColumn {

  private final Grid.Column column;
  private boolean isFilterable = false;

  public FilterableColumn(Grid.Column column, boolean isFilterable) {
    this.column = column;
    this.isFilterable = isFilterable;
  }

  public Grid.Column getColumn() {
    return column;
  }

  public boolean isFilterable() {
    return isFilterable;
  }

  public void setFilterable(boolean filterable) {
    isFilterable = filterable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    FilterableColumn that = (FilterableColumn) o;

    return column.getId().equals(that.column.getId());
  }

  @Override
  public int hashCode() {
    return column.getId().hashCode();
  }
}
