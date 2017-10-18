package org.openherbarium.module.ui.component.mainview.searchview.grid.filter;

import java.time.LocalDate;

public class TimeSpan {

    private LocalDate from;
    private LocalDate to;

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
