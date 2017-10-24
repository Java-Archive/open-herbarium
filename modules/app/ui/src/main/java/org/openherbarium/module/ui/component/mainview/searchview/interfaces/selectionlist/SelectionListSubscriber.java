package org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist;

import org.openherbarium.module.backend.metadataservice.api.Metadata;

import java.util.List;

public interface SelectionListSubscriber {

    public void currentSelectionListIs(List<Metadata> list);
}
