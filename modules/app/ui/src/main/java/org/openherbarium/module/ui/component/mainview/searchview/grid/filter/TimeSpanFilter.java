package org.openherbarium.module.ui.component.mainview.searchview.grid.filter;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Composite;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;

public class TimeSpanFilter extends Composite implements HasValue.ValueChangeListener<LocalDate> {

    private static final String FROM = "Von";
    private static final String TO = "Bis";

    private enum DateFilter {
        FROM,
        TO
    }

    private DateField from;
    private DateField to;
    private final TimeSpan timeSpan;

    public TimeSpanFilter() {
        final HorizontalLayout dateFieldLayout = new HorizontalLayout();
        from = new DateField();
        to = new DateField();
        timeSpan = new TimeSpan();
        createAndConfigureDateFields();
        layoutDateFields();
        dateFieldLayout.addComponents(from, to);
        configureCompositionRoot(dateFieldLayout);
    }

    private void createAndConfigureDateFields() {
        createAndConfigureDateField(from, DateFilter.FROM, FROM);
        createAndConfigureDateField(to, DateFilter.TO, TO);
    }

    private void createAndConfigureDateField(final DateField dateField, final DateFilter dateFilter, final String placeHolder) {
        dateField.setData(dateFilter);
        dateField.setPlaceholder(placeHolder);
        dateField.addValueChangeListener(this);
    }

    private void layoutDateFields() {
        layoutDateField(from);
        layoutDateField(to);
    }

    public void layoutDateField(final DateField field) {
        field.setWidth("90px");
        field.addStyleName(ValoTheme.DATEFIELD_TINY);
    }

    private void configureCompositionRoot(final HorizontalLayout compositionRoot) {
        setCompositionRoot(compositionRoot);
        compositionRoot.setComponentAlignment(from, Alignment.MIDDLE_LEFT);
        compositionRoot.setComponentAlignment(to, Alignment.MIDDLE_LEFT);
    }

    @Override
    public void valueChange(HasValue.ValueChangeEvent valueChangeEvent) {
        final Component component = valueChangeEvent.getComponent();
        if (!(component instanceof DateField)) {
            return;
        }
        final DateField dateField = (DateField) component;
        final LocalDate newDate = (LocalDate) valueChangeEvent.getValue();
        final DateFilter filter = (DateFilter) dateField.getData();
        switch (filter) {
            case FROM:
                if (to.getValue() != null && newDate != null && newDate.isAfter(to.getValue())) {
                    to.setValue(newDate);
                }
                timeSpan.setFrom(from.getValue());
                break;
            case TO:
                if (from.getValue() != null && newDate != null && newDate.isBefore(from.getValue())) {
                    from.setValue(newDate);
                }
                timeSpan.setTo(to.getValue());
                break;
            default:
                break;
        }
        fireComponentEvent();
    }

    public LocalDate getFrom() {
        return from.getValue();
    }

    public LocalDate getTo() {
        return to.getValue();
    }
}
