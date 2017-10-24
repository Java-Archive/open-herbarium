package org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist;

import java.util.List;

public interface SelectionListSubject<T> {

  void notifySubscribersAboutUpdatedList(final List<T> list);

  void addSubscriber(final SelectionListSubscriber subscriber);
}
